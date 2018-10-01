package mercadolibre.paymentapp.network.service;

import java.util.List;

import io.reactivex.Observable;
import mercadolibre.paymentapp.model.bank.Bank;
import mercadolibre.paymentapp.model.card.Card;
import mercadolibre.paymentapp.model.installment.Installment;
import mercadolibre.paymentapp.util.PaymentAppConfig;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PaymentMethodService {

  @GET(PaymentAppConfig.Network.VersionEndpoint.V1 + PaymentAppConfig.Network.PaymentMethodEndpoints.PAYMENT_METHODS)
  Observable<List<Card>> getPaymentMethods();

  @GET(PaymentAppConfig.Network.VersionEndpoint.V1 + PaymentAppConfig.Network.PaymentMethodEndpoints.CARD_ISSUER)
  Observable<List<Bank>> getBanks(@Query("payment_method_id") String payment_method_id);

  @GET(PaymentAppConfig.Network.VersionEndpoint.V1 + PaymentAppConfig.Network.PaymentMethodEndpoints.INSTALLMENTS)
  Observable<List<Installment>> getInstallments(@Query("payment_method_id") String payment_method_id, @Query("issuer.id") String issuerId);
}
