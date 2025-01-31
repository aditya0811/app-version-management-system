package application;

public class ApplicationFile {
  private String versionId;
  private String metadata;

  public ApplicationFile(String versionId, String metadata) {
    this.versionId = versionId;
    this.metadata = metadata;
  }

  public String getVersionId() {
    return versionId;
  }

  public void setVersionId(String versionId) {
    this.versionId = versionId;
  }

  public String getMetadata() {
    return metadata;
  }

  public void setMetadata(String metadata) {
    this.metadata = metadata;
  }
}
