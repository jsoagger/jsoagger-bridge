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
public interface IProductInstanceApi {

  IOperationResult getProductById(JsonObject query);
  IOperationResult getProductByNumber(JsonObject query);

  IOperationResult getProducts(JsonObject query);

  IOperationResult updateProductDiscontinuationDate(JsonObject query);

  IOperationResult setProductMasterCategory(JsonObject query);

  IOperationResult addProductToCategory(JsonObject query);

  IOperationResult getCategoriesOf(JsonObject query);

  IOperationResult deleteFromCategory(JsonObject query);

  IOperationResult updateProduct(JsonObject params);
}
