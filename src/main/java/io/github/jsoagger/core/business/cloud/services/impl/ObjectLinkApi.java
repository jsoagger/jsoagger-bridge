/**
 * 24 janv. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;


import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IObjectLinkApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 24 janv. 2018
 */
public class ObjectLinkApi extends AbstractClientApi implements IObjectLinkApi {

  private static final String	CREATE_LINK_ULR			 = "/jsoagger/base/v1/secured/api/objectLink/";
  private static final String	DELETE_LINK				 = "/jsoagger/base/v1/secured/api/objectLink/%s/deleteLink/%s/?linkClass=%s&containerId=%s";
  private static final String	IS_LINKED				 = "/jsoagger/base/v1/secured/api/objectLink/%s/isLinked/%s/?linkClass=%s&containerId=%s";
  private static final String	GET_ROLEBs_ULR			 = "/jsoagger/base/v1/secured/api/objectLink/%s/roleBs/?linkClass=%s&containerId=%s";
  private static final String	GET_ROLEBs_WITH_LINK_ULR = "/jsoagger/base/v1/secured/api/objectLink/%s/roleBsWithLinks/?linkClass=%s&containerId=%s";
  private static final String	GET_PAGINATED_ROLEBs_ULR = "/jsoagger/base/v1/secured/api/objectLink/%s/paginatedRoleBs/?linkClass=%s&pageSize=%s&page=%s&containerId=%s";
  private static final String	GET_PAGINATED_ROLEAs_ULR = "/jsoagger/base/v1/secured/api/objectLink/%s/paginatedRoleAs/?linkClass=%s&pageSize=%s&page=%s&containerId=%s";
  private static final String	COUNT_ROLEBs_URL		 = "/jsoagger/base/v1/secured/api/objectLink/%s/countRoleBs?&linkClass=%s&containerId=%s";


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult createLink(JsonObject query) {
    try {
      String linkClass = query.get("linkClass").getAsString();

      String roleA = query.get("roleA").getAsString();
      String roleB = query.get("roleB").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(CREATE_LINK_ULR));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult isLinked(JsonObject query) {
    try {
      String roleAOid = query.get("roleA").getAsString();
      String roleBOid = query.get("roleB").getAsString();
      String linkClass = query.get("linkClass").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(IS_LINKED, roleAOid, roleBOid, linkClass, containerId));
      IOperationResult result = doGet(null, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult deleteLink(JsonObject query) {
    try {
      String roleAOid = query.get("roleA").getAsString();
      String roleBOid = query.get("roleB").getAsString();
      String linkClass = query.get("linkClass").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(DELETE_LINK, roleAOid, roleBOid, linkClass, containerId));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult countRoleBs(JsonObject query) {
    try {
      String oid = getFullId(query);
      String linkClass = query.get("linkClass").getAsString();
      String containerId = query.get("containerId").getAsString();

      String byOidUrl = getRootUrl().concat(String.format(COUNT_ROLEBs_URL, oid, linkClass, containerId));
      IOperationResult result = doGet(null, byOidUrl, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getAllRoleBs(JsonObject query) {
    try {
      String oid = getFullId(query);
      String linkClass = query.get("linkClass").getAsString();
      String containerId = query.get("containerId").getAsString();

      String byOidUrl = getRootUrl().concat(String.format(GET_ROLEBs_ULR, oid, linkClass, containerId));
      IOperationResult result = doGet(null, byOidUrl, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getAllRoleBsWithLinks(JsonObject query) {
    try {
      String oid = getFullId(query);
      String linkClass = query.get("linkClass").getAsString();
      String containerId = query.get("containerId").getAsString();

      String byOidUrl = getRootUrl().concat(String.format(GET_ROLEBs_WITH_LINK_ULR, oid, linkClass, containerId));
      IOperationResult result = doGet(null, byOidUrl, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getPaginatedRoleBs(JsonObject query) {
    try {
      String oid = getFullId(query);
      String linkClass = query.get("linkClass").getAsString();
      String containerId = query.get("containerId").getAsString();
      Integer page = query.get("page").getAsInt();
      Integer pageSize = query.get("pageSize").getAsInt();

      String byOidUrl = getRootUrl().concat(String.format(GET_PAGINATED_ROLEBs_ULR, oid, linkClass, pageSize.toString(), page.toString(), containerId));
      IOperationResult result = doGet(null, byOidUrl, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getPaginatedRoleAs(JsonObject query) {
    try {
      String oid = getFullId(query);
      String linkClass = query.get("linkClass").getAsString();
      String containerId = query.get("containerId").getAsString();
      Integer page = query.get("page").getAsInt();
      Integer pageSize = query.get("pageSize").getAsInt();

      String byOidUrl = getRootUrl().concat(String.format(GET_PAGINATED_ROLEAs_ULR, oid, linkClass, pageSize.toString(), page.toString(), containerId));
      IOperationResult result = doGet(null, byOidUrl, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }
}
