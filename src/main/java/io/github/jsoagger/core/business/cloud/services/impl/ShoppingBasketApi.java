/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IShoppingBasketApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana VONJISOA
 *
 */
@SuppressWarnings("exports")
public class ShoppingBasketApi extends AbstractClientApi implements IShoppingBasketApi {

  private static String GET_SB_URL = "/v1/secured/api/shoppingBasket/?ownerId=%s&containerId=%s";
  private static String GET_PRODUCTS_IN_SB_URL = "/v1/secured/api/shoppingBasket/%s/products?containerId=%s";
  private static String DELETE_PRODUCTS_IN_SB_URL = "/v1/secured/api/shoppingBasket/%s/products/%s?containerId=%s";
  private static String PRODUCTS_QUANTITY_IN_SB_URL = "/v1/secured/api/shoppingBasket/%s/products/%s/quantity/?quantity=%s&containerId=%s";
  private static String GET_PRODUCTS_QUANTITY_IN_SB_URL = "/v1/secured/api/shoppingBasket/%s/products/%s/quantity/?containerId=%s";

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult getAllProductsInBasket(JsonObject query) {
    try {
      String basketFullId = query.get("id").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(GET_PRODUCTS_IN_SB_URL, basketFullId, containerId));
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
  public IOperationResult cleanProductsInBasket(JsonObject query) {
    try {
      String basketFullId = query.get("id").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(GET_PRODUCTS_IN_SB_URL, basketFullId, containerId));
      IOperationResult result = doDelete(query, url, SingleResult.class);
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
  public IOperationResult getShoppingBasketofUser(JsonObject query) {
    try {
      String ownerId = query.get("ownerId").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(GET_SB_URL, ownerId, containerId));
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
  public IOperationResult removeProductFromBasket(JsonObject query) {
    try {
      String basketFullId = query.get("id").getAsString();
      String productId = query.get("productId").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(DELETE_PRODUCTS_IN_SB_URL, basketFullId, productId, containerId));
      IOperationResult result = doDelete(query, url, SingleResult.class);
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
  public IOperationResult setProductQuantityInBasket(JsonObject query) {
    try {
      String basketFullId = query.get("id").getAsString();
      String productId = query.get("productId").getAsString();
      String quantity = query.get("quantity").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(PRODUCTS_QUANTITY_IN_SB_URL, basketFullId, productId, quantity, containerId));
      IOperationResult result = doPost(query, url, SingleResult.class);
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
  public IOperationResult getProductQuantityInBasket(JsonObject query) {
    try {
      String basketFullId = query.get("id").getAsString();
      String productId = query.get("productId").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(GET_PRODUCTS_QUANTITY_IN_SB_URL, basketFullId, productId, containerId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

}
