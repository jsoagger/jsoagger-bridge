/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IRoleApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana VONJISOA
 *
 */
public class RoleApi extends AbstractClientApi implements IRoleApi {


  private static final String   getRoleByKey                = "/api/role/byKey/?containerId=%s&roleKey=%s";
  private static final String   getRole                     = "/api/role/%s/?containerId=%s";
  private static final String   getRolePermissions              = "/api/role/%s/permissions/?containerId=%s";
  private static final String   add_remove_PermissionToRole     = "/api/role/%s/permissions/%s/?containerId=%s";


  @Override
  public IOperationResult getRole(JsonObject query) {
    try {
      final String id = getFullId(query);
      final String containerId = query.get("containerId").getAsString();
      final String byPathUrl = getRootUrl().concat(String.format(getRole, id, containerId));
      final IOperationResult result = doGet(query, byPathUrl, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult getRoleByKey(JsonObject query) {
    try {
      final String containerId = query.get("containerId").getAsString();
      final String roleKey = query.get("roleKey").getAsString();

      final String byPathUrl = getRootUrl().concat(String.format(getRoleByKey, containerId, roleKey));
      final IOperationResult result = doGet(query, byPathUrl, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult getRolePermissions(JsonObject query) {
    try {
      final String id = getFullId(query);
      final String containerId = query.get("containerId").getAsString();

      final String byPathUrl = getRootUrl().concat(String.format(getRolePermissions, id, containerId));
      final IOperationResult result = doGet(query, byPathUrl, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }

  @Override
  public IOperationResult addPermissionToRole(JsonObject query) {
    try {
      final String id = getFullId(query);
      final String containerId = query.get("containerId").getAsString();
      final String permissionId = query.get("permissionId").getAsString();

      final String byPathUrl = getRootUrl().concat(String.format(add_remove_PermissionToRole, id, permissionId, containerId));
      final IOperationResult result = doPost(query, byPathUrl, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult removePermissionToRole(JsonObject query) {
    try {
      final String id = getFullId(query);
      final String containerId = query.get("containerId").getAsString();
      final String permissionInstanceId = query.get("permissionId").getAsString();

      final String byPathUrl = getRootUrl().concat(String.format(add_remove_PermissionToRole, id, permissionInstanceId, containerId));
      final IOperationResult result = doDelete(query, byPathUrl, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

}
