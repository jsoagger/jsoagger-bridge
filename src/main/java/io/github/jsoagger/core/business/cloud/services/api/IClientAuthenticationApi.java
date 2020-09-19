/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.api;

import com.google.gson.JsonObject;

import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface IClientAuthenticationApi {

  boolean login(JsonObject query);
  IOperationResult activationToken(JsonObject query);
  IOperationResult logout(JsonObject query);

  IOperationResult lostPassword(JsonObject query);
  IOperationResult setSystemLocked(JsonObject query);
  IOperationResult resendLockToken(JsonObject query);
  IOperationResult activateAccount(JsonObject query);
}