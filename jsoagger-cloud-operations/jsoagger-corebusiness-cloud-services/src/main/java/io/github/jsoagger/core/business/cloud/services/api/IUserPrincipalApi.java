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
public interface IUserPrincipalApi {

  public IOperationResult rootFolder(JsonObject query);

  public IOperationResult accountOfParty(JsonObject query) ;

  public IOperationResult getAccountByLoginLike(JsonObject query);

  public IOperationResult addRoleToAccount(JsonObject query);
  public IOperationResult removeRoleFromAccount(JsonObject query);
  public IOperationResult getAccountRoles(JsonObject query);

  public IOperationResult getAccountByLogin(JsonObject query);


  public IOperationResult getAccountByNameLike(JsonObject query);


  public IOperationResult getCurrentUserAccount(JsonObject query);


  public IOperationResult getUserDetails(JsonObject query);


  public IOperationResult resetUserPassword(JsonObject query);


  public IOperationResult setUserAccountLocked(JsonObject query);
  public IOperationResult setUserAccountUnLocked(JsonObject query);


  IOperationResult getUsersInRole(JsonObject query);
  public IOperationResult permissions(JsonObject query);

  public IOperationResult getAccountOwnerByLogin(JsonObject params);


  public IOperationResult updateUserEmail(JsonObject query);

  public IOperationResult switchToContext(JsonObject query);


  public IOperationResult addMembersInGroupOperation(JsonObject query);
  public IOperationResult countGroupsMembersOperation(JsonObject query);
  public IOperationResult getGroupMembersOperation(JsonObject query);
  public IOperationResult removeMembersFromGroup(JsonObject query);
  public IOperationResult getGroupByName(JsonObject query);
  public IOperationResult deleteGroup(JsonObject query);
  public IOperationResult getChildrenOf(JsonObject query);

  IOperationResult updatePassword(JsonObject query);
}
