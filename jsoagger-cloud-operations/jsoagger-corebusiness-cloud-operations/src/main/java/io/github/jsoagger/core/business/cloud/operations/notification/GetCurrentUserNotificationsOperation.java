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
import io.github.jsoagger.core.bridge.result.OperationData;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class GetCurrentUserNotificationsOperation implements IOperation {

  // needs GetCurrentUserOperation
  private IOperation getCurrentUserOperation;


  /**
   * Constructor
   */
  public GetCurrentUserNotificationsOperation() {
    super();
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      getCurrentUserOperation.doOperation(new JsonObject(), res -> {
        OperationData data = (OperationData) res.rootData();
        System.out.println(data.getAttributes());
        String login = (String) data.getAttributes().get("login");
        query.addProperty("login", login);

        IOperationResult result;
        try {
          System.out.println(query);
          result = CloudServicesLocator.notificationApi.loadAllNotification(query);
          resultHandler.accept(result);
        }
        catch (OperationException e) {
          if(exHandler != null) exHandler.accept(e);
        }
      });
    }
    catch (Exception e) {
      if(exHandler != null) exHandler.accept(e);
    }
  }


  /**
   * @return the getCurrentUserOperation
   */
  public IOperation getGetCurrentUserOperation() {
    return getCurrentUserOperation;
  }


  /**
   * @param getCurrentUserOperation the getCurrentUserOperation to set
   */
  public void setGetCurrentUserOperation(IOperation getCurrentUserOperation) {
    this.getCurrentUserOperation = getCurrentUserOperation;
  }
}
