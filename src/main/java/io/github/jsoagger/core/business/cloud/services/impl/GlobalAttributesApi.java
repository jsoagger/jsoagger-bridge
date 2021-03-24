/**
 * 2 févr. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;
import io.github.jsoagger.core.business.cloud.services.api.IGlobalAttributesApi;
import com.google.gson.JsonObject;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 2 févr. 2018
 */
public class GlobalAttributesApi extends AbstractClientApi implements IGlobalAttributesApi {

  private static final String	COUNT_ALL		= "/transdev/base/v1/secured/api/globalAttributes/count/?type=%s";
  private static final String	GET_ALL			= "/transdev/base/v1/secured/api/globalAttributes/?type=%s&pageSize=%s&page=%s";
  private static final String	BY_LOGICAL_NAME	= "/transdev/base/v1/secured/api/globalAttributes/byLogicalName/%s/";
  private static final String	BY_OID			= "/transdev/base/v1/secured/api/globalAttributes/%s/";


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult getAllGlobalAttributes(JsonObject query) {
    try {
      Object page = query.get("page").getAsString();
      Object pageSize = query.get("pageSize").getAsString();
      String byPathUrl = getRootUrl().concat(String.format(GET_ALL, "", pageSize, page));
      IOperationResult result = doGet(query, byPathUrl, MultipleResult.class);
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
  public IOperationResult countGlobalAttributes(JsonObject query) {
    try {
      Object type = query.get("type").getAsString();
      String byPathUrl = getRootUrl().concat(String.format(COUNT_ALL, type));
      IOperationResult result = doGet(query, byPathUrl, SingleResult.class);
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
  public IOperationResult getGlobalAttributesByType(JsonObject query) {
    try {
      Object page = query.get("page");
      Object pageSize = query.get("pageSize");

      // string
      Object type = query.get("type");
      String byPathUrl = getRootUrl().concat(String.format(GET_ALL, type, pageSize, page));
      IOperationResult result = doGet(query, byPathUrl, MultipleResult.class);
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
  public IOperationResult getGlobalAttributesByLogicalName(JsonObject query) {
    try {
      Object logicalName = query.get("logicalName");
      String oid = getRootUrl().concat(String.format(BY_LOGICAL_NAME, logicalName));
      IOperationResult result = doGet(query, oid, SingleResult.class);
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
  public IOperationResult getGlobalAttributesByOid(JsonObject query) {
    try {
      Object oid = query.get("oid").getAsString();
      String url = getRootUrl().concat(String.format(BY_OID, oid));
      IOperationResult result = doGet(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      return IOperationResult.getGeneralSingleResultError();
    }
  }
}
