/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.api;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public interface IContainerApi {

  IOperationResult permissions(JsonObject query);
  IOperationResult getContainerByPath(JsonObject query);
  IOperationResult getContainer(JsonObject query);

  IOperationResult getContainerAdmins(JsonObject query);

  IOperationResult createChildContainer(JsonObject query);

  IOperationResult rootFolder(JsonObject query);

  IOperationResult members(JsonObject query) ;

  IOperationResult subContainers(JsonObject query);

  IOperationResult roles(JsonObject query) ;

  IOperationResult folderTemplates(JsonObject query);

  IOperationResult lifecycles(JsonObject query);

  IOperationResult rootLinkTypes(JsonObject query);

  IOperationResult rootTypes(JsonObject query);

  public IOperationResult getRulesIdentifier(JsonObject query) ;

  public IOperationResult getEnTemplates(JsonObject query);

}
