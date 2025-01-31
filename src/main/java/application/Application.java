package application;

import device.Device;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Application {
  private String applicationId;
  private Map<String, ApplicationFile> applicationFileVersionMap = new HashMap<>();

  private String latestVersion;

  public Map<String, Device> getDevicesMap() {
    return devicesMap;
  }

  public void setDevicesMap(Map<String, Device> devicesMap) {
    this.devicesMap = devicesMap;
  }

  private Map<String, Device> devicesMap = new HashMap<>();

  public Application(String applicationId, Map<String, ApplicationFile> applicationFileVersionMap,
      String latestVersion) {
    this.applicationId = applicationId;
    this.applicationFileVersionMap = applicationFileVersionMap;
    this.latestVersion = latestVersion;
  }

  public String getApplicationId() {
    return applicationId;
  }

  public void setApplicationId(String applicationId) {
    this.applicationId = applicationId;
  }

  public Map<String, ApplicationFile> getApplicationFileVersionMap() {
    return applicationFileVersionMap;
  }

  public void setApplicationFileVersionMap(Map<String, ApplicationFile> applicationFileVersionMap) {
    this.applicationFileVersionMap = applicationFileVersionMap;
  }

  public String getLatestVersion() {
    return latestVersion;
  }

  public void setLatestVersion(String latestVersion) {
    this.latestVersion = latestVersion;
  }
}
