/* First created by JCasGen Sun Oct 14 21:55:13 EDT 2012 */

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
 * Updated by JCasGen Sun Oct 14 21:55:13 EDT 2012
 * 
 * @generated
 */
public class SourceModel_Type extends Annotation_Type {
  /** @generated */
  @Override
  protected FSGenerator getFSGenerator() {
    return fsGenerator;
  }

  /** @generated */
  private final FSGenerator fsGenerator = new FSGenerator() {
    public FeatureStructure createFS(int addr, CASImpl cas) {
      if (SourceModel_Type.this.useExistingInstance) {
        // Return eq fs instance if already created
        FeatureStructure fs = SourceModel_Type.this.jcas.getJfsFromCaddr(addr);
        if (null == fs) {
          fs = new SourceModel(addr, SourceModel_Type.this);
          SourceModel_Type.this.jcas.putJfsFromCaddr(addr, fs);
          return fs;
        }
        return fs;
      } else
        return new SourceModel(addr, SourceModel_Type.this);
    }
  };

  /** @generated */
  public final static int typeIndexID = SourceModel.typeIndexID;

  /**
   * @generated
   * @modifiable
   */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("SourceModel");

  /** @generated */
  final Feature casFeat_sentence;

  /** @generated */
  final int casFeatCode_sentence;

  /** @generated */
  public String getSentence(int addr) {
    if (featOkTst && casFeat_sentence == null)
      jcas.throwFeatMissing("sentence", "SourceModel");
    return ll_cas.ll_getStringValue(addr, casFeatCode_sentence);
  }

  /** @generated */
  public void setSentence(int addr, String v) {
    if (featOkTst && casFeat_sentence == null)
      jcas.throwFeatMissing("sentence", "SourceModel");
    ll_cas.ll_setStringValue(addr, casFeatCode_sentence, v);
  }

  /** @generated */
  final Feature casFeat_id;

  /** @generated */
  final int casFeatCode_id;

  /** @generated */
  public String getId(int addr) {
    if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "SourceModel");
    return ll_cas.ll_getStringValue(addr, casFeatCode_id);
  }

  /** @generated */
  public void setId(int addr, String v) {
    if (featOkTst && casFeat_id == null)
      jcas.throwFeatMissing("id", "SourceModel");
    ll_cas.ll_setStringValue(addr, casFeatCode_id, v);
  }

  /**
   * initialize variables to correspond with Cas Type and Features
   * 
   * @generated
   */
  public SourceModel_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl) this.casType, getFSGenerator());

    casFeat_sentence = jcas.getRequiredFeatureDE(casType, "sentence", "uima.cas.String", featOkTst);
    casFeatCode_sentence = (null == casFeat_sentence) ? JCas.INVALID_FEATURE_CODE
            : ((FeatureImpl) casFeat_sentence).getCode();

    casFeat_id = jcas.getRequiredFeatureDE(casType, "id", "uima.cas.String", featOkTst);
    casFeatCode_id = (null == casFeat_id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl) casFeat_id)
            .getCode();

  }
}
