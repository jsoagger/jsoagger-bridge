/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IProductInstanceApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana VONJISOA
 *
 */
@SuppressWarnings("exports")
public class ProductInstanceApi extends AbstractClientApi implements IProductInstanceApi {


  private static final String   getProduct_byid_ULR                   = "/api/productInstance/%s/?containerId=%s";
  private static final String   getProducts_bynumber_ULR                   = "/api/productInstance/byNumber/?containerId=%s&number=%s";
  private static final String   getProducts_ULR                   = "/api/productInstance/?containerId=%s&o=%s&c=%s&page=%s&pageSize=%s&s=%s";
  private static final String   updateDiscontinuationDate_ULR     = "/api/productInstance/%s/discontinuationDate/?containerId=%s";
  private static final String   setProductMasterCategory_ULR      = "/api/productInstance/%s/masterCategory/?c=%s&containerId=%s";
  private static final String   getCategoriesOf_ULR             = "/api/productInstance/%s/categories/?containerId=%s";
  private static final String   manageCategoriesOf_ULR          = "/api/productInstance/%s/categories/?categoryId=%s&containerId=%s";


  @Override
  public IOperationResult getProductById(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(getProduct_byid_ULR, id,containerId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult getProductByNumber(JsonObject query) {
    try {
      String number = query.get("number").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(getProducts_bynumber_ULR, containerId, number));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  @Override
  public IOperationResult getProducts(JsonObject query) {
    try {

      // container is mandatory
      String containerId = query.get("containerId").getAsString();

      // owner is optional
      JsonElement owner = query.get("ownerId");
      String ownerId = null;
      if(owner != null) {
        ownerId = owner.getAsString();
      }

      JsonElement category = query.get("categoryId");
      String categoryId = null;
      if(category != null) {
        categoryId = category.getAsString();
      }

      JsonElement states = query.get("states");
      String statesString = null;
      if(states != null) {
        statesString = states.getAsString();
      }

      String page = query.get("page").getAsString();
      String pageSize = query.get("pageSize").getAsString();

      String url = getRootUrl().concat(String.format(getProducts_ULR,containerId, ownerId, categoryId, page, pageSize, statesString));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.emptyMultipleResult();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult updateProduct(JsonObject params) {
    try {
      String id = getFullId(params);
      String containerId = params.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(getProducts_ULR,id, containerId));
      IOperationResult result = doGet(params, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult updateProductDiscontinuationDate(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();
      query.get("discontinuationDate").getAsString();
      query.get("supportDiscontinuationDate").getAsString();
      query.get("introductionDate").getAsString();

      String url = getRootUrl().concat(String.format(updateDiscontinuationDate_ULR, id, containerId));
      IOperationResult result = doPut(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult setProductMasterCategory(JsonObject query) {
    try {
      String id = getFullId(query);
      String categoryId = query.get("categoryId").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(setProductMasterCategory_ULR,id, categoryId,containerId));
      IOperationResult result = doPut(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult addProductToCategory(JsonObject query) {
    try {
      String id = getFullId(query);
      String categoryId = query.get("categoryId").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(manageCategoriesOf_ULR, id, categoryId, containerId));
      IOperationResult result = doPut(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult getCategoriesOf(JsonObject query) {
    try {
      String id = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(getCategoriesOf_ULR, id, containerId));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult deleteFromCategory(JsonObject query) {
    try {
      String id = getFullId(query);
      String categoryId = query.get("categoryId").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(manageCategoriesOf_ULR,id, categoryId, containerId));
      IOperationResult result = doDelete(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }
}
