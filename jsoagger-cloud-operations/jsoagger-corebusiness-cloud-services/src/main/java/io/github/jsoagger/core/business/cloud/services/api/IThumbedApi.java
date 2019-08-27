/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.api;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public interface IThumbedApi {

	public byte[] getThumb(JsonObject query);


	public byte[] getIllustration(JsonObject query);


	public IOperationResult contentFormat(JsonObject query);
}
