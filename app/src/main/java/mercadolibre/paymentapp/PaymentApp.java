package mercadolibre.paymentapp;

import android.app.Application;

import mercadolibre.paymentapp.model.payment.Payment;

public class PaymentApp extends Application {
  private static Payment payment;

  @Override
  public void onCreate() {
    super.onCreate();
    payment = new Payment();
  }

  public static Payment getPayment() {
    return payment;
  }
}
