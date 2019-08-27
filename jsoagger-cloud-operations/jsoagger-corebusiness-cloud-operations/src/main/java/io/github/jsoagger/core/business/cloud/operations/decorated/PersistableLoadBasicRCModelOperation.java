/**
 * 24 janv. 2018
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
import io.github.jsoagger.core.bridge.result.SingleResult;
import io.github.jsoagger.jfxcore.api.services.Services;

/**
 * Loads a Revision controlled, ie load Entity + its master
 *
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 24 janv. 2018
 */
public class PersistableLoadBasicRCModelOperation implements IOperation {

  private IOperationResult	result;
  private TypeManagedFeatureArgs typeManagedFeatureArgs = new TypeManagedFeatureArgs();
  private LifecycleManagedFeatureArgs lifecycleManagedFeatureArgs = new LifecycleManagedFeatureArgs();

  /**
   * Constructor
   */
  public PersistableLoadBasicRCModelOperation() {
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
      result = CloudServicesLocator.persistableApi.loadBasicRCModel(query);

      if(lifecycleManagedFeatureArgs.isLoadAllLifecycleStates()  && result instanceof SingleResult) {
        IOperation glsop = (IOperation) Services.getBean("GetLifecycleStatesOperation");
        glsop.doOperation(query, res -> {
          if(res != null) {
            ((SingleResult)result).getData().getLinks().put("allStates",((SingleResult)res).getData().getAttributes().get("allStates"));
            ((SingleResult)result).getData().getLinks().put("stateByDenote",((SingleResult)res).getData().getAttributes().get("stateByDenote"));
            ((SingleResult)result).getData().getLinks().put("stateByPromote",((SingleResult)res).getData().getAttributes().get("stateByPromote"));
          }
        });
      }

      resultHandler.accept(result);
    }
    catch (Exception e) {
      if (exHandler != null) {
        exHandler.accept(e);
      }
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getResult() {
    return result;
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


  /**
   * @param result the result to set
   */
  public void setResult(IOperationResult result) {
    this.result = result;
  }
}