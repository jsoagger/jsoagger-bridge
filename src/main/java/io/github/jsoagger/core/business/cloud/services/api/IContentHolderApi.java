/**
 * 4 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.services.api;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.bridge.operation.IOperationResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 4 févr. 2018
 */
public interface IContentHolderApi {

  IOperationResult downloadAllByHolderOid(JsonObject query);
  IOperationResult downloadAttachmentsByHolderOid(JsonObject query);
  IOperationResult downloadPrimaryContentByHolderOid(JsonObject query);

  IOperationResult getAllContentsByHolderOid(JsonObject query);
  IOperationResult getAttachmentsByHolderOid(JsonObject query);

  IOperationResult getPrimaryContentByHolderOid(JsonObject query);
  IOperationResult setPrimaryContentByHolderOid(JsonObject query);
  IOperationResult addAttachmentContentByHolderOid(JsonObject query);

  IOperationResult getContentInfo(JsonObject object);
  IOperationResult deleteContentItemByHolderOid(JsonObject query);
}
