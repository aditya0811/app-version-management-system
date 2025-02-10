package updates;

import application.ApplicationFile;
import device.Device;
import java.util.List;


public interface ApplicationUpdate {
  void updateApplication(List<String> devicesList, ApplicationFile newApplicationFile);
  void updateApplication(int k, ApplicationFile newApplicationFile);
  void updateApplication(ApplicationFile newApplicationFile);
}
