/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.general;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.operations.cache.PlatformOperationsCache;
import io.github.jsoagger.core.business.cloud.operations.cache.PlatformOperationsCache.CachedObjectType;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class ListvaluesOperation implements IOperation {

  // needs PlatformOperationsCache
  private PlatformOperationsCache cache;


  /**
   * Constructor
   */
  public ListvaluesOperation() {
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      final String enumerationKey = query.get("enumerationKey").getAsString();
      final String containerPath = query.get("containerId").getAsString();

      final String cacheKey = enumerationKey + "____" + containerPath + "_____ENUM_KEY__VALUE";

      if (cache.contains(cacheKey, CachedObjectType.OPERATION_RESULT)) {
        final IOperationResult clone = cache.get(cacheKey, CachedObjectType.OPERATION_RESULT);
        resultHandler.accept(clone);
      }
      else {
        final IOperationResult result = CloudServicesLocator.listvaluesApi.getByName(query);
        if (result == null || result.hasBusinessError()) {
          resultHandler.accept(null);
          return;
        }

        //cache.put(cacheKey, result, CachedObjectType.OPERATION_RESULT);
        //final IOperationResult clone = cache.get(cacheKey, CachedObjectType.OPERATION_RESULT);
        resultHandler.accept(result);
      }
    }
    catch (final Exception e) {
      if(exHandler != null)
        exHandler.accept(e);
    }
  }

  /**
   * @return the cache
   */
  public PlatformOperationsCache getCache() {
    return cache;
  }


  /**
   * @param cache the cache to set
   */
  public void setCache(PlatformOperationsCache cache) {
    this.cache = cache;
  }
}
