/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.appversion;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019 
 */
public class GetAppVersionOperation implements IOperation {

  /* (non-Javadoc)
   * @see io.github.jsoagger.core.bridge.operation.IOperation#doOperation(com.google.gson.JsonObject, java.util.function.Consumer, java.util.function.Consumer)
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler,
      Consumer<Throwable> exHandler) {

    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      IOperationResult result = CloudServicesLocator.appVersionApi.getApplicationVersion(query);
      resultHandler.accept(result);
    }
    catch (Exception e) {
      if(exHandler != null) exHandler.accept(e);
    }
  }

}
