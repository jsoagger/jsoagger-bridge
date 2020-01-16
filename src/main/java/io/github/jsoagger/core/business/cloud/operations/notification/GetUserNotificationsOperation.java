/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.notification;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.exception.OperationException;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class GetUserNotificationsOperation implements IOperation {

  /**
   * Constructor
   */
  public GetUserNotificationsOperation() {
    super();
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      IOperationResult result = CloudServicesLocator.notificationApi.loadAllNotification(query);
      resultHandler.accept(result);
    }
    catch (OperationException e) {
      if(exHandler != null) exHandler.accept(e);
    }
  }
}