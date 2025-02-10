package device;

import application.Application;
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

  public void installApplicationInTheDevice(Application application) {
    //may be add validation, app already installed
    if (applicationsInstalled.get(application.getApplicationId()) != null) {
      //throw exception, installing duplicate app
    } else {
      String latestVersion = application.getLatestVersion();
      ApplicationFile latestApplicationFile = application.getApplicationFileVersionMap().get(latestVersion);
      applicationsInstalled.put(application.getApplicationId(), latestApplicationFile);
      System.out.println("The device with id: " + deviceId + " is installed with application :" +
          application.getApplicationId() + ". It is with latest version : " + application.getLatestVersion() +
          ". Its content are : " + latestApplicationFile.getMetadata());

      // Update application as well with the device
      application.getDevicesMap().put(deviceId, this);
      System.out.println("The application :" + application.getApplicationId() + " is added to device :" +
          deviceId + ". Total applications installed in device : " + applicationsInstalled.size() +
          ". Total devices with this application: " + application.getDevicesMap().size());
    }
  }




}
