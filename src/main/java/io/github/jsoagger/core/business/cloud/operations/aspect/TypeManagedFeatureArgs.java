/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.aspect;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class TypeManagedFeatureArgs {

  private boolean loadType = true;
  private boolean loadAttributeDefinitions = true;

  /**
   * Constructor
   */
  public TypeManagedFeatureArgs() {
  }

  /**
   * @return the loadType
   */
  public boolean isLoadType() {
    return loadType;
  }

  /**
   * @param loadType the loadType to set
   */
  public void setLoadType(boolean loadType) {
    this.loadType = loadType;
  }

  /**
   * @return the loadAttributeDefinitions
   */
  public boolean isLoadAttributeDefinitions() {
    return loadAttributeDefinitions;
  }

  /**
   * @param loadAttributeDefinitions the loadAttributeDefinitions to set
   */
  public void setLoadAttributeDefinitions(boolean loadAttributeDefinitions) {
    this.loadAttributeDefinitions = loadAttributeDefinitions;
  }
}
