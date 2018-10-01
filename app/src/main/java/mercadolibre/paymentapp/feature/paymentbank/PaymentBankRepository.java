package mercadolibre.paymentapp.feature.paymentbank;

import java.util.List;

import io.reactivex.Observable;
import mercadolibre.paymentapp.model.bank.Bank;
import mercadolibre.paymentapp.storage.datasource.paymentmethod.PaymentMethodDataSource;

public class PaymentBankRepository {
  private PaymentMethodDataSource paymentMethodDataSource;

  public PaymentBankRepository(PaymentMethodDataSource paymentMethodDataSource) {
    this.paymentMethodDataSource = paymentMethodDataSource;
  }

  public Observable<List<Bank>> getPaymentBank(String typeCardId){
    return paymentMethodDataSource.getPaymentBanks(typeCardId);
  }

}
