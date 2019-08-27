/**
 * 2 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.services.api;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 2 févr. 2018
 */
public interface IGlobalAttributesApi {

	IOperationResult getAllGlobalAttributes(JsonObject query);


	IOperationResult getGlobalAttributesByType(JsonObject query);


	IOperationResult getGlobalAttributesByLogicalName(JsonObject query);


	IOperationResult countGlobalAttributes(JsonObject query);


	IOperationResult getGlobalAttributesByOid(JsonObject query);
}
