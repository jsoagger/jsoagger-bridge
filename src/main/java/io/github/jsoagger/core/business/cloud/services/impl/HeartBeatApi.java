/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.SingleResult;
import io.github.jsoagger.core.business.cloud.services.api.IHeartbeatApi;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class HeartBeatApi extends AbstractClientApi implements IHeartbeatApi {


  private static final String API_HEARTBEAT_ANON_PING = "/v1/anon/ping";


  /**
   * Constructor
   */
  public HeartBeatApi() {
    super();
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult ping() {
    try {
      String pingUrl = getRootUrl().concat(API_HEARTBEAT_ANON_PING);
      IOperationResult result = doGet(null, pingUrl, SingleResult.class);
      return result;
    } catch (Exception e) {
      logException(e);
      return IOperationResult.getGeneralSingleResultError();
    }
  }
}
