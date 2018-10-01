package mercadolibre.paymentapp.model.bank;

public class Bank {

  private String id;
  private String name;
  private String secureThumbnail;
  private String thumbnail;
  private String processingMode;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSecureThumbnail() {
    return secureThumbnail;
  }

  public void setSecureThumbnail(String secureThumbnail) {
    this.secureThumbnail = secureThumbnail;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  public String getProcessingMode() {
    return processingMode;
  }

  public void setProcessingMode(String processingMode) {
    this.processingMode = processingMode;
  }

}
