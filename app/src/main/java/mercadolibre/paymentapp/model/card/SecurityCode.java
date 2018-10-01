package mercadolibre.paymentapp.model.card;

public class SecurityCode {
  private String mode;
  private String cardLocation;
  private Integer length;

  public String getMode() {
    return mode;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }

  public String getCardLocation() {
    return cardLocation;
  }

  public void setCardLocation(String cardLocation) {
    this.cardLocation = cardLocation;
  }

  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

}
