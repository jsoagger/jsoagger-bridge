/**
 * 26 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.SingleResult;
import io.github.jsoagger.core.business.cloud.services.api.IDocumentApi;
import com.google.gson.JsonObject;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 26 févr. 2018
 */
public class DocumentApi extends AbstractClientApi implements IDocumentApi {

  private static final String CREATE_DOC_URI = "/api/document/";


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult createDocument(JsonObject query) {
    try {
      String createUrl = getRootUrl().concat(String.format(CREATE_DOC_URI));
      IOperationResult result = doPost(query, createUrl, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

}
