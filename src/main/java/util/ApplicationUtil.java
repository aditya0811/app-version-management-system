package util;

import application.Application;
import application.ApplicationFile;
import device.Device;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ApplicationUtil {

  public static void installApplicationInTheDevice(Application application, Device device) {
    //may be add validation, app already installed
    Map<String,ApplicationFile> applicationsFileMapInstalledInDevice = device.getApplicationsInstalled();
    if (applicationsFileMapInstalledInDevice.get(application.getApplicationId()) != null) {
      //throw exception, installing duplicate app
    } else {
      String latestVersion = application.getLatestVersion();
      ApplicationFile latestApplicationFile = application.getApplicationFileVersionMap().get(latestVersion);
      applicationsFileMapInstalledInDevice.put(application.getApplicationId(), latestApplicationFile);
      System.out.println("The device with id: " + device.getDeviceId() + " is installed with application :" +
          application.getApplicationId() + ". It is with latest version : " + application.getLatestVersion() +
          ". Its content are : " + latestApplicationFile.getMetadata());

      // Update application as well with the device
      application.getDevicesMap().put(device.getDeviceId(), device);
      System.out.println("The application :" + application.getApplicationId() + " is added to device :" +
          device.getDeviceId() + ". Total applications installed in device : " + device.getApplicationsInstalled().size() +
          ". Total devices with this application: " + application.getDevicesMap().size());
    }
  }



}
