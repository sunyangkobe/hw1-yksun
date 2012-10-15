/* First created by JCasGen Sun Oct 14 21:56:40 EDT 2012 */

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;

/**
 * Updated by JCasGen Sun Oct 14 21:56:40 EDT 2012 XML source:
 * C:/Users/KOBE/workspace/hw1-yksun/src/main/resources/descriptors/type_system/ProcessedModel.xml
 * 
 * @generated
 */
public class ProcessedModel extends Annotation {
  /**
   * @generated
   * @ordered
   */
  public final static int typeIndexID = JCasRegistry.register(ProcessedModel.class);

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
  protected ProcessedModel() {/* intentionally empty block */
  }

  /**
   * Internal - constructor used by generator
   * 
   * @generated
   */
  public ProcessedModel(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }

  /** @generated */
  public ProcessedModel(JCas jcas) {
    super(jcas);
    readObject();
  }

  /** @generated */
  public ProcessedModel(JCas jcas, int begin, int end) {
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
  // * Feature: id

  /**
   * getter for id - gets
   * 
   * @generated
   */
  public String getId() {
    if (ProcessedModel_Type.featOkTst && ((ProcessedModel_Type) jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "ProcessedModel");
    return jcasType.ll_cas.ll_getStringValue(addr, ((ProcessedModel_Type) jcasType).casFeatCode_id);
  }

  /**
   * setter for id - sets
   * 
   * @generated
   */
  public void setId(String v) {
    if (ProcessedModel_Type.featOkTst && ((ProcessedModel_Type) jcasType).casFeat_id == null)
      jcasType.jcas.throwFeatMissing("id", "ProcessedModel");
    jcasType.ll_cas.ll_setStringValue(addr, ((ProcessedModel_Type) jcasType).casFeatCode_id, v);
  }

  // *--------------*
  // * Feature: gene

  /**
   * getter for gene - gets
   * 
   * @generated
   */
  public String getGene() {
    if (ProcessedModel_Type.featOkTst && ((ProcessedModel_Type) jcasType).casFeat_gene == null)
      jcasType.jcas.throwFeatMissing("gene", "ProcessedModel");
    return jcasType.ll_cas.ll_getStringValue(addr,
            ((ProcessedModel_Type) jcasType).casFeatCode_gene);
  }

  /**
   * setter for gene - sets
   * 
   * @generated
   */
  public void setGene(String v) {
    if (ProcessedModel_Type.featOkTst && ((ProcessedModel_Type) jcasType).casFeat_gene == null)
      jcasType.jcas.throwFeatMissing("gene", "ProcessedModel");
    jcasType.ll_cas.ll_setStringValue(addr, ((ProcessedModel_Type) jcasType).casFeatCode_gene, v);
  }

  @Override
  public String toString() {
    return getId() + "|" + getBegin() + " " + getEnd() + "|" + getGene();
  }
}
