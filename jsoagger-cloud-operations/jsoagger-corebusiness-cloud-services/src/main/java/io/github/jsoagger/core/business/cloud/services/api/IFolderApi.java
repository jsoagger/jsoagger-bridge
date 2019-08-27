/**
 * 20 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.services.api;

import java.util.function.Consumer;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 20 févr. 2018
 */
public interface IFolderApi {

  IOperationResult createFolder(JsonObject query);


  IOperationResult countFolderChidren(JsonObject query);


  IOperationResult getFolderChidren(JsonObject query);


  IOperationResult getFolderContent(JsonObject query);

  void getFolderContentAsynch(JsonObject query, Consumer<IOperationResult> c);

  IOperationResult getFolder(JsonObject query) ;
  IOperationResult countFolderContent(JsonObject query);


  IOperationResult populateFromTemplate(JsonObject params);


  IOperationResult getFolderByPath(JsonObject params);


  IOperationResult deleteFolder(JsonObject params);
}
