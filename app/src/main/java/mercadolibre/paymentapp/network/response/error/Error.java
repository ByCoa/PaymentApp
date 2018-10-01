package mercadolibre.paymentapp.network.response.error;

import java.util.List;

public class Error {

  private String message;
  private String error;
  private Integer status;
  private List<Cause> cause;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public List<Cause> getCause() {
    return cause;
  }

  public void setCause(List<Cause> cause) {
    this.cause = cause;
  }

}
