package mercadolibre.paymentapp.model.installment;

import java.util.List;

public class PayerCost {

  private Integer installments;
  private Integer installmentRate;
  private Integer discountRate;
  private List<String> labels = null;
  private List<String> installmentRateCollector = null;
  private Integer minAllowedAmount;
  private Integer maxAllowedAmount;
  private String recommendedMessage;
  private Integer installmentAmount;
  private Integer totalAmount;

  public Integer getInstallments() {
    return installments;
  }

  public void setInstallments(Integer installments) {
    this.installments = installments;
  }

  public Integer getInstallmentRate() {
    return installmentRate;
  }

  public void setInstallmentRate(Integer installmentRate) {
    this.installmentRate = installmentRate;
  }

  public Integer getDiscountRate() {
    return discountRate;
  }

  public void setDiscountRate(Integer discountRate) {
    this.discountRate = discountRate;
  }

  public List<String> getLabels() {
    return labels;
  }

  public void setLabels(List<String> labels) {
    this.labels = labels;
  }

  public List<String> getInstallmentRateCollector() {
    return installmentRateCollector;
  }

  public void setInstallmentRateCollector(List<String> installmentRateCollector) {
    this.installmentRateCollector = installmentRateCollector;
  }

  public Integer getMinAllowedAmount() {
    return minAllowedAmount;
  }

  public void setMinAllowedAmount(Integer minAllowedAmount) {
    this.minAllowedAmount = minAllowedAmount;
  }

  public Integer getMaxAllowedAmount() {
    return maxAllowedAmount;
  }

  public void setMaxAllowedAmount(Integer maxAllowedAmount) {
    this.maxAllowedAmount = maxAllowedAmount;
  }

  public String getRecommendedMessage() {
    return recommendedMessage;
  }

  public void setRecommendedMessage(String recommendedMessage) {
    this.recommendedMessage = recommendedMessage;
  }

  public Integer getInstallmentAmount() {
    return installmentAmount;
  }

  public void setInstallmentAmount(Integer installmentAmount) {
    this.installmentAmount = installmentAmount;
  }

  public Integer getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(Integer totalAmount) {
    this.totalAmount = totalAmount;
  }

}