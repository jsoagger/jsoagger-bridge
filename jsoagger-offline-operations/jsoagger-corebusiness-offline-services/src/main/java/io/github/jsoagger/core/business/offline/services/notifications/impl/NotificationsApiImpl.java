/**
 *
 */
package io.github.jsoagger.core.business.offline.services.notifications.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import com.google.gson.JsonObject;
import io.github.jsoagger.core.business.offline.services.notifications.api.INotificationsApi;
import io.github.jsoagger.core.bridge.exception.OperationException;
import io.github.jsoagger.core.bridge.operation.IOperationResult;
import io.github.jsoagger.core.bridge.operation.JsonUtils;
import io.github.jsoagger.core.bridge.result.MultipleResult;
import io.github.jsoagger.core.bridge.result.OperationData;
import io.github.jsoagger.core.bridge.result.SingleResult;

/**
 * @author Ramilafananana VONJISOA
 * @mailto yvonjisoa@nexitia.com
 * @date 2019
 */
public class NotificationsApiImpl implements INotificationsApi {

  private String			notificationsFilePath	= null;
  protected Properties	platformProperties;
  private final Map<String, JsonObject> allNotifications = new HashMap<String, JsonObject>();


  /**
   * Called by spring on bean initialisation, loads all serialized
   * notifications into allNotifications.
   */
  public void deserializeAllNotifications() {
    String notificationsFolderPath = platformProperties.getProperty("localNotificationsFolderPath");
    notificationsFilePath = notificationsFolderPath + File.separator + "local_notifications.txt";

    final File file = new File(notificationsFilePath);
    if (!file.exists()) {
      try {
        file.createNewFile();
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }

    if (file.exists()) {
      try {
        List<String> alllines = Files.readAllLines(file.toPath());
        for (String line : alllines) {
          JsonObject jsonObject = JsonUtils.toJsonObject(line);
          String uuid = jsonObject.get("uuid").getAsString();
          allNotifications.put(uuid, jsonObject);
        }
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
  }


  /**
   * @throws OperationException
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult createNotification(JsonObject query) throws OperationException {
    try {
      String notif = query.get("notification").getAsString();
      JsonObject jsonObject = JsonUtils.toJsonObject(notif);
      String uuid = UUID.randomUUID().toString();
      jsonObject.addProperty("uuid", uuid);
      allNotifications.put(uuid, jsonObject);

      // dump all line to file
      StringBuffer buffer = dumpAllToString();
      Files.write(new File(notificationsFilePath).toPath(), buffer.toString().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

      SingleResult result = new SingleResult.Builder().addMeta("done", "1").build();
      return result;
    }
    catch (IOException e) {
      e.printStackTrace();
      throw new OperationException(e);
    }
  }


  /**
   * @throws OperationException
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult deleteNotification(JsonObject query) throws OperationException {
    try {

      String uuid = query.get("uuid").getAsString();
      allNotifications.remove(uuid);

      // dump all line to file
      StringBuffer buffer = dumpAllToString();
      Files.write(new File(notificationsFilePath).toPath(), buffer.toString().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

      SingleResult result = new SingleResult.Builder().addMeta("done", "1").build();
      return result;
    }
    catch (IOException e) {
      e.printStackTrace();
      throw new OperationException(e);
    }
  }


  /**
   * @throws OperationException
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult setStatus(JsonObject query) throws OperationException {
    try {

      String uuids = query.get("uuids").getAsString();
      String status = query.get("status").getAsString();
      Arrays.asList(uuids.split(";")).forEach(uuid -> {
        JsonObject jsonObject = allNotifications.get(uuid);
        jsonObject.addProperty("status", status);
      });

      // dump all line to file
      StringBuffer buffer = dumpAllToString();
      Files.write(new File(notificationsFilePath).toPath(), buffer.toString().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

      SingleResult result = new SingleResult.Builder().addMeta("done", "1").build();
      return result;
    }
    catch (IOException e) {
      e.printStackTrace();
      throw new OperationException(e);
    }
  }


  /**
   * @throws OperationException
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult loadAllNotification(JsonObject query) throws OperationException {
    try {
      List<JsonObject> jsons = new ArrayList<JsonObject>();

      String status = query.get("status").getAsString();
      for (String key : allNotifications.keySet()) {
        JsonObject value = allNotifications.get(key);
        if (status != null) {
          String curStatus = value.get("status").getAsString();
          if (curStatus.equalsIgnoreCase(status)) {
            jsons.add(value);
          }
        }
        else {
          jsons.add(value);
        }
      }

      IOperationResult result = new MultipleResult.Builder().addMeta("total-pages", 1).addMeta("total-elements", jsons.size()).addMeta("current-page", 0).addMeta("current-elements", jsons.size())
          .data(toOperationData(jsons)).build();

      return result;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new OperationException(e);
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult deleteAllNotifications(JsonObject query) throws OperationException {
    try {
      allNotifications.clear();

      // dump all line to file
      StringBuffer buffer = dumpAllToString();
      Files.write(new File(notificationsFilePath).toPath(), buffer.toString().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

      SingleResult result = new SingleResult.Builder().addMeta("done", "1").build();
      return result;
    }
    catch (IOException e) {
      e.printStackTrace();
      throw new OperationException(e);
    }
  }


  /**
   * @{inheritedDoc}
   */
  @Override
  public IOperationResult markAllNotificationsReaden(JsonObject query) throws OperationException {
    try {
      allNotifications.forEach((key, value) -> value.addProperty("status", "READEN"));

      // dump all line to file
      StringBuffer buffer = dumpAllToString();
      Files.write(new File(notificationsFilePath).toPath(), buffer.toString().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);

      SingleResult result = new SingleResult.Builder().addMeta("done", "1").build();
      return result;
    }
    catch (IOException e) {
      e.printStackTrace();
      throw new OperationException(e);
    }
  }


  private List<OperationData> toOperationData(List<JsonObject> jsonObjects) {
    List<OperationData> datas = new ArrayList<OperationData>();
    for (JsonObject obj : jsonObjects) {
      OperationData operationData = new OperationData.Builder().attributes(JsonUtils.toMap(obj)).build();
      datas.add(operationData);
    }
    return datas;
  }


  private StringBuffer dumpAllToString() {
    StringBuffer buffer = new StringBuffer();
    for(String k : allNotifications.keySet()) {
      JsonObject o = allNotifications.get(k);
      String json = JsonUtils.toString(o);
      buffer.append(json);
      buffer.append("\n");
    }
    return buffer;
  }
}
