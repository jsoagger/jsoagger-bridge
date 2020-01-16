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
public interface IOrganizationApi {

  IOperationResult createOrganization(JsonObject query);

  IOperationResult getByNameLike(JsonObject query);

  IOperationResult getBySiretEquals(JsonObject query);

  IOperationResult rename(JsonObject params);
}
