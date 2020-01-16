/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.aspect;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class LifecycleManagedFeatureArgs {

  private boolean  loadAllLifecycleStates = true;
  private boolean  loadLifecycleName = true;

  /**
   * Constructor
   */
  public LifecycleManagedFeatureArgs() {
  }

  /**
   * @return the loadAllLifecycleStates
   */
  public boolean isLoadAllLifecycleStates() {
    return loadAllLifecycleStates;
  }

  /**
   * @param loadAllLifecycleStates the loadAllLifecycleStates to set
   */
  public void setLoadAllLifecycleStates(boolean loadAllLifecycleStates) {
    this.loadAllLifecycleStates = loadAllLifecycleStates;
  }

  /**
   * @return the loadLifecycleName
   */
  public boolean isLoadLifecycleName() {
    return loadLifecycleName;
  }

  /**
   * @param loadLifecycleName the loadLifecycleName to set
   */
  public void setLoadLifecycleName(boolean loadLifecycleName) {
    this.loadLifecycleName = loadLifecycleName;
  }
}
