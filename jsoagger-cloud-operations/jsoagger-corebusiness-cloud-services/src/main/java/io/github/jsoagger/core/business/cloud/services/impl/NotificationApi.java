/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;


import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.INotificationApi;
import io.github.jsoagger.core.bridge.exception.OperationException;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class NotificationApi extends AbstractClientApi implements INotificationApi {

  private static final String	ALL_NOTIFICATIONS_URI		= "/api/account/%s/notification/?status=%s&page=%s&pageSize=%s&sort=%s";
  private static final String	COUNT_ALL_NOTIFICATIONS_URI	= "/api/account/%s/notification/count/?status=%s";
  private static final String	SET_STATUS_URI				= "/api/notification/%s/status/%s";
  private static final String	NOTIFY_GROUP_URI			= "/api/notification/notifyGroup";
  private static final String	NOTIFY_TEAM_URI				= "/api/notification/notifyTeam";
  private static final String	NOTIFY_USER_URI				= "/api/notification/notifyUser";
  private static final String	DELETE_ONE_URI				= "/api/notification/%s";
  private static final String	DELETE_ALL_URI				= "/api/account/%s/notification/deleteAll";
  private static final String	MARK_ALL_READEN_URI			= "/api/account/%s/notification/markAllReaden";


  @Override
  public IOperationResult notifyGroup(JsonObject query) {
    try {
      query.get("groupId");
      query.get("messageTemplateId");
      String url = getRootUrl().concat(String.format(NOTIFY_GROUP_URI));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult notifyTeam(JsonObject query) {
    try {
      query.get("teamId");
      query.get("messageTemplateId");
      String url = getRootUrl().concat(String.format(NOTIFY_TEAM_URI));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult deleteNotification(JsonObject query) throws OperationException {
    try {
      String url = getRootUrl().concat(String.format(DELETE_ONE_URI, query.get("fullId") + "/"));
      IOperationResult result = doDelete(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult setStatus(JsonObject query) throws OperationException {
    try {
      Object fid = query.get("fullId");
      Object status = query.get("status");
      String url = getRootUrl().concat(String.format(SET_STATUS_URI, fid, status));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult countUserNotifications(JsonObject query) {
    try {
      Object login = query.get("login");
      Object status = query.get("status");
      String url = getRootUrl().concat(String.format(COUNT_ALL_NOTIFICATIONS_URI, login, status));
      IOperationResult result = doGet(query, url, SingleResult.class);
      System.out.println(result);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult loadAllNotification(JsonObject query) throws OperationException {
    try {
      Object login = query.get("login");
      Object status = query.get("status");
      Object page = query.get("page");
      Object pageSize = query.get("pageSize");
      Object sort = query.get("sort");
      String url = getRootUrl().concat(String.format(ALL_NOTIFICATIONS_URI, login, status, page, pageSize, sort));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }


  @Override
  public IOperationResult deleteAllNotifications(JsonObject query) throws OperationException {
    try {
      Object login = query.get("login");
      String url = getRootUrl().concat(String.format(DELETE_ALL_URI, login));
      IOperationResult result = doDelete(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult markAllNotificationsReaden(JsonObject query) throws OperationException {
    try {
      Object login = query.get("login");
      String url = getRootUrl().concat(String.format(MARK_ALL_READEN_URI, login));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult notifyUser(JsonObject query) {
    try {
      query.get("login");
      String url = getRootUrl().concat(String.format(NOTIFY_USER_URI));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }
}
