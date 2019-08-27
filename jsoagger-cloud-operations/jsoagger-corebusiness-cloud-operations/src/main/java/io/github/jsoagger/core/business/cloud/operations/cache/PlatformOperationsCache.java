/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.cache;

import java.util.HashMap;
import java.util.Map;

import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.SingleResult;
import io.github.jsoagger.core.utils.StringUtils;

/**
 * TODO Handler LRU.
 *
 * @author Ramilafananana  VONJISOA
 *
 */
public class PlatformOperationsCache {

  private final static Map<Object, IOperationResult>	OP_RESULT_CACHE	= new HashMap<>();
  private final static Map<Object, IOperationResult>	IMAGES_CACHE	= new HashMap<>();


  /**
   * Put an object in cache, location depends on type of the object.
   *
   * @param key
   * @param value
   * @param type
   */
  public static void put(Object key, IOperationResult value, CachedObjectType type) {
    if (type == CachedObjectType.IMAGE) {
      synchronized (IMAGES_CACHE) {
        IMAGES_CACHE.put(key, value);
      }
    }
    else {
      synchronized (OP_RESULT_CACHE) {
        OP_RESULT_CACHE.put(key, value);
      }
    }
  }


  /**
   * @param key
   * @param val
   */
  public static void addImage(String key, byte[] val) {
    final IOperationResult res = new SingleResult();
    res.addMetaData(key, val);
    IMAGES_CACHE.put(key, res);
  }


  /**
   * @param key
   * @param base64
   */
  public static byte[] getImage(String key) {
    final IOperationResult res = IMAGES_CACHE.get(key);
    if (res != null) {
      return (byte[]) res.getMetaData().get(key);
    }

    return null;
  }


  /**
   * @param key
   * @param value
   * @param type
   */
  public static boolean contains(Object key, CachedObjectType type) {
    if (type == CachedObjectType.IMAGE) {
      synchronized (IMAGES_CACHE) {
        return IMAGES_CACHE.containsKey(key);
      }
    }
    else {
      synchronized (OP_RESULT_CACHE) {
        return OP_RESULT_CACHE.containsKey(key);
      }
    }
  }


  public static IOperationResult get(Object key, CachedObjectType type) {
    if (type == CachedObjectType.IMAGE) {
      synchronized (IMAGES_CACHE) {
        return IMAGES_CACHE.get(key);
      }
    }
    else {
      synchronized (OP_RESULT_CACHE) {
        return OP_RESULT_CACHE.get(key);
      }
    }
  }


  public static void cleanup(CachedObjectType type) {
    if (type == null) {
      cleanup();
      return;
    }

    if (type == CachedObjectType.IMAGE) {
      synchronized (OP_RESULT_CACHE) {
        OP_RESULT_CACHE.clear();
      }
      return;
    }

    if (type == CachedObjectType.OPERATION_RESULT) {
      synchronized (OP_RESULT_CACHE) {
        IMAGES_CACHE.clear();
      }
    }
  }


  public static void cleanup() {
    synchronized (OP_RESULT_CACHE) {
      OP_RESULT_CACHE.clear();
    }

    synchronized (OP_RESULT_CACHE) {
      IMAGES_CACHE.clear();
    }
  }

  protected class CacheObjectAccessRegistry {

    public long		lastAccessed	= System.currentTimeMillis();
    public String	value;


    protected CacheObjectAccessRegistry(String value) {
      this.value = value;
    }
  }

  /**
   * Type of object to put into the cache.
   *
   * @author Ramilafananana  VONJISOA
   *
   */
  public enum CachedObjectType {
    IMAGE, OPERATION_RESULT;

    public static CachedObjectType fromString(String s) {
      if (StringUtils.isEmpty(s)) {
        return OPERATION_RESULT;
      }

      if ("images".equalsIgnoreCase(s)) {
        return CachedObjectType.IMAGE;
      }

      return OPERATION_RESULT;
    }
  }
}
