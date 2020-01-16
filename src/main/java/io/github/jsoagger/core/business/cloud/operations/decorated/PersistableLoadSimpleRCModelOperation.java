/**
 * 24 janv. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.operations.decorated;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * Loads a Revision controlled, ie load Entity + its master
 *
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 24 janv. 2018
 */
public class PersistableLoadSimpleRCModelOperation implements IOperation {

  private IOperationResult	result;

  /**
   * Constructor
   */
  public PersistableLoadSimpleRCModelOperation() {
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      result = CloudServicesLocator.persistableApi.loadBasicRCModel(query);
      // WITHOUT FEATURES, BECAUSE SERVICE NOT INSTALLED IN ALL CLIENTS LIKE
      // CONSOLE ADMIN
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
}