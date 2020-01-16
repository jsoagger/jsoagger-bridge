/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.cache;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.operations.cache.PlatformOperationsCache.CachedObjectType;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.utils.StringUtils;

/**
 * Clear all datas in cache.
 *
 * @author Ramilafananana  VONJISOA
 *
 */
public class ClearCacheOperation implements IOperation {

  // needs PlatformOperationsCache
  private PlatformOperationsCache cache;


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      final Object location = query.get("location").getAsString();
      if (StringUtils.isEmpty((String) location)) {
        cache.cleanup();
      }
      else {
        final CachedObjectType type = CachedObjectType.fromString(location.toString());
        cache.cleanup(type);
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
