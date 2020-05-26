/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonObject;

import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;
import io.github.jsoagger.core.business.cloud.services.api.ICategoryApi;

/**
 * @author Ramilafananana VONJISOA
 *
 */
public class CategoryApi extends AbstractClientApi implements ICategoryApi {

  private static final String ROOT_CATEGORIES_URL = "/v1/secured/api/category/rootCategories?typeId=%s&ownerId=%s&containerId=%s";
  private static final String CATEGORY_BY_NUMBER_URL = "/v1/secured/api/category/byNumber/?number=%s&containerId=%s";
  private static final String CATEGORY_BY_NAME_URL = "/v1/secured/api/category/byName/?name=%s&typeId=%s&ownerId=%s&containerId=%s";
  private static final String CHILDREN_URL = "/v1/secured/api/category/%s/children/?containerId=%s";
  private static final String PARENT_URL = "/v1/secured/api/category/%s/parent/?containerId=%s";
  private static final String GET_URL = "/v1/secured/api/category/%s/?containerId=%s";

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult deleteCategoryByFullId(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(GET_URL, fullId, containerId));
      IOperationResult result = doDelete(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult getCategoryByFullId(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(GET_URL, fullId, containerId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult getCategoryByName(JsonObject query) {
    try {
      String name = query.get("name").getAsString();
      String typeId = query.get("typeId").getAsString();
      String ownerId = query.get("ownerId").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(CATEGORY_BY_NAME_URL, name, typeId, ownerId, containerId));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult getChildrenCategoriesByFullId(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(CHILDREN_URL, fullId, containerId));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult getParentCategoryByFullId(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(PARENT_URL, fullId, containerId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult getCategoryByNumber(JsonObject query) {
    try {
      String number = query.get("number").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(CATEGORY_BY_NUMBER_URL, number, containerId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult getRootCategories(JsonObject query) {
    try {
      String typeId = query.get("typeId").getAsString();
      String ownerId = query.get("ownerId").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(ROOT_CATEGORIES_URL, typeId, ownerId, containerId));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }

}
