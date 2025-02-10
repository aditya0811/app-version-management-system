import application.Application;
import application.ApplicationFile;
import application.ApplicationOwner;
import device.Device;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import util.ApplicationUtil;


public class Client {
  public static void main(String[] args) {
    ApplicationFile applicationFile =  new ApplicationFile("0.0","this is file content");
    Map<String, ApplicationFile> applicationFileMap = new HashMap<>();
    //can put validation here
    applicationFileMap.put(applicationFile.getVersionId(), applicationFile);

    //can put validation here, that the latest version should be in map, and atleast 1 value in map
    Application phonePeapplication = new Application("PhonePe", applicationFileMap, "0.0");

    Device device = new Device("deviceid1");
    ApplicationUtil.installApplicationInTheDevice(phonePeapplication, device);
    Device device2 = new Device("deviceid2");
    ApplicationUtil.installApplicationInTheDevice(phonePeapplication, device2);
    Device device3 = new Device("deviceid3");
    ApplicationUtil.installApplicationInTheDevice(phonePeapplication, device3);
    Device device4 = new Device("deviceid4");
    ApplicationUtil.installApplicationInTheDevice(phonePeapplication, device4);

    ApplicationOwner applicationOwner = new ApplicationOwner("phonePeOwner", phonePeapplication);

    ApplicationFile applicationFilePhonePe1 = new ApplicationFile("1.0", "this is version 1.0 for phonepe");
//    ApplicationUtil.updateApplicationWithAllDevices(phonePeapplication, applicationFilePhonePe1);
//    ApplicationUtil.updateApplicationWithPercentage(phonePeapplication, applicationFilePhonePe1, 30);
//    List<String> deviceBetaList = new ArrayList<>();
//    deviceBetaList.add("deviceid5");
//    ApplicationUtil.updateApplicationWithBeta(deviceBetaList, phonePeapplication, applicationFilePhonePe1);
//

//    applicationOwner.updateApplication(applicationFilePhonePe1);

  }
}
