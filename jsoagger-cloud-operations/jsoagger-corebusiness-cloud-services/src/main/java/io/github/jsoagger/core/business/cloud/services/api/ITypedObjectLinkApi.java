/**
 * 24 janv. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.services.api;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 24 janv. 2018
 */
public interface ITypedObjectLinkApi {


  /**
   * Get all 'ADD_TYPEDOBJECT_LINK_CONSTRAINT_AGREGATION' from a link type.
   *
   * @param query
   * @return
   */
  public IOperationResult linkConstraints(JsonObject query);

  public IOperationResult countRoleBs(JsonObject query);

  public IOperationResult getUsages(JsonObject query);


  public IOperationResult getAllRoleBs(JsonObject query);


  /**
   * When navigating normal entities or masters.
   *
   * @param query
   * @return
   */
  public IOperationResult getPaginatedRoleBs(JsonObject query);


  /**
   * When navigating iterations, and need to fetch master with each of them
   *
   * @param query
   * @return
   */
  public IOperationResult getPaginatedRoleBsIterations(JsonObject query);


  public IOperationResult createLink(JsonObject query);


  public IOperationResult getRoleBsWithLinksOperation(JsonObject query);


  public IOperationResult getPaginatedRoleBsWithLinksOperation(JsonObject query);

}
