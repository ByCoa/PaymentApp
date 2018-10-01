package mercadolibre.paymentapp.storage.datasource.paymentmethod;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import mercadolibre.paymentapp.model.bank.Bank;
import mercadolibre.paymentapp.model.card.Card;
import mercadolibre.paymentapp.model.installment.Installment;
import mercadolibre.paymentapp.network.service.PaymentMethodService;

public class PaymentMethodRemoteDataSource implements PaymentMethodDataSource {

  private PaymentMethodService paymentMethodService;

  public PaymentMethodRemoteDataSource(PaymentMethodService paymentMethodService) {
    this.paymentMethodService = paymentMethodService;
  }

  @Override
  public Observable<List<Card>> getPaymentMethods() {
    return paymentMethodService.getPaymentMethods()
         .observeOn(AndroidSchedulers.mainThread());
  }

  @Override
  public Observable<List<Bank>> getPaymentBanks(String typeCardId) {
    return paymentMethodService.getBanks(typeCardId)
        .observeOn(AndroidSchedulers.mainThread());
  }

  @Override
  public Observable<List<Installment>> getInstallments(String typeCardId, String bankId) {
    return paymentMethodService.getInstallments(typeCardId,bankId)
        .observeOn(AndroidSchedulers.mainThread());
  }
}
