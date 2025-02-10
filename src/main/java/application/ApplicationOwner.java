package application;

import device.Device;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import updates.ApplicationUpdate;


public class ApplicationOwner implements ApplicationUpdate {

  private String applicationOwnerId;


  private Application application;

  public ApplicationOwner(String applicationOwnerId, Application application) {
    this.applicationOwnerId = applicationOwnerId;
    this.application = application;
  }

  public String getApplicationOwnerId() {
    return applicationOwnerId;
  }

  public void setApplicationOwnerId(String applicationOwnerId) {
    this.applicationOwnerId = applicationOwnerId;
  }


  public Application getApplication() {
    return application;
  }

  public void setApplication(Application application) {
    this.application = application;
  }

  @Override
  public void updateApplication(List<String> devicesList, ApplicationFile newApplicationFile) {
    for (String deviceId : devicesList) {
      Device deviceToUpdate = this.application.getDevicesMap().get(deviceId);

      Map<String, ApplicationFile> applicationFileMapInDevice = deviceToUpdate.getApplicationsInstalled();

      //get old application file
      ApplicationFile applicationFileInstalledInDevice = applicationFileMapInDevice.get(application.getApplicationId());

      //Substitute it with new application.ApplicationFile version
      ApplicationFile applicationFileDiff = getApplicationFileDiffMetadata(newApplicationFile, applicationFileInstalledInDevice);
      applicationFileMapInDevice.put(application.getApplicationId(), applicationFileDiff);
      System.out.println("This application with id: " + application.getApplicationId() + " is replaced in " + "device with device id: "
          + deviceId + "from old version : " + applicationFileInstalledInDevice.getVersionId() + " to the new version : "
          + newApplicationFile.getVersionId());
    }
  }

  @Override
  public void updateApplication(int k, ApplicationFile newApplicationFile) {
    application.setLatestVersion(newApplicationFile.getVersionId());
    application.getApplicationFileVersionMap().put(newApplicationFile.getVersionId(),
        newApplicationFile);
    Map<String, Device> devicesInstalledWithApplication =  application.getDevicesMap();
    List<String> devicesToBeUpdated = new ArrayList<>();
    int totalDevices = devicesInstalledWithApplication.size();
    int countOfDevicesToBeUpdated = (totalDevices*k)/100;
    for(String deviceInApplication : devicesInstalledWithApplication.keySet()){
      if(countOfDevicesToBeUpdated>0) {
        devicesToBeUpdated.add(deviceInApplication);
        countOfDevicesToBeUpdated--;
      } else {
        break;
      }
    }
    updateApplication(devicesToBeUpdated, newApplicationFile);

  }

  @Override
  public void updateApplication(ApplicationFile newApplicationFile) {
    //First step is updating application.Application itself

    application.setLatestVersion(newApplicationFile.getVersionId());
    application.getApplicationFileVersionMap().put(newApplicationFile.getVersionId(), newApplicationFile);

    Map<String, Device> devicesInstalledWithApplication =  application.getDevicesMap();
    updateApplication(application.getDevicesMap().keySet().stream().toList(), newApplicationFile);

  }

  private ApplicationFile getApplicationFileDiffMetadata(ApplicationFile newApplicationFile,
      ApplicationFile oldApplicationFile) {

    return new ApplicationFile(newApplicationFile.getVersionId(),
        newApplicationFile.getVersionId()+"-d-" + oldApplicationFile.getVersionId());
  }


}
