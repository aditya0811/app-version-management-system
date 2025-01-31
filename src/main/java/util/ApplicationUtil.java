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


  //As updated application is synonymous with updating application
  public static void updateApplicationWithBeta(List<String> specificDevice, Application application,
      ApplicationFile applicationFile) {
    //First step is updating application.Application itself

    application.setLatestVersion(applicationFile.getVersionId());
    application.getApplicationFileVersionMap().put(applicationFile.getVersionId(), applicationFile);

    Map<String, Device> devicesInstalledWithApplication =  application.getDevicesMap();
    List<String> devicesToBeUpdated = new ArrayList<>();
    for (String deviceId : specificDevice) {
      if(devicesInstalledWithApplication.containsKey(deviceId)) {
        devicesToBeUpdated.add(deviceId);
      }
    }

    updateDevicesWithApplication(application.getDevicesMap(), devicesToBeUpdated, application, applicationFile);

  }



  //TODO BELOW THREE METHODS CAN BE CHANGED TO DIFFERENT CLASSES, INTRODUCING INTERFACE, AND BRING THIS RESPONSIBILITY
  // TO APPLICATION OWNER
  public static void updateApplicationWithPercentage(Application application,
      ApplicationFile applicationFile, int value) {
    //First step is updating application.Application itself

    application.setLatestVersion(applicationFile.getVersionId());
    application.getApplicationFileVersionMap().put(applicationFile.getVersionId(), applicationFile);
    Map<String, Device> devicesInstalledWithApplication =  application.getDevicesMap();
    List<String> devicesToBeUpdated = new ArrayList<>();
    int totalDevices = devicesInstalledWithApplication.size();
    int countOfDevicesToBeUpdated = (totalDevices*value)/100;
    for(String deviceInApplication : devicesInstalledWithApplication.keySet()){
        if(countOfDevicesToBeUpdated>0) {
          devicesToBeUpdated.add(deviceInApplication);
          countOfDevicesToBeUpdated--;
        } else {
          break;
        }
    }

    updateDevicesWithApplication(application.getDevicesMap(), devicesToBeUpdated, application, applicationFile);

  }

  public static void updateApplicationWithAllDevices(Application application,
      ApplicationFile applicationFile) {
    //First step is updating application.Application itself

    application.setLatestVersion(applicationFile.getVersionId());
    application.getApplicationFileVersionMap().put(applicationFile.getVersionId(), applicationFile);

    Map<String, Device> devicesInstalledWithApplication =  application.getDevicesMap();
    List<String> devicesToBeUpdated = new ArrayList<>(devicesInstalledWithApplication.keySet());
    updateDevicesWithApplication(application.getDevicesMap(), devicesToBeUpdated, application, applicationFile);

  }

  private static void updateDevicesWithApplication(Map<String,Device> devicesInstalledWithApplication,
      List<String> devicesToBeUpdated, Application application, ApplicationFile applicationFile) {
    for (String deviceId : devicesToBeUpdated) {
      Device deviceToUpdate = devicesInstalledWithApplication.get(deviceId);

      Map<String, ApplicationFile> applicationFileMapInDevice = deviceToUpdate.
          getApplicationsInstalled();

      //get old application file
      ApplicationFile applicationFileInstalledInDevice = applicationFileMapInDevice.get(application.getApplicationId());

      //Substitute it with new application.ApplicationFile version
      ApplicationFile applicationFileDiff = getApplicationFileDiffMetadata(applicationFile, applicationFileInstalledInDevice);
      applicationFileMapInDevice.put(application.getApplicationId(),applicationFileDiff);
      System.out.println("This application with id: " +application.getApplicationId() + " is replaced in "
          + "device with device id: " + deviceId + "from old version : "
          + applicationFileInstalledInDevice.getVersionId() + " to the new version : "
          + applicationFile.getVersionId());
    }

  }

  private static ApplicationFile getApplicationFileDiffMetadata(ApplicationFile applicationFileOld,
      ApplicationFile applicationFileNew) {

    return new ApplicationFile(applicationFileNew.getVersionId(),
        applicationFileNew.getVersionId()+"-d-" + applicationFileOld.getVersionId());
  }
}
