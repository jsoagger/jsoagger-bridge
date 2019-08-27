/**
 *
 */
package io.github.jsoagger.core.business.offline.services.notifications.api;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.exception.OperationException;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface INotificationsApi {

  IOperationResult createNotification(JsonObject query) throws OperationException;


  IOperationResult deleteNotification(JsonObject query) throws OperationException;


  IOperationResult setStatus(JsonObject query) throws OperationException;


  IOperationResult loadAllNotification(JsonObject query) throws OperationException;


  IOperationResult deleteAllNotifications(JsonObject query) throws OperationException;


  IOperationResult markAllNotificationsReaden(JsonObject query) throws OperationException;

}
