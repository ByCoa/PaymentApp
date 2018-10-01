package mercadolibre.paymentapp.feature.paymentinstallments;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import mercadolibre.paymentapp.PaymentApp;
import mercadolibre.paymentapp.R;
import mercadolibre.paymentapp.base.BaseViewHolder;
import mercadolibre.paymentapp.feature.paymentamout.PaymentAmountActivity;
import mercadolibre.paymentapp.model.installment.PayerCost;
import mercadolibre.paymentapp.model.payment.Payment;

public class PaymentInstallmentsAdapter extends  RecyclerView.Adapter<PaymentInstallmentsAdapter.PaymentInstallmentsViewHolder> {

private List<PayerCost> paymentInstallment = new ArrayList<>();

  @NonNull
  @Override
  public PaymentInstallmentsAdapter.PaymentInstallmentsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
      View rootView = LayoutInflater.from(viewGroup.getContext())
      .inflate(R.layout.item_payment, viewGroup, false);
      return new PaymentInstallmentsAdapter.PaymentInstallmentsViewHolder(rootView);
      }

  @Override
  public void onBindViewHolder(@NonNull PaymentInstallmentsAdapter.PaymentInstallmentsViewHolder rewardViewHolder, int position) {
      rewardViewHolder.bind(paymentInstallment.get(position));
      }

  @Override
  public int getItemCount() {
      return paymentInstallment.size();
      }

  public void addItems(List<PayerCost> rewardItems) {
      paymentInstallment.addAll(rewardItems);
      notifyDataSetChanged();
      }

  class PaymentInstallmentsViewHolder extends BaseViewHolder {

    @BindView(R.id.textName)
    TextView textCardName;

    @BindView(R.id.imgLogo)
    ImageView imgCard;

    PaymentInstallmentsViewHolder (@NonNull View itemView) {
      super(itemView);
    }

    @Override
    public void onClick(View view) {
      Payment payment = PaymentApp.getPayment();
      payment.setPayerCost(paymentInstallment.get(getAdapterPosition()));
      Intent paymentAmountIntent = new Intent(view.getContext(), PaymentAmountActivity.class);
      view.getContext().startActivity(paymentAmountIntent);
    }

    void bind(PayerCost payerCost) {
      imgCard.setVisibility(View.GONE);
      textCardName.setText(payerCost.getRecommendedMessage());
    }
  }
}