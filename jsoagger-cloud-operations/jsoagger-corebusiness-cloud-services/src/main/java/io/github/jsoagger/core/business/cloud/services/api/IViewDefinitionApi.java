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
public interface IViewDefinitionApi {

  public IOperationResult getAllByPlatform(JsonObject query);
  public IOperationResult getById(JsonObject query);
  public IOperationResult getByInternalName(JsonObject query);
  public IOperationResult delete(JsonObject query);
  public IOperationResult update(JsonObject query);

  public IOperationResult jsonSchema(JsonObject query);
  public IOperationResult uiSchema(JsonObject query);
  public IOperationResult allSchemas(JsonObject query);
}
