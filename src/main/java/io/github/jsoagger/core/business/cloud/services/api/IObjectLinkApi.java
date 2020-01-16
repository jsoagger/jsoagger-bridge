/**
 * 25 janv. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.services.api;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 25 janv. 2018
 */
public interface IObjectLinkApi {

	IOperationResult countRoleBs(JsonObject query);


	IOperationResult getAllRoleBs(JsonObject query);


	IOperationResult getAllRoleBsWithLinks(JsonObject query);


	IOperationResult getPaginatedRoleBs(JsonObject query);


	IOperationResult getPaginatedRoleAs(JsonObject query);


	public IOperationResult isLinked(JsonObject query);


	public IOperationResult deleteLink(JsonObject query);


	IOperationResult createLink(JsonObject query);

}