package mercadolibre.paymentapp.network.api;

import android.support.annotation.NonNull;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import mercadolibre.paymentapp.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {
  private static Retrofit.Builder builder = getRetrofitBuilder();
  private static final OkHttpClient okBuilder = getOkHttpClientInstance().build();
  private static final Retrofit retrofitPlain = builder.baseUrl(BuildConfig.HOST).client(okBuilder).build();

  private static Gson gson = new Gson();

  private static Retrofit.Builder getRetrofitBuilder() {
    return new Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(getGson()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync());
  }

  private static OkHttpClient.Builder getOkHttpClientInstance() {
    OkHttpClient.Builder client = new OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS);

    client.addInterceptor(new HttpLoggingInterceptor().setLevel(getHttpLogLevel()));

    return client;
  }

  @NonNull
  private static HttpLoggingInterceptor.Level getHttpLogLevel() {
    return BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE;
  }

  public static <T> T createService(Class<T> serviceClass) {
    return  retrofitPlain.create(serviceClass);
  }

  private static Gson getGson() {
    if (gson == null) {
      gson = new GsonBuilder()
          .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
          .create();
    }
    return gson;
  }
}
