package mercadolibre.paymentapp.feature.paymentresume;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mercadolibre.paymentapp.PaymentApp;
import mercadolibre.paymentapp.R;
import mercadolibre.paymentapp.model.payment.Payment;

public class PaymentResumeDialog extends DialogFragment {
  private static final int MARGIN_DP = 30;

  @BindView(R.id.textAmount)
  TextView textAmount;

  @BindView(R.id.textCardName)
  TextView textCardName;

  @BindView(R.id.imgLogoCard)
  ImageView imgLogoCard;

  @BindView(R.id.textBankName)
  TextView textBankName;

  @BindView(R.id.imgLogoBank)
  ImageView imgLogoBank;

  @BindView(R.id.textInstallment)
  TextView textInstallment;

  @BindView(R.id.btnDismiss)
  AppCompatButton btnDismiss;

  private Unbinder unbinder;

  public static void show(FragmentManager fragmentManager) {
    PaymentResumeDialog loginDialog = newInstance();
    loginDialog.show(fragmentManager, PaymentResumeDialog.class.getSimpleName());
  }

  public static PaymentResumeDialog newInstance() {
    return new PaymentResumeDialog();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    return setupDialog(savedInstanceState);
  }

  @NonNull
  private Dialog setupDialog(@Nullable Bundle savedInstanceState) {
    int dp = (int) (MARGIN_DP * getResources().getDisplayMetrics().density);
    Dialog dialog = super.onCreateDialog(savedInstanceState);
    InsetDrawable background = new InsetDrawable(new ColorDrawable(Color.TRANSPARENT), dp);
    if (dialog.getWindow() != null) dialog.getWindow().setBackgroundDrawable(background);
    return dialog;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.dialog_payment_resume, container, false );
    unbinder = ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Payment payment = PaymentApp.getPayment();
    textAmount.setText(String.valueOf(payment.getAmount()));
    textCardName.setText(payment.getCard().getName());
    textBankName.setText(payment.getBank().getName());
    textInstallment.setText(payment.getPayerCost().getRecommendedMessage());
    Picasso.with(imgLogoCard.getContext())
        .load(payment.getCard().getThumbnail())
        .placeholder(R.drawable.ic_not_found)
        .into(imgLogoCard);
    Picasso.with(imgLogoBank.getContext())
        .load(payment.getBank().getThumbnail())
        .placeholder(R.drawable.ic_not_found)
        .into(imgLogoBank);
  }

  @Override
  public void onStart() {
    super.onStart();
    Dialog dialog = getDialog();
    if (dialog != null) {
      dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      dialog.setCancelable(false);
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    if (unbinder != null)
      unbinder.unbind();
  }

  @OnClick(R.id.btnDismiss)
  public void onDismissClicked(){
    Payment payment = PaymentApp.getPayment();
    payment.setPayerCost(null);
    payment.setBank(null);
    payment.setAmount(null);
    payment.setCard(null);
    dismiss();
  }
}
