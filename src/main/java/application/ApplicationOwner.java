package application;

import application.Application;


public class ApplicationOwner {

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




}
