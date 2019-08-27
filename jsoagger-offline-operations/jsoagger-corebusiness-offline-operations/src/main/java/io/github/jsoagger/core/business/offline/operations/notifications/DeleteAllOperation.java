/**
 *
 */
package io.github.jsoagger.core.business.offline.operations.notifications;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.offline.services.notifications.OffLineServiceLocator;
import io.github.jsoagger.core.bridge.exception.OperationException;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class DeleteAllOperation implements IOperation {

  /**
   * Constructor
   */
  public DeleteAllOperation() {
  }

  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    try {
      IOperationResult result = OffLineServiceLocator.notificationsService.deleteAllNotifications(query);
      resultHandler.accept(result);
    } catch (OperationException e) {
      e.printStackTrace();
      if (exHandler != null) {
        exHandler.accept(e);
      }
    }
  }

}
