/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IPeopleApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana  VONJISOA
 *
 */
public class PeopleApi extends AbstractClientApi implements IPeopleApi{


  private static final String CREATE_PEOPLE_URI = "/v1/secured/api/people/";


  @Override
  public IOperationResult createPeople(JsonObject query) {
    try {
      String url = getRootUrl().concat(String.format(CREATE_PEOPLE_URI));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  @Override
  public IOperationResult getPartyByReference(JsonObject params) {
    String reference = params.get("reference").getAsString();
    String type = reference.split("\\|")[0];
    String ref = reference.split("\\|")[1];

    if("people".equals(type)) {

    }
    else if("organisation".equals(type)) {

    }

    return null;
  }
}
