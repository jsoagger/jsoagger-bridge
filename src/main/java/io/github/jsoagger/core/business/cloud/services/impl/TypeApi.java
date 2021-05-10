/**
 * 20 janv. 2018
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.ITypeApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana Vonjisoa
 * @mailTo yvonjisoa@nexitia.com
 * @date 20 janv. 2018
 */
public class TypeApi extends AbstractClientApi implements ITypeApi {

  private static final String	TYPE_BY_PATH					= "/jsoagger/base/v1/secured/api/type/byPath/?path=%s&containerId=%s";
  private static final String   TYPE_BY_LOGICAL_NAME            = "/jsoagger/base/v1/secured/api/type/byLogicalName?logicalName=%s&containerId=%s";

  private static final String	LINKABLE_ROLE_B					= "/jsoagger/base/v1/secured/api/type/%s/linkableRoleBs/?linkTypePath=%s&containerId=%s";
  private static final String	SUBTYPE_TYPE					= "/jsoagger/base/v1/secured/api/type/%s/subtypes/?containerId=%s";
  private static final String	INSTANCIABLE_SUBTYPE			= "/jsoagger/base/v1/secured/api/type/%s/instanciableSubtypes/?rootType=%s&containerId=%s";
  private static final String	DYNAMICAL_ATTIBUTES				= "/jsoagger/base/v1/secured/api/type/%s/dynamicalAttributesDefinitions/?containerId=%s";

  private static final String   TYPE_GET_LIFECYCLE    = "/jsoagger/base/v1/secured/api/type/%s/lifecycle/?containerId=%s";
  private static final String   TYPE_SET_LIFECYCLE    = "/jsoagger/base/v1/secured/api/type/%s/lifecycle/?lifecycleId=%s&containerId=%s";


  /**
   * {@inheritDoc}
   */
  @Override
  public synchronized IOperationResult subtypes(JsonObject query) {
    try {
      final String oid = getFullId(query);
      final String containerId = query.get("containerId").getAsString();

      final String url = getRootUrl().concat(String.format(SUBTYPE_TYPE, oid, containerId));
      final IOperationResult result = doGet(null, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult instanciableSubtypes(JsonObject query) {
    try {
      final String id = getFullId(query);
      final String containerId = query.get("containerId").getAsString();

      final String url = getRootUrl().concat(String.format(INSTANCIABLE_SUBTYPE, id, containerId));
      final IOperationResult result = doGet(null, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult softAttributesDefinition(JsonObject query) {
    try {
      final String id = getFullId(query);
      final String containerId = query.get("containerId").getAsString();

      final String url = getRootUrl().concat(String.format(DYNAMICAL_ATTIBUTES, id, containerId));
      final IOperationResult result = doGet(null, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }


  @Override
  public synchronized IOperationResult byPath(JsonObject query) {
    try {
      final String path = query.get("path").getAsString();
      final String containerId = query.get("containerId").getAsString();

      final String url = getRootUrl().concat(String.format(TYPE_BY_PATH, encodeString(path), containerId));
      final IOperationResult result = doGet(null, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public synchronized IOperationResult linkableRoleBs(JsonObject query) {
    try {
      final String oid = getFullId(query);
      final String linkTypePath = query.get("linkTypePath").getAsString();
      final String containerId = query.get("containerId").getAsString();

      final String url = getRootUrl().concat(String.format(LINKABLE_ROLE_B, oid, encodeString(linkTypePath), containerId));
      final IOperationResult result = doGet(null, url, MultipleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.generalMultipleResutError();
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult byLogicalName(JsonObject query) {
    try {
      final String logicalName = query.get("logicalName").getAsString();
      final String containerId = query.get("containerId").getAsString();

      final String url = getRootUrl().concat(String.format(TYPE_BY_LOGICAL_NAME, encodeString(logicalName), containerId));
      final IOperationResult result = doGet(null, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult setLifecycle(JsonObject query) {
    try {
      final String typeId = getFullId(query);
      final String lifecycleMasterId = query.get("lifecycleId").getAsString();
      final String containerId = query.get("containerId").getAsString();

      final String url = getRootUrl().concat(String.format(TYPE_SET_LIFECYCLE,typeId, lifecycleMasterId, containerId));
      final IOperationResult result = doPost(query, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }


  /**
   * {@inheritDoc}
   */
  @Override
  public IOperationResult getLifecycle(JsonObject query) {
    try {
      final String typeId = query.get("id").getAsString();
      final String containerId = query.get("containerId").getAsString();

      final String url = getRootUrl().concat(String.format(TYPE_GET_LIFECYCLE, encodeString(typeId), containerId));
      final IOperationResult result = doGet(null, url, SingleResult.class);
      return result;
    }
    catch (Exception e) {
      return IOperationResult.getGeneralSingleResultError();
    }
  }
}
