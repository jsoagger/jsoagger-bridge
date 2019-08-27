/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.api;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana VONJISOA
 *
 */
public interface IShoppingBasketApi {


  IOperationResult cleanProductsInBasket(JsonObject query);

  IOperationResult getAllProductsInBasket(JsonObject query);

  IOperationResult getShoppingBasketofUser(JsonObject query);

  IOperationResult removeProductFromBasket(JsonObject query);

  IOperationResult setProductQuantityInBasket(JsonObject query);

  public IOperationResult getProductQuantityInBasket(JsonObject query);
}
