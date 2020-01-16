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
public interface IObjectCatalogApi {

  IOperationResult getObjectCatalogById(JsonObject query);

  IOperationResult getObjectCatalogs(JsonObject query);

  IOperationResult createObjectCatalog(JsonObject query);

  IOperationResult deleteObjectCatalog(JsonObject query);

  IOperationResult getObjectCatalogRootCategories(JsonObject query);

  IOperationResult assignRootCategory(JsonObject params);
}
