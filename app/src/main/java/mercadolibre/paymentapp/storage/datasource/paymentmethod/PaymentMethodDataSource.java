package mercadolibre.paymentapp.storage.datasource.paymentmethod;

import java.util.List;


import io.reactivex.Observable;
import mercadolibre.paymentapp.model.bank.Bank;
import mercadolibre.paymentapp.model.card.Card;
import mercadolibre.paymentapp.model.installment.Installment;

public interface PaymentMethodDataSource {
  Observable<List<Card>> getPaymentMethods();

  Observable<List<Bank>> getPaymentBanks(String typeCardId);

  Observable<List<Installment>> getInstallments (String typeCardId, String bankId);
}
