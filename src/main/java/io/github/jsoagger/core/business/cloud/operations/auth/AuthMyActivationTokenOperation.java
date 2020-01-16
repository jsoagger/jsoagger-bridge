/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.auth;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.operations.ICloudOperation;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class AuthMyActivationTokenOperation implements ICloudOperation {

  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query,
      Consumer<IOperationResult> resultHandler,
      Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      IOperationResult res = CloudServicesLocator.authenticationApi.activationToken(query);
      resultHandler.accept(res);
    }
    catch (Exception e) {
      if(exHandler != null) exHandler.accept(e);
    }
    finally {
    }
  }
}
