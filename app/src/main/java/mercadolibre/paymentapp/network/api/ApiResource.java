package mercadolibre.paymentapp.network.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class ApiResource<T> {

  @NonNull
  public final Status status;
  @Nullable
  public final T data;
  @Nullable
  public final String message;
  private ApiResource(@NonNull Status status, @Nullable T data,
                      @Nullable String message) {
    this.status = status;
    this.data = data;
    this.message = message;
  }

  public static <T> ApiResource<T> success(@NonNull T data) {
    return new ApiResource<>(Status.SUCCESS, data, null);
  }

  public static <T> ApiResource<T> error(String msg, @Nullable T data) {
    return new ApiResource<>(Status.ERROR, data, msg);
  }

  public static <T> ApiResource<T> loading() {
    return new ApiResource<>(Status.LOADING, null, null);
  }

  public enum Status { SUCCESS, ERROR, LOADING }
}