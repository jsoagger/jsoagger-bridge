/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IObjectCatalogApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;


/**
 * @author Ramilafananana VONJISOA
 *
 */
public class ObjectCatalogApi extends AbstractClientApi implements IObjectCatalogApi {


  static final String GET_BY_ID_URL = "/transdev/shopbase/v1/secured/api/objectCatalog/%s/?containerId=%s";
  static final String LIST_URL = "/transdev/shopbase/v1/secured/api/objectCatalog/?ownerId=%s&typeId=%s&containerId=%s&state=%s";
  static final String CREATE_URL = "/transdev/shopbase/v1/secured/api/objectCatalog/%s/?containerId=%s";
  static final String DELETE_URL = "/transdev/shopbase/v1/secured/api/objectCatalog/%s/?containerId=%s";
  static final String ROOT_CATEGORIES_URL = "/transdev/shopbase/v1/secured/api/objectCatalog/%s/rootCategories/?containerId=%s";
  static final String ASSIGNROOT_CATEGORIES_URL = "/transdev/shopbase/v1/secured/api/objectCatalog/%s/assignRootCategory/?rootCategoryId=%s&containerId=%s";

  @Override
  public IOperationResult getObjectCatalogById(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(GET_BY_ID_URL, id, containerId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult getObjectCatalogs(JsonObject query) {
    try {
      String containerId = query.get("containerId").getAsString();
      String ownerId = query.get("ownerId").getAsString();
      String typeId = query.get("typeId").getAsString();
      String state = query.get("state").getAsString();

      String url = getRootUrl().concat(String.format(LIST_URL, ownerId, typeId, containerId, state));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.emptyMultipleResult();
    }
  }

  @Override
  public IOperationResult createObjectCatalog(JsonObject query) {
    try {
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(CREATE_URL));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult deleteObjectCatalog(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(DELETE_URL, id, containerId));
      IOperationResult result = doDelete(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult getObjectCatalogRootCategories(JsonObject query) {
    try {
      String containerId = query.get("containerId").getAsString();
      String id = getFullId(query);

      String url = getRootUrl().concat(String.format(ROOT_CATEGORIES_URL, id, containerId));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.emptyMultipleResult();
    }
  }

  @Override
  public IOperationResult assignRootCategory(JsonObject query) {
    try {
      String rootCategoryId = query.get("rootCategoryId").getAsString();
      String containerId = query.get("containerId").getAsString();
      String id = getFullId(query);

      String url = getRootUrl().concat(String.format(ASSIGNROOT_CATEGORIES_URL, id, rootCategoryId, containerId));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.emptyMultipleResult();
    }
  }
}
