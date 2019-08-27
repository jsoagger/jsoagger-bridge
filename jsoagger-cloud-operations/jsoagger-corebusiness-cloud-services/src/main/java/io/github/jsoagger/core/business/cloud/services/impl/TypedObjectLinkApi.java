/**
 * 24 janv. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;


import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.ITypedObjectLinkApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.operation.JsonUtils;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 24 janv. 2018
 */
public class TypedObjectLinkApi extends AbstractClientApi implements ITypedObjectLinkApi {

  private static final String	CREATE_LINK_ULR	= "/api/typedObjectLink/%s/?roleB=%s&linkConstraintName=%s&linkClass=%s&linkType=%s";
  private static final String	COUNT_ROLEBs_URL	= "/api/typedObjectLink/%s/countRoleBs?linkConstraintName=%s&linkClass=%s";
  private static final String	USAGES_URL			= "/api/typedObjectLink/%s/usages/?page=%s&pageSize=%s&sort=%s&audience=%s&containerOid=%s&queryPredicate=%s&linkClass=%s";
  private static final String   GET_ROLEBs_ULR = "/api/typedObjectLink/%s/roleBs/?linkConstraintName=%s&linkClass=%s";
  private static final String	ROLEBS_WITH_LINKS_URL			= "/api/typedObjectLink/%s/roleBsWithLinks/?page=%s&pageSize=%s&sort=%s&audience=%s&containerOid=%s&queryPredicate=%s&linkClass=%s";
  private static final String	PAGINATED_ROLEBS_WITH_LINKS_URL	= "/api/typedObjectLink/%s/paginatedRoleBsWithLinks/?page=%s&pageSize=%s&sort=%s&audience=%s&containerOid=%s&queryPredicate=%s&linkClass=%s&linkConstraintName=%s";

  private static final String	GET_PAGINATED_ROLEBs_ULR_			= "/api/typedObjectLink/%s/paginatedRoleBs/?linkConstraintName=%s&linkClass=%s&pageSize=%s&page=%s";
  private static final String	GET_PAGINATED_ROLEBs_ULR_ITERATIONS	= "/api/typedObjectLink/%s/paginatedRoleBsIterations/?linkConstraintName=%s&linkClass=%s&pageSize=%s&page=%s";


  private static final String   LINK_CONSTRAINTS_URL   = "/api/typedObjectLink/%s/linkConstraints/?containerId=%s";


  /**
   * {@inheritDoc}
   */
  @Override
  public synchronized IOperationResult linkConstraints(JsonObject query) {
    try {
      final String oid = getFullId(query);
      final String containerId = query.get("containerId").getAsString();

      final String url = getRootUrl().concat(String.format(LINK_CONSTRAINTS_URL, oid, containerId));
      final IOperationResult result = doGet(null, url, MultipleResult.class);
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
  public IOperationResult countRoleBs(JsonObject query) {
    try {
      String oid = getFullId(query);
      String linkConstraintName = query.get("linkConstraintName").getAsString();
      String linkClass = query.get("linkClass").getAsString();
      String byOidUrl = getRootUrl().concat(String.format(COUNT_ROLEBs_URL, oid, linkConstraintName, linkClass));
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
      String linkConstraintName = query.get("linkConstraintName").getAsString();
      String linkClass = query.get("linkClass").getAsString();
      String byOidUrl = getRootUrl().concat(String.format(GET_ROLEBs_ULR, oid, linkConstraintName, linkClass));
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
      String linkConstraintName =  query.get("linkConstraintName").getAsString();
      String linkClass = query.get("linkClass").getAsString();

      Integer page = JsonUtils.getJsonInt(query,"page", 0);
      Integer pageSize = JsonUtils.getJsonInt(query,"pageSize", -1);

      String byOidUrl = getRootUrl().concat(String.format(GET_PAGINATED_ROLEBs_ULR_, oid, linkConstraintName, linkClass, pageSize.toString(), page.toString()));
      IOperationResult result = doGet(null, byOidUrl, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getPaginatedRoleBsIterations(JsonObject query) {
    try {
      String oid = getFullId(query);
      String linkConstraintName = query.get("linkConstraintName").getAsString();
      String linkClass = query.get("linkClass").getAsString();

      Integer page = JsonUtils.getJsonInt(query,"page", 0);
      Integer pageSize = JsonUtils.getJsonInt(query,"pageSize", -1);

      String byOidUrl = getRootUrl().concat(String.format(GET_PAGINATED_ROLEBs_ULR_ITERATIONS, oid, linkConstraintName, linkClass, pageSize.toString(), page.toString()));
      IOperationResult result = doGet(null, byOidUrl, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getUsages(JsonObject query) {
    try {
      String roleAFullId = getFullId(query);
      Object querySpec = JsonUtils.getJsonString(query, "querySpec");
      Object effectivity = JsonUtils.getJsonString(query, "effectivity");
      Object linkClass = JsonUtils.getJsonString(query, "linkClass");

      Integer page = JsonUtils.getJsonInt(query, "page", 0);
      Integer pageSize = JsonUtils.getJsonInt(query, "pageSize", -1);
      String sort = JsonUtils.getJsonString(query, "sort");
      String audience = JsonUtils.getJsonString(query,"audience");
      String queryPredicate = JsonUtils.getJsonString(query,"queryPredicate");

      String url = getRootUrl().concat(String.format(USAGES_URL, roleAFullId, page, pageSize, sort, audience, "", queryPredicate, linkClass));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getRoleBsWithLinksOperation(JsonObject query) {
    try {
      String roleAFullId = getFullId(query);

      //      Object querySpec = query.get("querySpec").getAsString();
      //      Object effectivity = query.get("effectivity").getAsString();

      Object linkClass = query.get("linkClass");

      Integer page = JsonUtils.getJsonInt(query,"page", 0);
      Integer pageSize = JsonUtils.getJsonInt(query,"pageSize", 0);
      String sort = JsonUtils.getJsonString(query,"sort", "");
      String audience = JsonUtils.getJsonString(query,"page", "0");

      String queryPredicate = JsonUtils.getJsonString(query,"queryPredicate", "0");

      String url = getRootUrl().concat(String.format(ROLEBS_WITH_LINKS_URL, roleAFullId, page, pageSize, sort, audience, "", queryPredicate, linkClass));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult createLink(JsonObject query) {
    try {
      String linkConstraintName = query.get("linkConstraintName").getAsString();
      String linkClass = query.get("linkClass").getAsString();
      String linkType = query.get("linkType").getAsString();

      String roleA = query.get("roleA").getAsString();
      String roleB = query.get("roleB").getAsString();

      String url = getRootUrl().concat(String.format(CREATE_LINK_ULR, roleA, roleB, linkConstraintName, linkClass, linkType));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult getPaginatedRoleBsWithLinksOperation(JsonObject query) {
    try {
      String roleAFullId = getFullId(query);

      //Object querySpec = JsonUtils.getJsonString(query, "querySpec");
      //Object effectivity = JsonUtils.getJsonString(query, "effectivity");
      Object linkClass = JsonUtils.getJsonString(query, "linkClass");

      Integer page = JsonUtils.getJsonInt(query, "page", 0);
      Integer pageSize = JsonUtils.getJsonInt(query, "pageSize", -1);
      String sort = JsonUtils.getJsonString(query, "sort");
      String audience = JsonUtils.getJsonString(query, "audience");
      String queryPredicate = JsonUtils.getJsonString(query, "queryPredicate");
      Object linkConstraintName = JsonUtils.getJsonString(query, "linkConstraintName", "");

      String url = getRootUrl().concat(String.format(PAGINATED_ROLEBS_WITH_LINKS_URL, roleAFullId, page, pageSize, sort, audience, "", queryPredicate, linkClass, linkConstraintName));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }
}
