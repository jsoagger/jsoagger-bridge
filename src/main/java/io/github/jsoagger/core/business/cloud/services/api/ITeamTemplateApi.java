/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.api;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 3 févr. 2018
 */
public interface ITeamTemplateApi {

	IOperationResult getTeamTemplatesInContainer(JsonObject query);


	IOperationResult getTeamTemplateClob(JsonObject query);


	IOperationResult populateContainerMembersFromTemplate(JsonObject query);


	IOperationResult deleteTeamTemplate(JsonObject query);


	IOperationResult getByFullId(JsonObject query);


	IOperationResult getByName(JsonObject query);


	IOperationResult getUsersInTemplate(JsonObject query);
}
