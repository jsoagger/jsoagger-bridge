/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.api;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana VONJISOA
 *
 */
public interface IRoleApi {


  IOperationResult getRole(JsonObject query);
  IOperationResult getRolePermissions(JsonObject query);

  IOperationResult getRoleByKey(JsonObject query);

  IOperationResult addPermissionToRole(JsonObject query);

  IOperationResult removePermissionToRole(JsonObject query);
}
