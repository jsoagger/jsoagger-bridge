/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IUserPrincipalApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class UserPrincipalApi extends AbstractClientApi implements IUserPrincipalApi {

  private static final String	account_current_URL		         = "/api/account/current/?containerId=%s";
  private static final String   account_current_update_pass_URL  = "/api/account/%s/updatePassword/?containerId=%s";

  private static final String   account_of_Party_current_URL     = "/api/account/ofParty/?partyId=%s&containerId=%s";

  private static final String	account_details_URL		        = "/api/account/%s/details/?containerId=%s";
  private static final String   account_update_Mail_URL         = "/api/account/%s/updateMail/?email=%s&containerId=%s";
  private static final String   account_owner_URL               = "/api/account/%s/owner/?containerId=%s";
  private static final String	account_byloginLike_URL	        = "/api/account/byLoginLike/?login=%s&containerId=%s";
  private static final String	account_bylogin_URL		         = "/api/account/byLogin/?login=%s&containerId=%s";
  private static final String	account_byname_URL		          = "/api/account/byNameLike/?name=%s&containerId=%s";
  private static final String	account_resetPassword_URL		= "/api/account/%s/resetPassword/?containerId=%s";

  private static final String	account_lock_URL	   = "/api/account/%s/lock/?containerId=%s";
  private static final String   account_unlocked_URL   = "/api/account/%s/unlock/?lockToken=%s&containerId=%s";

  private static final String   account_roles_URL       = "/api/account/%s/roles/?containerId=%s";
  private static final String   account_roles_add_remove_URL       = "/api/account/%s/roles/%s/?containerId=%s";

  private static final String   account_rootFolder_URL   = "/api/account/%s/rootFolder/?containerId=%s";
  private static final String   account_permissions_URL   = "/api/account/%s/permissions/?containerId=%s&includeParentItems=%s";


  private static final String	getGroupByName				= "/api/userGroup/byName?internalName=%s&containerId=%s";
  private static final String	deleteGroup_URL				= "/api/userGroup/%s/?containerId=%s";
  private static final String	childrenOf_URL				= "/api/userGroup/%s/children/?containerId=%s";
  private static final String	members_URL					= "/api/userGroup/%s/members/?containerId=%s";
  private static final String	members_count_URL			= "/api/userGroup/%s/members/count/?containerId=%s";
  private static final String	members_remove_URL			= "/api/userGroup/%s/members/?containerId=%s";
  private static final String	members_add_URL				= "/api/userGroup/%s/members/?containerId=%s";

  private static final String   SWITCH_CONTAINER_CONTEXT      = "/api/account/%s/switchToContext?containerId=%s";



  @Override
  public IOperationResult addRoleToAccount(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();
      String roleId = query.get("roleId").getAsString();

      String url = getRootUrl().concat(String.format(account_roles_add_remove_URL, id, roleId, containerId));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult removeRoleFromAccount(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();
      String roleId = query.get("roleId").getAsString();

      String url = getRootUrl().concat(String.format(account_roles_add_remove_URL, id, roleId, containerId));
      IOperationResult result = doDelete(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult getAccountRoles(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(account_roles_URL, id, containerId));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }



  @Override
  public IOperationResult permissions(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();
      String includeParentItems = query.get("includeParentItems").getAsString();

      String url = getRootUrl().concat(String.format(account_permissions_URL, id, containerId, includeParentItems));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }



  @Override
  public IOperationResult rootFolder(JsonObject query) {
    try {
      String containerId = query.get("containerId").getAsString();
      String id = getFullId(query);

      String url = getRootUrl().concat(String.format(account_rootFolder_URL, id, containerId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult accountOfParty(JsonObject query) {
    try {
      String containerId = query.get("containerId").getAsString();
      String partyId = query.get("partyId").getAsString();

      String url = getRootUrl().concat(String.format(account_of_Party_current_URL, partyId, containerId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult switchToContext(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(SWITCH_CONTAINER_CONTEXT, id, containerId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult getGroupByName(JsonObject query) {
    try {
      String internalName = query.get("internalName").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(getGroupByName, internalName, containerId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult deleteGroup(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();
      String url = getRootUrl().concat(String.format(deleteGroup_URL, id));
      IOperationResult result = doDelete(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult getChildrenOf(JsonObject query) {
    try {
      String fullId = query.get("fullId").getAsString();
      String url = getRootUrl().concat(String.format(childrenOf_URL, fullId));
      IOperationResult result = doDelete(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult addMembersInGroupOperation(JsonObject query) {
    try {
      String fullId = query.get("fullId").getAsString();
      query.get("members");

      String url = getRootUrl().concat(String.format(members_add_URL, fullId));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult countGroupsMembersOperation(JsonObject query) {
    try {
      String fullId = query.get("fullId").getAsString();
      String url = getRootUrl().concat(String.format(members_count_URL, fullId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult getGroupMembersOperation(JsonObject query) {
    try {
      String id = getFullId(query);
      String url = getRootUrl().concat(String.format(members_URL, id));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }


  @Override
  public IOperationResult removeMembersFromGroup(JsonObject query) {
    try {
      String id = getFullId(query);
      String url = getRootUrl().concat(String.format(members_remove_URL, id));
      IOperationResult result = doDelete(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  // ---------------------------------------------------------------------------------------------------
  // ---------------------------------------------------------------------------------------------------
  @Override
  public IOperationResult getUsersInRole(JsonObject query) {
    return null;
  }


  // ---------------------------------------------------------------------------------------------------
  // ---------------------------------------------------------------------------------------------------
  @Override
  public IOperationResult getAccountByLoginLike(JsonObject query) {
    try {
      String login = query.get("login").getAsString();
      String containerId = query.get("containerId").getAsString();

      int pageSize = 5;
      String url = getRootUrl().concat(String.format(account_byloginLike_URL, login, pageSize, containerId));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }


  @Override
  public IOperationResult getAccountByLogin(JsonObject query) {
    try {
      String login = query.get("login").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(account_bylogin_URL, login, containerId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult getAccountByNameLike(JsonObject query) {
    try {
      String name = query.get("name").getAsString();
      String containerId = query.get("containerId").getAsString();
      String url = getRootUrl().concat(String.format(account_byname_URL, name, containerId));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }


  @Override
  public IOperationResult updatePassword(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();
      String newPassword = query.get("newPassword").getAsString();
      String oldPassword = query.get("oldPassword").getAsString();

      String url = getRootUrl().concat(String.format(account_current_update_pass_URL, id, containerId));
      IOperationResult result = doPut(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult getCurrentUserAccount(JsonObject query) {
    try {
      int tries = 0;
      try {
        String containerId = query.get("containerId").getAsString();
        String url = getRootUrl().concat(String.format(account_current_URL, containerId));

        IOperationResult result = doGet(new JsonObject(), url, SingleResult.class);
        return result;
      }
      catch (Exception e) {
        if (tries < 2) {
          tries++;
          Thread.sleep(2000);
          return getCurrentUserAccount(query);
        }
      }

      return new SingleResult();
    }
    catch (Throwable e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult getUserDetails(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(account_details_URL, id, containerId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult resetUserPassword(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(account_resetPassword_URL, id, containerId));
      IOperationResult result = doPut(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult setUserAccountLocked(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(account_lock_URL, id, containerId));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult setUserAccountUnLocked(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();
      String lockToken = query.get("lockToken").getAsString();

      String url = getRootUrl().concat(String.format(account_unlocked_URL, id, lockToken, containerId));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult getAccountOwnerByLogin(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(account_owner_URL, id, containerId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult updateUserEmail(JsonObject query) {
    try {
      String id = getFullId(query);
      String email = query.get("email").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(account_update_Mail_URL, id, email, containerId));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }
}
