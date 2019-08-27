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
public interface IBatchApi {

  /**
   *
   * @param query
   * @return {@link IOperationResult}
   */
  IOperationResult batchLoad(JsonObject query);
  IOperationResult batchExport(JsonObject query);
}
