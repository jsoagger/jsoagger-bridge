/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.folder;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class GetApplicationContainerRootFolder implements IOperation {

  /**
   * Constructor
   */
  public GetApplicationContainerRootFolder() {
    super();
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query,
      Consumer<IOperationResult> resultHandler,
      Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      query.addProperty("fullId", "1:io.github.jsoagger.core.model.api.composite.ApplicationContainer");
      query.addProperty("oid", "1:io.github.jsoagger.core.model.api.composite.ApplicationContainer");
      IOperationResult result = CloudServicesLocator.containerApi.rootFolder(query);

      MultipleResult multipleResult = (MultipleResult) IOperationResult.emptyMultipleResult();
      multipleResult.addMetaData("totalPages", 1);
      multipleResult.addMetaData("totalElements", 1);
      multipleResult.getData().add(0, ((SingleResult)result).getData());
      resultHandler.accept(multipleResult);
    }
    catch (Exception e) {
      if(exHandler != null) exHandler.accept(e);
    }
  }

}
