import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceAccessException;
import org.apache.uima.resource.ResourceInitializationException;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.ConfidenceChunker;
import com.aliasi.util.AbstractExternalizable;

/**
 * Position, TagName Annotator
 * 
 * @author <a href="mailto:yksun@cs.cmu.edu">Yang Sun</a>
 * 
 */
public class PosTagNamedEntityRecognizer extends JCasAnnotator_ImplBase {
  /**
   * Name of configuration parameter that must be set to the Maximum N Best Chunks.
   */
  public static final String PARAM_MAXN = "MAX_N_BEST_CHUNKS";

  /**
   * Name of configuration parameter that must be set to the Confidence Acceptance Level.
   */
  public static final String PARAM_THRESHOLD = "Threshold";

  private ConfidenceChunker chunker;

  private JCas jcas;

  private int maxN;

  private double threshold;

  /**
   * @see org.apache.uima.AnalysisComponent.AnalysisComponent#initialize(org.apache.uima.AnalysisComponent.AnalysisComponentContext)
   */
  @Override
  public void initialize(UimaContext aContext) throws ResourceInitializationException {
    super.initialize(aContext);
    try {
      maxN = (Integer) aContext.getConfigParameterValue(PARAM_MAXN);
      threshold = (Float) aContext.getConfigParameterValue(PARAM_THRESHOLD);
      chunker = (ConfidenceChunker) AbstractExternalizable.readObject(new File(getContext()
              .getResourceFilePath("LingPipeModel")));
    } catch (IOException e) {
      throw new ResourceInitializationException();
    } catch (ClassNotFoundException e) {
      throw new ResourceInitializationException();
    } catch (ResourceAccessException e) {
      throw new ResourceInitializationException();
    }
  }

  /**
   * @see org.apache.uima.analysis_component.AnalysisComponent#process(org.apache.uima.core.AbstractCas)
   */
  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    jcas = aJCas;
    Iterator<Annotation> model_iter = aJCas.getAnnotationIndex(SourceModel.type).iterator();
    while (model_iter.hasNext()) {
      produceGeneSpans((SourceModel) model_iter.next());
    }

  }

  /**
   * Get all the indices of whitespace in the text
   * 
   * @param text
   *          String object that will be processed in this method.
   * @return the ArrayList<Integer> object that contains the indices of whitespace in the text.
   */
  private ArrayList<Integer> getSpaceSpans(String text) {
    ArrayList<Integer> spaceList = new ArrayList<Integer>();
    int i = 0;
    while ((i = text.indexOf(" ", i)) > 0)
      spaceList.add(i++);
    return spaceList;
  }

  /**
   * Get the number of whitespace before the target index position.
   * 
   * @param position
   *          int that will be treated as the target index.
   * @param spaceList
   *          ArrayList<Integer> object that will be used as the source.
   * @return the int that indicates the number of whitespace before the target index.
   */
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

  /**
   * Determine if the input text is a complete gene mention
   * 
   * @param text
   *          String that will be processed in this method.
   * @return the boolean that indicates the true/false value.
   */
  private boolean isComplete(String text) {
    int left = text.indexOf("(");
    int right = text.indexOf(")");

    return (left != -1 && right > left) || (left == -1 && right == -1);
  }

  /**
   * Produce the Gene Information according to recognizer's confidence.
   * 
   * @param model
   *          SourceModel object that will be served as data source and processed in this method.
   */
  private void produceGeneSpans(SourceModel model) {
    char[] cs = model.getSentence().toCharArray();
    Iterator<Chunk> iter = chunker.nBestChunks(cs, 0, cs.length, maxN);
    while (iter.hasNext()) {
      Chunk chunk = iter.next();
      double conf = Math.pow(2.0, chunk.score());
      String gene = model.getSentence().substring(chunk.start(), chunk.end());
      if (conf > threshold && gene.length() > 1 && isComplete(gene)) {
        ProcessedModel outputModel = new ProcessedModel(jcas);
        final ArrayList<Integer> spaceList = getSpaceSpans(model.getSentence());
        int numBeginSpaces = getNumSpaces(chunk.start(), spaceList);
        int numEndSpaces = getNumSpaces(chunk.end(), spaceList);

        outputModel.setId(model.getId());
        outputModel.setBegin(chunk.start() - numBeginSpaces);
        outputModel.setEnd(chunk.end() - 1 - numEndSpaces);
        outputModel.setGene(gene);
        outputModel.setConf(conf);
        outputModel.addToIndexes();
      }
    }
  }
}
