package device;

import application.ApplicationFile;
import java.util.HashMap;
import java.util.Map;


public class Device {
  private String deviceId;

  // we need this as map, to get app version
  private Map<String, ApplicationFile> applicationsInstalled = new HashMap<>();

  public Device(String deviceId) {
    this.deviceId = deviceId;
    System.out.println("New device is created with device id: " + deviceId);
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public Map<String, ApplicationFile> getApplicationsInstalled() {
    return applicationsInstalled;
  }

  public void setApplicationsInstalled(Map<String, ApplicationFile> applicationsInstalled) {
    this.applicationsInstalled = applicationsInstalled;
  }




}
