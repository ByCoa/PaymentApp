package mercadolibre.paymentapp.feature.paymentbank;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mercadolibre.paymentapp.PaymentApp;
import mercadolibre.paymentapp.R;
import mercadolibre.paymentapp.base.BaseViewHolder;
import mercadolibre.paymentapp.feature.paymentinstallments.PaymentInstallmentsActivity;
import mercadolibre.paymentapp.model.bank.Bank;
import mercadolibre.paymentapp.model.payment.Payment;

public class PaymentBankAdapter extends  RecyclerView.Adapter<PaymentBankAdapter.PaymentBankViewHolder> {

  private List<Bank> paymentBank = new ArrayList<>();

  @NonNull
  @Override
  public PaymentBankAdapter.PaymentBankViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
    View rootView = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.item_payment, viewGroup, false);
    return new PaymentBankAdapter.PaymentBankViewHolder(rootView);
  }

  @Override
  public void onBindViewHolder(@NonNull PaymentBankAdapter.PaymentBankViewHolder rewardViewHolder, int position) {
    rewardViewHolder.bind(paymentBank.get(position));
  }

  @Override
  public int getItemCount() {
    return paymentBank.size();
  }

  public void addItems(List<Bank> rewardItems) {
    paymentBank.addAll(rewardItems);
    notifyDataSetChanged();
  }

  class PaymentBankViewHolder extends BaseViewHolder {

    @BindView(R.id.textName)
    TextView textCardName;

    @BindView(R.id.imgLogo)
    ImageView imgCard;

    PaymentBankViewHolder (@NonNull View itemView) {
      super(itemView);
    }

    @Override
    public void onClick(View view) {
      Payment payment = PaymentApp.getPayment();
      payment.setBank(paymentBank.get(getAdapterPosition()));
      Intent paymentAmountIntent = new Intent(view.getContext(), PaymentInstallmentsActivity.class);
      view.getContext().startActivity(paymentAmountIntent);
    }

    void bind(Bank bank) {
      Picasso.with(imgCard.getContext())
          .load(bank.getThumbnail())
          .placeholder(R.drawable.ic_not_found)
          .into(imgCard);
      textCardName.setText(bank.getName());
    }
  }
}