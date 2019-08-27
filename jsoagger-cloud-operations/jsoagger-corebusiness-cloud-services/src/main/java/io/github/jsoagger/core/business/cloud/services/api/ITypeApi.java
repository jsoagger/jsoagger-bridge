/**
 * 20 janv. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.services.api;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 20 janv. 2018
 */
public interface ITypeApi {


  public IOperationResult subtypes(JsonObject query);


  public IOperationResult instanciableSubtypes(JsonObject query);


  public IOperationResult softAttributesDefinition(JsonObject query);


  public IOperationResult byPath(JsonObject query);


  public IOperationResult linkableRoleBs(JsonObject query);


  public IOperationResult byLogicalName(JsonObject query);


  public IOperationResult setLifecycle(JsonObject query);

  public IOperationResult getLifecycle(JsonObject query);
}
