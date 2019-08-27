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
public interface IPersistableApi {

	public IOperationResult updateAttributes(JsonObject query);


	public IOperationResult loadSimpleModel(JsonObject query);


	public IOperationResult loadBasicModel(JsonObject query);


	public IOperationResult loadBasicRCModel(JsonObject query);


	public IOperationResult paginateEntity(JsonObject params);


	public IOperationResult paginateRc(JsonObject params);
}
