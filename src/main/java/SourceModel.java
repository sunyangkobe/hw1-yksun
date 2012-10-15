
/* First created by JCasGen Sun Oct 14 21:55:13 EDT 2012 */

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;

/**
 * Updated by JCasGen Sun Oct 14 21:55:13 EDT 2012 XML source:
 * C:/Users/KOBE/workspace/hw1-yksun/src/main/resources/descriptors/type_system/SourceModel.xml
 * 
 * @generated
 */
public class SourceModel extends Annotation {
  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(SourceModel.class);

  /**
   * @generated
   * @ordered
   */
  public final static int type = typeIndexID;

  /** @generated */
  @Override
  public int getTypeIndexID() {
    return typeIndexID;
  }

  /**
   * Never called. Disable default constructor
   * 
   * @generated
   */
  protected SourceModel() {/* intentionally empty block */
  }

  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public SourceModel(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }

  /** @generated */
  public SourceModel(JCas jcas) {
    super(jcas);
    readObject();
  }

  /** @generated */
  public SourceModel(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }

  /**
   * <!-- begin-user-doc --> Write your own initialization here <!-- end-user-doc -->
   * 
   * @generated modifiable
   */
  private void readObject() {/* default - does nothing empty block */
  }

  // *--------------*
  // * Feature: sentence

  /**
   * getter for sentence - gets Current sentence
   * 
   * @generated
   */
  public String getSentence() {
    if (SourceModel_Type.featOkTst && ((SourceModel_Type) jcasType).casFeat_sentence == null)
      jcasType.jcas.throwFeatMissing("sentence", "SourceModel");
    return jcasType.ll_cas.ll_getStringValue(addr,
            ((SourceModel_Type) jcasType).casFeatCode_sentence);
  }

  /**
   * setter for sentence - sets Current sentence
   * 
   * @generated
   */
  public void setSentence(String v) {
    if (SourceModel_Type.featOkTst && ((SourceModel_Type) jcasType).casFeat_sentence == null)
      jcasType.jcas.throwFeatMissing("sentence", "SourceModel");
    jcasType.ll_cas.ll_setStringValue(addr, ((SourceModel_Type) jcasType).casFeatCode_sentence, v);
  }

  // *--------------*
  // * Feature: id

  /**
   * getter for id - gets
   * 
   * @generated
   */
  public String getId() {
    if (SourceModel_Type.featOkTst && ((SourceModel_Type) jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "SourceModel");
    return jcasType.ll_cas.ll_getStringValue(addr, ((SourceModel_Type) jcasType).casFeatCode_id);
  }

  /**
   * setter for id - sets
   * 
   * @generated
   */
  public void setId(String v) {
    if (SourceModel_Type.featOkTst && ((SourceModel_Type) jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "SourceModel");
    jcasType.ll_cas.ll_setStringValue(addr, ((SourceModel_Type) jcasType).casFeatCode_id, v);
  }
}
