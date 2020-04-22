/**
 *
 */
package io.github.jsoagger.core.business.cloud.services.impl;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import com.google.gson.JsonObject;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.operation.JsonUtils;
import io.github.jsoagger.core.bridge.result.SingleResult;
import io.github.jsoagger.jfxcore.api.security.ILoginSessionHolder;
import io.github.jsoagger.jfxcore.api.services.Services;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public abstract class AbstractClientApi {

  private static final String HTTP_CODE = "__http_code";
  private static final String ID = "id";
  public static final String REMOTE_SERVER_LOCATION = "remoteServerLocation";
  public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

  private Properties cloudServicesProperties;
  protected static OkHttpClient client = new OkHttpClient();

  /**
   * Constructor
   */
  public AbstractClientApi() {
    client.setConnectTimeout(20, TimeUnit.SECONDS);
    client.setReadTimeout(120, TimeUnit.SECONDS);
    client.setWriteTimeout(120, TimeUnit.SECONDS);

    ConnectionPool connectionPool = new ConnectionPool(10, 10000, TimeUnit.MILLISECONDS);
    client.setConnectionPool(connectionPool);
  }

  private String getSessionId() {
    ILoginSessionHolder loginContext = (ILoginSessionHolder) Services.getBean("LoginSessionHolder");
    String sesstionId = loginContext.getSessionId();
    return sesstionId;
  }

  public <T extends IOperationResult> IOperationResult doGet(JsonObject query, String url,
      Class<T> clazz) {

    Request request = new Request.Builder().url(url).header("Content-Type", "application/json")
        .header("Accept", "application/json").header("Cookie", "sid=" + getSessionId()).build();

    if (query == null) {
      query = new JsonObject();
    }

    Response response = null;
    try {
      response = client.newCall(request).execute();
      T o = JsonUtils.toJsonObject(response.body().string(), clazz);
      setHttpStatus(response, o);
      return o;
    } catch (IOException e1) {
      System.out.println("Error > " + e1.getMessage());
    } finally {
      if (response != null) {
        try {
          response.body().close();
        } catch (IOException e) {
          // e1.printStackTrace();
        }
      }
    }

    return null;
  }


  public <T extends IOperationResult> void doGetAsynch(JsonObject query, String url, Class<T> clazz,
      Consumer<IOperationResult> resHandler) {

    Request request = new Request.Builder().url(url).header("Content-Type", "application/json")
        .header("Accept", "application/json")
        .header("Authorization", "Token " + System.getProperty("shiro.session.id"))
        .header("Cookie", "sid=" + getSessionId()).build();

    if (query == null) {
      query = new JsonObject();
    }

    client.newCall(request).enqueue(new Callback() {

      @Override
      public void onResponse(Response response) throws IOException {
        try {
          IOperationResult o = JsonUtils.toJsonObject(response.body().string(), clazz);
          setHttpStatus(response, o);
          resHandler.accept(o);
        } catch (IOException e) {
          System.out.println("Error > " + e.getMessage());
        } finally {
          response.body().close();
        }
      }

      @Override
      public void onFailure(Request request, IOException e) {
        System.out.println("Error > " + e.getMessage());
      }
    });
  }


  public byte[] doGetByte(JsonObject query, String url) throws IOException {
    Request request = new Request.Builder().url(url).header("Content-Type", "application/json")
        .header("Accept", "application/json").header("Cookie", "sid=" + getSessionId()).build();

    if (query == null) {
      query = new JsonObject();
    }

    Response response = null;

    try {
      response = client.newCall(request).execute();
      byte[] o = response.body().string().getBytes();
      return o;
    } finally {
      if (response != null) {
        try {
          response.body().close();
        } catch (IOException e) {
        }
      }
    }
  }

  public <T extends IOperationResult> IOperationResult doPost(JsonObject query, String url,
      Class<T> clazz, boolean anon) {
    Response response = null;

    try {
      RequestBody body = RequestBody.create(JSON, JsonUtils.toString(query));
      Request request = null;

      if (anon) {
        request = new Request.Builder().url(url).header("Content-Type", "application/json")
            .header("Accept", "application/json").post(body).build();
      } else {
        request = new Request.Builder().url(url).header("Content-Type", "application/json")
            .header("Accept", "application/json").header("Cookie", "sid=" + getSessionId())
            .post(body).build();
      }

      response = client.newCall(request).execute();
      T o = JsonUtils.toJsonObject(response.body().string(), clazz);
      setHttpStatus(response, o);
      return o;
    } catch (Exception e) {
      System.out.println("Error > " + e.getMessage());
      if (clazz.getName().equals(SingleResult.class.getName())) {
        return IOperationResult.getNetworkError();
      } else {
        return IOperationResult.generalMultipleResutError();
      }
    } finally {
      if (response != null) {
        try {
          response.body().close();
        } catch (IOException e) {
        }
      }
    }
  }


  /**
   * Do http POST operation and add header to request before sending it
   *
   * @param query
   * @param url
   * @return IOperationResult
   */
  public <T extends IOperationResult> IOperationResult doPut(JsonObject query, String url,
      Class<T> clazz) {
    return doPut(query, url, clazz, false);
  }

  public <T extends IOperationResult> IOperationResult doPut(JsonObject query, String url,
      Class<T> clazz, boolean anon) {
    Response response = null;

    try {
      RequestBody body = RequestBody.create(JSON, JsonUtils.toString(query));
      Request request = null;

      if (anon) {
        request = new Request.Builder().url(url).header("Content-Type", "application/json")
            .header("Accept", "application/json").put(body).build();
      } else {
        request = new Request.Builder().url(url).header("Content-Type", "application/json")
            .header("Accept", "application/json").header("Cookie", "sid=" + getSessionId())
            .put(body).build();
      }

      response = client.newCall(request).execute();
      T o = JsonUtils.toJsonObject(response.body().string(), clazz);
      setHttpStatus(response, o);
      return o;
    } catch (Exception e) {
      System.out.println("Error > " + e.getMessage());
      if (clazz.getName().equals(SingleResult.class.getName())) {
        return IOperationResult.getNetworkError();
      } else {
        return IOperationResult.generalMultipleResutError();
      }
    } finally {
      if (response != null) {
        try {
          response.body().close();
        } catch (IOException e) {
        }
      }
    }
  }


  /**
   * Do http POST operation and add header to request before sending it
   *
   * @param query
   * @param url
   * @return IOperationResult
   */
  public <T extends IOperationResult> IOperationResult doPost(JsonObject query, String url,
      Class<T> clazz) {
    return doPost(query, url, clazz, false);
  }

  /**
   * Do http POST operation and add header to request before sending it
   *
   * @param query
   * @param url
   * @return IOperationResult
   */
  public <T extends IOperationResult> IOperationResult doPostMp(JsonObject query,
      Map<String, File> files, String url, Class<T> clazz) {

    Response response = null;

    try {
      RequestBody body = null;

      if (files != null && !files.isEmpty()) {
        MultipartBuilder mpbuilder = new MultipartBuilder().type(MultipartBuilder.FORM);
        for (String filename : files.keySet()) {
          mpbuilder.addFormDataPart("file", filename,
              RequestBody.create(MediaType.parse("text/csv"), files.get(filename)));
        }

        mpbuilder.addFormDataPart("form", JsonUtils.toString(query));
        body = mpbuilder.build();
      } else {
        body = new MultipartBuilder().type(MultipartBuilder.FORM).build();
      }

      Request request = new Request.Builder().url(url).header("Accept", "application/json")
          .header("Cookie", "sid=" + getSessionId()).post(body).build();

      response = client.newCall(request).execute();
      T o = JsonUtils.toJsonObject(response.body().string(), clazz);
      setHttpStatus(response, o);
      return o;
    } catch (Exception e) {
      System.out.println("Error > " + e.getMessage());
      if (clazz.getName().equals(SingleResult.class.getName())) {
        return IOperationResult.getNetworkError();
      } else {
        return IOperationResult.generalMultipleResutError();
      }
    } finally {
      if (response != null) {
        try {
          response.body().close();
        } catch (IOException e) {
        }
      }
    }
  }


  /**
   * Do http DELETE operation and add header to request before sending it
   *
   * @param query
   * @param url
   * @return IOperationResult
   */
  public <T extends IOperationResult> IOperationResult doDelete(JsonObject query, String url,
      Class<T> clazz) {

    Response response = null;

    try {
      RequestBody body = RequestBody.create(JSON, JsonUtils.toString(query));
      Request request = new Request.Builder().url(url).header("Content-Type", "application/json")
          .header("Accept", "application/json").header("Cookie", "sid=" + getSessionId())
          .delete(body).build();

      response = client.newCall(request).execute();
      T o = JsonUtils.toJsonObject(response.body().string(), clazz);
      setHttpStatus(response, o);
      return o;
    } catch (Exception e) {
      System.out.println("Error > " + e.getMessage());
      if (clazz.getName().equals(SingleResult.class.getName())) {
        return IOperationResult.getNetworkError();
      } else {
        return IOperationResult.generalMultipleResutError();
      }
    } finally {
      if (response != null) {
        try {
          response.body().close();
        } catch (IOException e) {
        }
      }
    }
  }

  /**
   * Encode given string into UTF-8
   *
   * @param param
   * @return
   */
  protected String encodeString(String param) {
    try {
      return URLEncoder.encode(param, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      return URLEncoder.encode(param);
    }
  }


  /**
   * Load an exception
   *
   * @param e
   */
  protected void logException(Throwable e) {
    e.printStackTrace();
    if (e.getCause() != null) {
      e.getCause().printStackTrace();
    }
  }


  /**
   * @return the cloudServicesProperties
   */
  public Properties getCloudServicesProperties() {
    return cloudServicesProperties;
  }


  /**
   * @param cloudServicesProperties the cloudServicesProperties to set
   */
  public void setCloudServicesProperties(Properties cloudServicesProperties) {
    this.cloudServicesProperties = cloudServicesProperties;
  }

  /**
   * Get root URL
   *
   * @return
   */
  public String getRootUrl() {
    String rootUrl = cloudServicesProperties.getProperty(REMOTE_SERVER_LOCATION);
    // System.out.println("AbstractClientApi: " + rootUrl);
    // System.out.println("AbstractClientApi: " + cloudServicesProperties);
    return "http://soaggyshop-demo.uat.jsoagger.tech/soaggyshop/";
  }

  /**
   * Get full identifier of an query
   *
   * @param query
   * @return
   */
  protected String getFullId(JsonObject query) {
    if (query.get(ID).isJsonNull()) {
      return query.get(ID).getAsString();
    }
    return query.get(ID).getAsString();
  }


  /**
   * Copy http status code from response to result. It can be accessed via meatada through key
   * 'http_code'
   *
   * @param response
   * @param result
   */
  protected void setHttpStatus(Response response, Object result) {
    if (result != null && response != null && result instanceof IOperationResult) {
      IOperationResult r = (IOperationResult) result;
      r.addMetaData(HTTP_CODE, response.code());
    }
  }
}
