/**
 * 4 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IRCApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 4 févr. 2018
 */
public class RCApi extends AbstractClientApi implements IRCApi {

  private static final String	MASTER_BY_NAME		  = "/transdev/base/v1/secured/api/rc/master/byName/?name=%s&domainClass=%s&containerId=%s";
  private static final String   MASTER_BY_NUMBER      = "/transdev/base/v1/secured/api/rc/master/byNumber/?number=%s&domainClass=%s&containerId=%s";

  private static final String	CHECKIN_URL			= "/transdev/base/v1/secured/api/workable/%s/checkin/?containerId=%s";
  private static final String   CHECKOUT_URL        = "/transdev/base/v1/secured/api/workable/%s/checkout/?containerId=%s";
  private static final String   UNDOCHECKOUT_URL    = "/transdev/base/v1/secured/api/workable/%s/undoCheckout/?containerId=%s";

  private static final String   ITERATED_MASTER_URL = "/transdev/base/v1/secured/api/rc/%s/master/?containerId=%s";
  private static final String	REVISE_URL			= "/transdev/base/v1/secured/api/rc/%s/revise/?containerId=%s";
  private static final String   DELETE_ITER_URL     = "/transdev/base/v1/secured/api/rc/%s/?containerId=%s";
  private static final String	WORKINGCOPY_URL		= "/transdev/base/v1/secured/api/rc/%s/workingCopy/?containerId=%s";

  private static final String	ALL_VERSIONS_OF_MASTER_URL	        = "/transdev/base/v1/secured/api/rc/%s/versions/?containerId=%s";
  private static final String   ALL_ITERATIONS_OF_MASTER_URL        = "/transdev/base/v1/secured/api/rc/%s/iterations/?containerId=%s";
  private static final String   LATEST_OF_MASTER_URL                = "/transdev/base/v1/secured/api/rc/%s/latest/?containerId=%s";

  private static final String ALL_ITERATIONS_BY_VERSION_URL = "/transdev/base/v1/secured/api/rc/%s/iterationsOfVersion/?versionNumber=%s&containerId=%s";

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult deleteIteration(JsonObject query) {
    String fullId = getFullId(query);
    String url = getRootUrl().concat(String.format(DELETE_ITER_URL, fullId));
    IOperationResult result = doDelete(query, url, SingleResult.class);
    return result;
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult masterByName(JsonObject query) {
    try {
      String container = query.get("containerId").getAsString();
      String name = query.get("name").getAsString();
      String domainClass = query.get("domainClass").getAsString();

      String url = getRootUrl().concat(String.format(MASTER_BY_NAME, name, domainClass, container));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult masterByNumber(JsonObject query) {
    try {
      String container = query.get("containerId").getAsString();
      String number = query.get("number").getAsString();
      String domainClass = query.get("domainClass").getAsString();

      String url = getRootUrl().concat(String.format(MASTER_BY_NUMBER, number, domainClass, container));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult checkin(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String container = query.get("containerId").getAsString();
      String url = getRootUrl().concat(String.format(CHECKIN_URL, fullId, container));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult checkout(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String container = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(CHECKOUT_URL, fullId, container));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult workingCopy(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String container = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(WORKINGCOPY_URL, fullId, container));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult undoCheckout(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String container = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(UNDOCHECKOUT_URL, fullId, container));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult revise(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String container = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(REVISE_URL, fullId, container));
      IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult allIterationsOf(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String container = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(ALL_ITERATIONS_OF_MASTER_URL, fullId, container));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult allIterationsOfVersion(JsonObject query) {
    try {
      JsonElement fullId = query.get("masterId");
      JsonElement versionNumber = query.get("versionNumber");
      String container = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(ALL_ITERATIONS_BY_VERSION_URL, fullId.getAsString(), versionNumber.getAsString(), container));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult allVersionsOf(JsonObject query) {
    try {
      String fullId = getFullId(query);
      String container = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(ALL_VERSIONS_OF_MASTER_URL, fullId, container));
      IOperationResult result = doGet(query, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getExactIterationVersionNumber(JsonObject query) {
    return null;
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getIterationWithMasterByOid(JsonObject query) {
    try {
      String masterId = getFullId(query);
      String container = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(ITERATED_MASTER_URL, masterId, container));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getLatestIterationOfVersion(JsonObject query) {
    return null;
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getLatestVersion(JsonObject query) {
    try {
      String masterId = getFullId(query);
      String container = query.get("containerId").getAsString();

      String url = getRootUrl().concat(String.format(LATEST_OF_MASTER_URL, masterId, container));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }
}
