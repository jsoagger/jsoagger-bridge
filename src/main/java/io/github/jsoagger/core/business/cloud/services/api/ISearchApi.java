/**
 * 27 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.services.api;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 27 févr. 2018
 */
public interface ISearchApi {

	IOperationResult rcSearch(JsonObject query);


	IOperationResult entitySearch(JsonObject query);


	IOperationResult createSavedSearch(JsonObject query);


	IOperationResult deleteSavedSearch(JsonObject query);


	IOperationResult getAllSavedSearch(JsonObject query);


	IOperationResult getPublicSavedSearch(JsonObject query);


	IOperationResult getUserSavedSearch(JsonObject query);

}
