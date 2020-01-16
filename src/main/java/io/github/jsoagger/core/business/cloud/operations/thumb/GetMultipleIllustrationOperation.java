/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.thumb;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.operations.cache.PlatformOperationsCache;
import io.github.jsoagger.core.business.cloud.operations.cache.PlatformOperationsCache.CachedObjectType;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class GetMultipleIllustrationOperation implements IOperation {

  // needs PlatformOperationsCache
  private PlatformOperationsCache cache;

  /**
   * Constructor
   */
  public GetMultipleIllustrationOperation() {
    super();
  }

  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler,
      Consumer<Throwable> exHandler) {

    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      final String fullId = query.get("fullId").getAsString();
      final String cacheKey = fullId + "__THUMBNAIL";
      final IOperationResult result = new MultipleResult();
      if (cache.contains(cacheKey, CachedObjectType.IMAGE)) {
        // TODO
        resultHandler.accept(null);
      } else {
        // TODO
        resultHandler.accept(null);
      }
    } catch (final Exception e) {
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
