/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.api;

import io.github.jsoagger.core.bridge.exception.OperationException;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import com.google.gson.JsonObject;

/**
 * No createNotification method, use notify user
 *
 * @author Ramilafananana  VONJISOA
 *
 */
public interface INotificationApi {

	IOperationResult deleteNotification(JsonObject query) throws OperationException;


	IOperationResult setStatus(JsonObject query) throws OperationException;


	IOperationResult loadAllNotification(JsonObject query) throws OperationException;


	IOperationResult deleteAllNotifications(JsonObject query) throws OperationException;


	IOperationResult markAllNotificationsReaden(JsonObject query) throws OperationException;


	IOperationResult notifyGroup(JsonObject params);


	IOperationResult notifyTeam(JsonObject params);


	IOperationResult notifyUser(JsonObject params);


	IOperationResult countUserNotifications(JsonObject params);
}
