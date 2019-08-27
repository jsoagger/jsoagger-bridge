/**
 * 21 févr. 2018
 *
 */
package io.github.jsoagger.core.business.offline.operations.folderTags;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperation;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.OperationData;
import io.github.jsoagger.core.utils.StringUtils;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 21 févr. 2018
 */
public class GetAllFolderTagsOperation implements IOperation {

  /**
   * Default Constructor
   */
  public GetAllFolderTagsOperation() {
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public void doOperation(JsonObject query, Consumer<IOperationResult> resultHandler, Consumer<Throwable> exHandler) {
    OperationData data = populate("io.github.jsoagger.foldered.Tag/Red", query);
    OperationData data2 = populate("io.github.jsoagger.foldered.Tag/Blue", query);
    OperationData data3 = populate("io.github.jsoagger.foldered.Tag/Yellow", query);
    OperationData data4 = populate("io.github.jsoagger.foldered.Tag/Green", query);
    OperationData data5 = populate("io.github.jsoagger.foldered.Tag/Orange", query);

    List<OperationData> datas = new ArrayList<>();
    datas.addAll(Arrays.asList(data, data2, data3, data4, data5));

    MultipleResult multipleResult = new MultipleResult();
    multipleResult.setData(datas);
    resultHandler.accept(multipleResult);
  }


  private OperationData populate(String tag, JsonObject query) {
    OperationData operationData = new OperationData();

    Object containerOid = query.get("containerOid");

    operationData.getBusinessType().put("internalType", tag);
    operationData.getContainer().put("containerOid", containerOid);
    operationData.getAttributes().put("name", StringUtils.substringAfterLast(tag, "/"));
    operationData.getAttributes().put("fullId", tag);
    return operationData;
  }
}
