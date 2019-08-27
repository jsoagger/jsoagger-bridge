/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.cloud.services.api.IBatchApi;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class BatchApi extends AbstractClientApi implements IBatchApi {

  private static final String LOAD_URI = "/api/batch/load";
  private static final String EXPORT_URI = "/api/batch/export";


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult batchLoad(JsonObject query) {
    try {
      String byPathUrl = getRootUrl().concat(LOAD_URI);

      Map<String, File> f = new HashMap<>();

      // upload folder content
      // firts leve children of that folder are files to upload
      if(query.get("local.attachments.folder") != null) {
        String attachments = query.get("local.attachments.folder").getAsString();

        File at = new File(attachments);
        if(at.isDirectory()) {
          for(File file : at.listFiles()) {
            if(file.isFile()) f.put("attachments_" + file.getName(), file);
          }
        }
      }

      if(query.get("input.file.path.raw") != null) {
        File file = new File(query.get("input.file.path.raw").getAsString());
        f.put("master_file", file);
      }

      if(!f.isEmpty()) {
        return doPostMp(query, f, byPathUrl, SingleResult.class);
      }
      else {
        return doPost(query, byPathUrl, SingleResult.class);
      }
    }
    catch (Exception e) {
      logException(e);
      return IOperationResult.getGeneralSingleResultError();
    }
  }

  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult batchExport(JsonObject query) {
    try {
      String byPathUrl = getRootUrl().concat(EXPORT_URI);

      if(query.get("input.file.path.raw") != null && !query.get("input.file.path.raw").isJsonNull()) {
        String uploadMasterFile = query.get("input.file.path.raw").getAsString();
        Map<String, File> f = new HashMap<>();
        //f.put("input.file.path.raw", new File(uploadMasterFile));
        //return doPostMp(query, f, byPathUrl, SingleResult.class);
      }

      return doPost(query, byPathUrl, SingleResult.class);
    }
    catch (Exception e) {
      logException(e);
      return IOperationResult.getGeneralSingleResultError();
    }
  }
}
