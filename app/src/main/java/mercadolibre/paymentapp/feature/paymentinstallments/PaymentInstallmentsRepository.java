package mercadolibre.paymentapp.feature.paymentinstallments;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import mercadolibre.paymentapp.model.installment.Installment;
import mercadolibre.paymentapp.model.installment.PayerCost;
import mercadolibre.paymentapp.storage.datasource.paymentmethod.PaymentMethodDataSource;

public class PaymentInstallmentsRepository {
  private PaymentMethodDataSource paymentMethodDataSource;

  public PaymentInstallmentsRepository(PaymentMethodDataSource paymentMethodDataSource) {
    this.paymentMethodDataSource = paymentMethodDataSource;
  }

  public Observable<List<PayerCost>> getPaymentInstallment(String typeCardId, String bankId){
    return paymentMethodDataSource.getInstallments(typeCardId,bankId)
        .flatMap(installments -> {
          List<PayerCost> payerCosts = new ArrayList<>();
          if (installments != null) {
            for (Installment installment : installments) {
              for (PayerCost payerCost : installment.getPayerCosts()) {
                payerCosts.add(payerCost);
              }
            }
          }
          return Observable.just(payerCosts);
        });
  }

}
