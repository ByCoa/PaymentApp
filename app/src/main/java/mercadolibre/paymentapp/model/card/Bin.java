package mercadolibre.paymentapp.model.card;

public class Bin {

  private String pattern;
  private String installmentsPattern;
  private Object exclusionPattern;

  public String getPattern() {
    return pattern;
  }

  public void setPattern(String pattern) {
    this.pattern = pattern;
  }

  public String getInstallmentsPattern() {
    return installmentsPattern;
  }

  public void setInstallmentsPattern(String installmentsPattern) {
    this.installmentsPattern = installmentsPattern;
  }

  public Object getExclusionPattern() {
    return exclusionPattern;
  }

  public void setExclusionPattern(Object exclusionPattern) {
    this.exclusionPattern = exclusionPattern;
  }

}
