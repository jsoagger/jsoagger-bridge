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
public interface ITypeManagedApi {

	/**
	 * Get all dynamical attributes definitions of the given type managed object
	 * 
	 * @param query
	 * @return
	 */
	public IOperationResult attributeDefinitions(JsonObject query) ;

	/**
	 * @param query
	 * @return
	 */
	public IOperationResult typeOf(JsonObject query);
}
