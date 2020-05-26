/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;


import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IPersistableApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.operation.JsonUtils;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class PersistableApi extends AbstractClientApi implements IPersistableApi {

  private static final String	UPDATE_ATTRIBUTES_URI	= "/v1/secured/api/persistable/%s/updateAttributes/?containerId=%s";
  private static final String	LOAD_SIMPLE_MODEL_URI	= "/v1/secured/api/persistable/%s/loadSimpleModel/?containerId=%s";
  private static final String	LOAD_BASIC_MODEL_URI	= "/v1/secured/api/persistable/%s/loadBasicModel/?containerId=%s";
  private static final String	LOAD_BASIC_RC_MODEL_URI	= "/v1/secured/api/persistable/%s/loadBasicRCModel/?containerId=%s";
  private static final String	PAGINATE_ENTITY_URI		= "/v1/secured/api/persistable/%s/paginateEntity?page=%s&pageSize=%s&sort=%s&audience=%s&containerOid=%s&queryPredicate=%s";
  private static final String	PAGINATE_RC_URI			= "/v1/secured/api/persistable/%s/paginateRc?page=%s&pageSize=%s&sort=%s&audience=%s&containerOid=%s&queryPredicate=%s";


  /**
   * Constructor
   */
  public PersistableApi() {
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult updateAttributes(JsonObject query) {
    try {
      String fullId = query.get("hidden.persistence.fullId").getAsString();
      String populateUrl = getRootUrl().concat(String.format(UPDATE_ATTRIBUTES_URI, fullId));
      IOperationResult result = doPost(query, populateUrl, SingleResult.class);
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
  public IOperationResult loadSimpleModel(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String containerId = query.get("containerId").getAsString();
      String loadUrl = getRootUrl().concat(String.format(LOAD_SIMPLE_MODEL_URI, fullId, containerId));
      IOperationResult result = doGet(query, loadUrl, SingleResult.class);
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
  public IOperationResult loadBasicModel(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String loadUrl = getRootUrl().concat(String.format(LOAD_BASIC_MODEL_URI, fullId));
      IOperationResult result = doGet(query, loadUrl, SingleResult.class);
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
  public IOperationResult loadBasicRCModel(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String loadUrl = getRootUrl().concat(String.format(LOAD_BASIC_RC_MODEL_URI, fullId));
      IOperationResult result = doGet(query, loadUrl, SingleResult.class);
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
  public IOperationResult paginateEntity(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String containerOid = query.get("containerOid").getAsString();

      Integer page = JsonUtils.getJsonInt(query, "page", 0);
      Integer pageSize = JsonUtils.getJsonInt(query, "pageSize", 5);
      String sort = JsonUtils.getJsonString(query, "sort", "");
      String audience = JsonUtils.getJsonString(query, "audience", "");
      String queryPredicate = JsonUtils.getJsonString(query, "queryPredicate", "");

      String url = getRootUrl().concat(String.format(PAGINATE_ENTITY_URI, fullId, page, pageSize, sort, audience, containerOid, queryPredicate));
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
  public IOperationResult paginateRc(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String containerOid = query.get("containerOid").getAsString();
      Integer page = JsonUtils.getJsonInt(query, "page", 0);
      Integer pageSize = JsonUtils.getJsonInt(query, "pageSize", 5);
      String sort = JsonUtils.getJsonString(query, "sort", "");
      String audience = JsonUtils.getJsonString(query, "audience", "");
      String queryPredicate = JsonUtils.getJsonString(query, "queryPredicate", "");

      String url = getRootUrl().concat(String.format(PAGINATE_RC_URI, fullId, page, pageSize, sort, audience, containerOid, queryPredicate));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }
}
