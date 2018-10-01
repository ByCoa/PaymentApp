package mercadolibre.paymentapp.model.installment;

import java.util.List;

import mercadolibre.paymentapp.model.bank.Bank;

public class Installment {

  private String paymentMethodId;
  private String paymentTypeId;
  private Bank issuer;
  private String processingMode;
  private Object merchantAccountId;
  private List<PayerCost> payerCosts;

  public String getPaymentMethodId() {
    return paymentMethodId;
  }

  public void setPaymentMethodId(String paymentMethodId) {
    this.paymentMethodId = paymentMethodId;
  }

  public String getPaymentTypeId() {
    return paymentTypeId;
  }

  public void setPaymentTypeId(String paymentTypeId) {
    this.paymentTypeId = paymentTypeId;
  }

  public Bank getBank() {
    return issuer;
  }

  public void setBank(Bank issuer) {
    this.issuer = issuer;
  }

  public String getProcessingMode() {
    return processingMode;
  }

  public void setProcessingMode(String processingMode) {
    this.processingMode = processingMode;
  }

  public Object getMerchantAccountId() {
    return merchantAccountId;
  }

  public void setMerchantAccountId(Object merchantAccountId) {
    this.merchantAccountId = merchantAccountId;
  }

  public List<PayerCost> getPayerCosts() {
    return payerCosts;
  }

  public void setPayerCosts(List<PayerCost> payerCosts) {
    this.payerCosts = payerCosts;
  }

}
