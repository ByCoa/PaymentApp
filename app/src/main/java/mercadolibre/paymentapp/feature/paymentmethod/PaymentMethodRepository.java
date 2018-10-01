package mercadolibre.paymentapp.feature.paymentmethod;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import mercadolibre.paymentapp.model.card.Card;
import mercadolibre.paymentapp.storage.datasource.paymentmethod.PaymentMethodDataSource;

public class PaymentMethodRepository {
  private PaymentMethodDataSource paymentMethodDataSource;
  private static final String CREDIT_CARD = "credit_card";

  public PaymentMethodRepository(PaymentMethodDataSource paymentMethodDataSource) {
    this.paymentMethodDataSource = paymentMethodDataSource;
  }

  public Observable<List<Card>> getPaymentMethods(){
    return paymentMethodDataSource.getPaymentMethods()
        .flatMap(cards -> {
          List<Card> creditCards = new ArrayList<>();
          for (Card card: cards) {
            if (card.getPaymentTypeId().equalsIgnoreCase(CREDIT_CARD)){
              creditCards.add(card);
            }
          }
          return Observable.just(creditCards);
        });
  }


}
