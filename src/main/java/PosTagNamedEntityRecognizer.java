import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;

import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class PosTagNamedEntityRecognizer extends JCasAnnotator_ImplBase {

  private StanfordCoreNLP pipeline;

  private JCas jcas;

  public PosTagNamedEntityRecognizer() throws ResourceInitializationException {
    Properties props = new Properties();
    props.put("annotators", "tokenize, ssplit, pos");
    pipeline = new StanfordCoreNLP(props);
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

  private void getGeneSpans(SourceModel model, ArrayList<Integer> spaceList) {
    Annotation sentence = new Annotation(model.getSentence());
    pipeline.annotate(sentence);
    List<CoreLabel> candidate = new ArrayList<CoreLabel>();
    for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
      String pos = token.get(PartOfSpeechAnnotation.class);
      if (pos.startsWith("NN")) {
        candidate.add(token);
      } else if (candidate.size() > 0) {
        int numBeginSpaces = getNumSpaces(candidate.get(0).beginPosition(), spaceList);
        int numEndSpaces = getNumSpaces(candidate.get(candidate.size() - 1).endPosition(),
                spaceList);
        ProcessedModel outputModel = new ProcessedModel(jcas);
        outputModel.setId(model.getId());
        outputModel.setBegin(candidate.get(0).beginPosition() - numBeginSpaces);
        outputModel.setEnd(candidate.get(candidate.size() - 1).endPosition() - 1 - numEndSpaces);
        outputModel.setGene(model.getSentence().substring(outputModel.getBegin() + numBeginSpaces,
                outputModel.getEnd() + numEndSpaces + 1));
        outputModel.addToIndexes();
        candidate.clear();
      }
    }
    if (candidate.size() > 0) {
      int numBeginSpaces = getNumSpaces(candidate.get(0).beginPosition(), spaceList);
      int numEndSpaces = getNumSpaces(candidate.get(candidate.size() - 1).endPosition(), spaceList);
      ProcessedModel outputModel = new ProcessedModel(jcas);
      outputModel.setId(model.getId());
      outputModel.setBegin(candidate.get(0).beginPosition() - numBeginSpaces);
      outputModel.setEnd(candidate.get(candidate.size() - 1).endPosition() - 1 - numEndSpaces);
      outputModel.setGene(model.getSentence().substring(outputModel.getBegin() + numBeginSpaces,
              outputModel.getEnd() + numEndSpaces + 1));
      outputModel.addToIndexes();
      candidate.clear();
    }
  }

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    jcas = aJCas;
    Iterator<org.apache.uima.jcas.tcas.Annotation> model_iter = aJCas.getAnnotationIndex(
            SourceModel.type).iterator();
    while (model_iter.hasNext()) {
      SourceModel model = (SourceModel) model_iter.next();
      final ArrayList<Integer> spaceList = getSpaceSpans(model.getSentence());
      getGeneSpans(model, spaceList);
    }

  }
}
