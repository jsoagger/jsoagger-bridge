/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IMultiIdentificationApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana VONJISOA
 *
 */
public class MultiIdentificationApi extends AbstractClientApi implements IMultiIdentificationApi {

  private static String GET_MASTER_IDENT_FOR_TYPE = "/v1/secured/api/multiIdentifiable/%s/masterIdentification/?typeId=%s&containerId=%s";
  private static String GET_IDENTS_ = "/v1/secured/api/multiIdentifiable/%s/identifications?containerId=%s";
  private static String SET_MASTER_IDENT_FOR_TYPE = "/v1/secured/api/multiIdentifiable/%s/masterIdentification/?typeId=%s&identificationId=%s&containerId=%s";

  @Override
  public IOperationResult getAllIdentifications(JsonObject query) {
    try {
      String multiIdentifiableFullId = getFullId(query);
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(GET_IDENTS_, multiIdentifiableFullId,containerId));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }

  @Override
  public IOperationResult getMasterIdentificationForType(JsonObject query) {
    try {
      String multiIdentifiableFullId = getFullId(query);
      String typeFullId = query.get("typeId").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(GET_MASTER_IDENT_FOR_TYPE, multiIdentifiableFullId, typeFullId, containerId));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult setMasterIdentification(JsonObject query) {
    try {
      String multiIdentifiableFullId = getFullId(query);
      String identificationFullId = query.get("identificationId").getAsString();
      String typeFullId = query.get("typeId").getAsString();
      String containerId = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(SET_MASTER_IDENT_FOR_TYPE, multiIdentifiableFullId, typeFullId, identificationFullId, containerId));
      IOperationResult result = doPut(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }
}
