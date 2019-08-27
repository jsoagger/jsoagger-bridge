/**
 * 4 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.operations.decorated;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.operations.aspect.LifecycleManagedFeatureArgs;
import io.github.jsoagger.core.business.cloud.operations.aspect.TypeManagedFeatureArgs;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 4 févr. 2018
 */
public class DGetLatestVersionByMasterOidOperation implements IOperation {

  private TypeManagedFeatureArgs typeManagedFeatureArgs = new TypeManagedFeatureArgs();
  private LifecycleManagedFeatureArgs lifecycleManagedFeatureArgs = new LifecycleManagedFeatureArgs();

  /**
   * Default Constructor
   */
  public DGetLatestVersionByMasterOidOperation() {
    typeManagedFeatureArgs.setLoadType(true);
    typeManagedFeatureArgs.setLoadAttributeDefinitions(true);

    lifecycleManagedFeatureArgs.setLoadAllLifecycleStates(true);
    lifecycleManagedFeatureArgs.setLoadLifecycleName(true);
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      IOperationResult result = CloudServicesLocator.rcApi.getLatestVersion(query);
      resultHandler.accept(result);
    }
    catch (Exception e) {
      if(exHandler != null) exHandler.accept(e);
    }
  }


  /**
   * @return the typeManagedFeatureArgs
   */
  public TypeManagedFeatureArgs getTypeManagedFeatureArgs() {
    return typeManagedFeatureArgs;
  }


  /**
   * @param typeManagedFeatureArgs the typeManagedFeatureArgs to set
   */
  public void setTypeManagedFeatureArgs(TypeManagedFeatureArgs typeManagedFeatureArgs) {
    this.typeManagedFeatureArgs = typeManagedFeatureArgs;
  }


  /**
   * @return the lifecycleManagedFeatureArgs
   */
  public LifecycleManagedFeatureArgs getLifecycleManagedFeatureArgs() {
    return lifecycleManagedFeatureArgs;
  }


  /**
   * @param lifecycleManagedFeatureArgs the lifecycleManagedFeatureArgs to set
   */
  public void setLifecycleManagedFeatureArgs(LifecycleManagedFeatureArgs lifecycleManagedFeatureArgs) {
    this.lifecycleManagedFeatureArgs = lifecycleManagedFeatureArgs;
  }
}
