/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.container;

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
public class LoadContainerByOidOperation implements IOperation {


  // needs PlatformOperationsCache
  private PlatformOperationsCache cache;


  /**
   * Constructor
   */
  public LoadContainerByOidOperation() {
    super();
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      final String id = query.get("id").getAsString();
      final String cacheKey = id + "__object_key";


      cache.cleanup();

      if (cache.contains(cacheKey, CachedObjectType.OPERATION_RESULT)) {
        final IOperationResult clone = cache.get(cacheKey, CachedObjectType.OPERATION_RESULT);
        resultHandler.accept(clone);
      }
      else {
        final IOperationResult res = CloudServicesLocator.containerApi.getContainer(query);
        if (res == null || res.hasBusinessError()) {
          resultHandler.accept(null);
          return;
        }

        cache.put(cacheKey, res, CachedObjectType.OPERATION_RESULT);
        final IOperationResult clone = cache.get(cacheKey, CachedObjectType.OPERATION_RESULT);
        resultHandler.accept(clone);
      }
    }
    catch (final Exception e) {
      if(exHandler != null) exHandler.accept(e);
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
