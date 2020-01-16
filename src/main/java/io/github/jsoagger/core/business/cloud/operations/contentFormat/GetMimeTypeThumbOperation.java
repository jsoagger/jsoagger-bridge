/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.contentFormat;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.operations.cache.PlatformOperationsCache;
import io.github.jsoagger.core.business.cloud.operations.cache.PlatformOperationsCache.CachedObjectType;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * Get the thumbnail associated to a mimetype.
 *
 * @author Ramilafananana  VONJISOA
 *
 */
public class GetMimeTypeThumbOperation implements IOperation {


  // needs PlatformOperationsCache
  private PlatformOperationsCache cache;


  /**
   * Constructor
   */
  public GetMimeTypeThumbOperation() {
    super();
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    try {
      if(query == null) throw new NullPointerException("Query can not be null");
      final IOperationResult result = new SingleResult();
      final String mimetype = query.get("mimeType").getAsString();
      if (cache.contains(mimetype, CachedObjectType.IMAGE)) {
        final byte[] res = cache.getImage(mimetype);
        result.addMetaData("mimeType", res);
        resultHandler.accept(result);
      }
      else {
        final byte[] res = CloudServicesLocator.contentFormatApi.getMimeTypeThumb(query);
        if (res == null) {
          resultHandler.accept(null);
          return;
        }

        cache.addImage(mimetype, res);
        final byte[] clone = cache.getImage(mimetype);
        result.addMetaData("mimeType", clone);
        resultHandler.accept(result);
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
