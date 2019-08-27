/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.attributes;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 2 févr. 2018
 */
public class GetGlobalAttributesByLogicalNameOperation implements IOperation {


  /**
   * Default Constructor
   */
  public GetGlobalAttributesByLogicalNameOperation() {
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler,
      Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");

      IOperationResult result = CloudServicesLocator.globalAttributesApi.getGlobalAttributesByLogicalName(query);
      resultHandler.accept(result);
    }
    catch (Exception e) {
      if(exHandler != null) exHandler.accept(e);
    }
  }
}
