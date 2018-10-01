package mercadolibre.paymentapp.util;

import android.os.Build;

import mercadolibre.paymentapp.BuildConfig;

public interface PaymentAppConfig {

  interface Constants {
    interface Parameters {
      String AMOUNT = "amount";
      String TYPE_CARD_ID = "type_card_id";
    }
  }

  interface Network {
    interface VersionEndpoint {
      String V1 = "v1/";
    }

    interface PaymentMethodEndpoints {
      String PAYMENT_METHODS = "payment_methods"+ BuildConfig.PUBLIC_KEY;
      String CARD_ISSUER = "payment_methods/card_issuers"+ BuildConfig.PUBLIC_KEY;
      String INSTALLMENTS = "payment_methods/installments" + BuildConfig.PUBLIC_KEY;
    }
  }

  interface Validation {
    interface CreditCard {
      int MAX_ALLOWED_AMOUNT = 8000;
      double MIN_ALLOWED_AMOUNT = 0.01;
    }
  }
}
