/**
 *
 */
package io.github.jsoagger.core.business.cloud.operations.preferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.operations.cache.PlatformOperationsCache;
import io.github.jsoagger.core.business.cloud.operations.cache.PlatformOperationsCache.CachedObjectType;
import io.github.jsoagger.core.business.cloud.operations.container.user.GetCurrentUserOperation;
import io.github.jsoagger.core.business.cloud.services.utils.CloudServicesLocator;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.OperationData;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class GetPreferenceValueOperation implements IOperation {

  public static Map<String, OperationData> CACHE = new HashMap<>();


  /**
   * {@inheritDoc}
   */
  @Override
  public void doOperation(JsonObject query,
      Consumer<IOperationResult> resultHandler,
      Consumer<Throwable> exHandler) {

    if(query == null) throw new NullPointerException("Query can not be null");
    String key = query.get("key").getAsString();

    final List<OperationData> datas = new ArrayList<>();
    MultipleResult mr = new MultipleResult();

    if(CACHE.containsKey(key)) {
      OperationData data = CACHE.get(key);
      datas.add(data);
      mr.setData(datas);
      resultHandler.accept(mr);
    }
    else {
      try {
        SingleResult currentUser = (SingleResult) PlatformOperationsCache.get(GetCurrentUserOperation.CACHE_KEY, CachedObjectType.OPERATION_RESULT);
        String userLogin = (String) currentUser.getData().getAttributes().get("nickName");
        query.addProperty("userLogin", userLogin);

        IOperationResult result = CloudServicesLocator.preferenceApi.getPreference(query);
        if(!result.hasBusinessError()) {
          datas.add(((SingleResult)result).getData());
          mr.setData(datas);
          resultHandler.accept(mr);

          CACHE.put(key, ((SingleResult)result).getData());
        }
        else {
          resultHandler.accept(IOperationResult.emptyMultipleResult());
        }
      }
      catch (Exception e) {
        if(exHandler != null) exHandler.accept(e);
      }
    }
  }
}
