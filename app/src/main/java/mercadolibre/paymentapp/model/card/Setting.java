package mercadolibre.paymentapp.model.card;

public class Setting {
  private SecurityCode securityCode;
  private CardNumber cardNumber;
  private Bin bin;

  public SecurityCode getSecurityCode() {
    return securityCode;
  }

  public void setSecurityCode(SecurityCode securityCode) {
    this.securityCode = securityCode;
  }

  public CardNumber getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(CardNumber cardNumber) {
    this.cardNumber = cardNumber;
  }

  public Bin getBin() {
    return bin;
  }

  public void setBin(Bin bin) {
    this.bin = bin;
  }

}
