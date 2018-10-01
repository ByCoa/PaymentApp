package mercadolibre.paymentapp.model.payment;

import mercadolibre.paymentapp.model.bank.Bank;
import mercadolibre.paymentapp.model.card.Card;
import mercadolibre.paymentapp.model.installment.Installment;
import mercadolibre.paymentapp.model.installment.PayerCost;

public class Payment {
  private Double amount;
  private Card card;
  private Bank bank;
  private PayerCost payerCost;

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public Card getCard() {
    return card;
  }

  public void setCard(Card card) {
    this.card = card;
  }

  public Bank getBank() {
    return bank;
  }

  public void setBank(Bank bank) {
    this.bank = bank;
  }

  public PayerCost getPayerCost() {
    return payerCost;
  }

  public void setPayerCost(PayerCost payerCost) {
    this.payerCost = payerCost;
  }
}
