/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonObject;

import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.SingleResult;
import io.github.jsoagger.core.business.cloud.services.api.IClientAuthenticationApi;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class AuthenticationApi extends AbstractClientApi implements IClientAuthenticationApi {

  private static final String API_AUTHENTICATION_LOGIN = "/transdev/security/v1/anon/auth/login";
  private static final String API_LOST_PASS = "/transdev/security/v1/anon/auth/lostPassword?login=%s";
  private static final String API_AUTHENTICATION_LOGOUT = "/transdev/security/v1/secured/api/auth/logout";
  private static final String API_RESEND_LOCKTOKEN = "/transdev/security/v1/secured/api//auth/%s/resendLockToken/?containerId=%s";
  private static final String API_SET_TECHNICAL_STATE = "/transdev/security/v1/secured/api/auth/setSystemLocked?login=%s";
  private static final String API_ACTIVATE_ACCOUNT = "/transdev/security/inactive/auth/activateAccount?login=%s";
  private static final String MY_ACTIVATION_TOKEN = "/transdev/security/inactive/auth/myActivationToken";

  /**
   * Constructor
   */
  public AuthenticationApi() {
    super();
  }


  @Override
  public IOperationResult activationToken(JsonObject query) {
    try {
      String loginUrl = getRootUrl().concat(MY_ACTIVATION_TOKEN);
      IOperationResult result = doGet(query, loginUrl, SingleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public boolean login(JsonObject query) {
    try {
      String loginUrl = getRootUrl().concat(API_AUTHENTICATION_LOGIN);
      return doPostLogin(query, loginUrl);
    } catch (Exception e) {
      return false;
    }
  }

  @Override
  public IOperationResult logout(JsonObject query) {
    try {
      String url = getRootUrl().concat(API_AUTHENTICATION_LOGOUT);
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult lostPassword(JsonObject query) {
    try {
      String url = getRootUrl().concat(API_LOST_PASS);
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult setSystemLocked(JsonObject query) {
    try {
      String login = query.get("login").getAsString();
      String url = getRootUrl().concat(String.format(API_SET_TECHNICAL_STATE, login));
      IOperationResult result = doPut(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult resendLockToken(JsonObject query) {
    try {
      String login = query.get("login").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(API_RESEND_LOCKTOKEN, login, containerId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult activateAccount(JsonObject query) {
    try {
      String login = query.get("login").getAsString();
      String password = query.get("password").getAsString();
      String token = query.get("token").getAsString();
      String url = getRootUrl().concat(String.format(API_ACTIVATE_ACCOUNT, login));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }
}
