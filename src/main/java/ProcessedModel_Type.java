/* First created by JCasGen Sun Oct 14 21:56:40 EDT 2012 */

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/**
 * Updated by JCasGen Sun Oct 14 21:56:40 EDT 2012
 * 
 * @generated
 */
public class ProcessedModel_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {
    return fsGenerator;
  }

  /** @generated */
  private final FSGenerator fsGenerator = new FSGenerator() {
    public FeatureStructure createFS(int addr, CASImpl cas) {
      if (ProcessedModel_Type.this.useExistingInstance) {
        // Return eq fs instance if already created
        FeatureStructure fs = ProcessedModel_Type.this.jcas.getJfsFromCaddr(addr);
        if (null == fs) {
          fs = new ProcessedModel(addr, ProcessedModel_Type.this);
          ProcessedModel_Type.this.jcas.putJfsFromCaddr(addr, fs);
          return fs;
        }
        return fs;
      } else
        return new ProcessedModel(addr, ProcessedModel_Type.this);
    }
  };

  /** @generated */
  public final static int typeIndexID = ProcessedModel.typeIndexID;

  /**
   * @generated
   * @modifiable
   */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("ProcessedModel");

  /** @generated */
  final Feature casFeat_id;

  /** @generated */
  final int casFeatCode_id;

  /** @generated */
  public String getId(int addr) {
    if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "ProcessedModel");
    return ll_cas.ll_getStringValue(addr, casFeatCode_id);
  }

  /** @generated */
  public void setId(int addr, String v) {
    if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "ProcessedModel");
    ll_cas.ll_setStringValue(addr, casFeatCode_id, v);
  }

  /** @generated */
  final Feature casFeat_gene;

  /** @generated */
  final int casFeatCode_gene;

  /** @generated */
  public String getGene(int addr) {
    if (featOkTst && casFeat_gene == null)
      jcas.throwFeatMissing("gene", "ProcessedModel");
    return ll_cas.ll_getStringValue(addr, casFeatCode_gene);
  }

  /** @generated */
  public void setGene(int addr, String v) {
    if (featOkTst && casFeat_gene == null)
      jcas.throwFeatMissing("gene", "ProcessedModel");
    ll_cas.ll_setStringValue(addr, casFeatCode_gene, v);
  }

  /**
   * initialize variables to correspond with Cas Type and Features
   * 
   * @generated
   */
  public ProcessedModel_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl) this.casType, getFSGenerator());

    casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.String", featOkTst);
    casFeatCode_id = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl) casFeat_id)
            .getCode();

    casFeat_gene = jcas.getRequiredFeatureDE(casType, "gene", "uima.cas.String", featOkTst);
    casFeatCode_gene = (null == casFeat_gene) ? JCas.INVALID_FEATURE_CODE
            : ((FeatureImpl) casFeat_gene).getCode();

  }
}
