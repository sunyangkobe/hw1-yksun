import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.ConfidenceChunker;
import com.aliasi.util.AbstractExternalizable;

public class PosTagNamedEntityRecognizer extends JCasAnnotator_ImplBase {

  private ConfidenceChunker chunker;

  private JCas jcas;

  public PosTagNamedEntityRecognizer() {
  }

  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    try {
      String modelPath = (String) aContext.getConfigParameterValue("ModelFile");
      Properties props = new Properties();
      props.put("annotators", "tokenize, ssplit, pos");
      chunker = (ConfidenceChunker) AbstractExternalizable.readObject(new File(modelPath));
    } catch (IOException e) {
      throw new ResourceInitializationException();
    } catch (ClassNotFoundException e) {
      throw new ResourceInitializationException();
    }
  }

  private ArrayList<Integer> getSpaceSpans(String text) {
    ArrayList<Integer> spaceList = new ArrayList<Integer>();
    int i = 0;
    while ((i = text.indexOf(" ", i)) > 0)
      spaceList.add(i++);
    return spaceList;
  }

  private int getNumSpaces(int position, ArrayList<Integer> spaceList) {
    int numSpaces = 0;
    for (Integer e : spaceList) {
      if (e < position)
        numSpaces++;
      else
        return numSpaces;
    }
    return numSpaces;
  }

  private void getGeneSpans(SourceModel model) {
    char[] cs = model.getSentence().toCharArray();
    Iterator<Chunk> iter = chunker.nBestChunks(cs, 0, cs.length, 100);
    while (iter.hasNext()) {
      Chunk chunk = iter.next();
      double conf = Math.pow(2.0, chunk.score());
      if (conf > 0.65) {
        ProcessedModel outputModel = new ProcessedModel(jcas);
        final ArrayList<Integer> spaceList = getSpaceSpans(model.getSentence());
        int numBeginSpaces = getNumSpaces(chunk.start(), spaceList);
        int numEndSpaces = getNumSpaces(chunk.end(), spaceList);

        outputModel.setId(model.getId());
        outputModel.setBegin(chunk.start() - numBeginSpaces);
        outputModel.setEnd(chunk.end() - 1 - numEndSpaces);
        outputModel.setGene(model.getSentence().substring(outputModel.getBegin() + numBeginSpaces,
                outputModel.getEnd() + numEndSpaces + 1));
        outputModel.setConf(conf);
        outputModel.addToIndexes();
      }
    }
  }

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    jcas = aJCas;
    Iterator<org.apache.uima.jcas.tcas.Annotation> model_iter = aJCas.getAnnotationIndex(
            SourceModel.type).iterator();
    while (model_iter.hasNext()) {
      getGeneSpans((SourceModel) model_iter.next());
    }

  }
}
