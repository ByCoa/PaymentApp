package mercadolibre.paymentapp.feature.paymentmethod;

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
import mercadolibre.paymentapp.feature.paymentbank.PaymentBankActivity;
import mercadolibre.paymentapp.model.card.Card;
import mercadolibre.paymentapp.model.payment.Payment;

public class PaymentMethodAdapter extends RecyclerView.Adapter<PaymentMethodAdapter.PaymentMethodViewHolder> {

  private List<Card> paymentMethod = new ArrayList<>();

  @NonNull
  @Override
  public PaymentMethodViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
    View rootView = LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.item_payment, viewGroup, false);
    return new PaymentMethodViewHolder(rootView);
  }

  @Override
  public void onBindViewHolder(@NonNull PaymentMethodViewHolder rewardViewHolder, int position) {
    rewardViewHolder.bind(paymentMethod.get(position));
  }

  @Override
  public int getItemCount() {
    return paymentMethod.size();
  }

  public void addItems(List<Card> rewardItems) {
    paymentMethod.addAll(rewardItems);
    notifyDataSetChanged();
  }

  class PaymentMethodViewHolder extends BaseViewHolder {

    @BindView(R.id.textName)
    TextView textCardName;

    @BindView(R.id.imgLogo)
    ImageView imgCard;

    PaymentMethodViewHolder(@NonNull View itemView) {
      super(itemView);
    }

    @Override
    public void onClick(View view) {
      Payment payment = PaymentApp.getPayment();
      payment.setCard(paymentMethod.get(getAdapterPosition()));
      Intent paymentAmountIntent = new Intent(view.getContext(), PaymentBankActivity.class);
      view.getContext().startActivity(paymentAmountIntent);
    }

    void bind(Card card) {
      Picasso.with(imgCard.getContext())
          .load(card.getThumbnail())
          .placeholder(R.drawable.ic_not_found)
          .into(imgCard);
      textCardName.setText(card.getName());
    }
  }
}
