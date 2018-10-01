package mercadolibre.paymentapp.feature.paymentamout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.BindView;
import mercadolibre.paymentapp.PaymentApp;
import mercadolibre.paymentapp.R;
import mercadolibre.paymentapp.base.BaseActivity;
import mercadolibre.paymentapp.feature.paymentmethod.PaymentMethodActivity;
import mercadolibre.paymentapp.feature.paymentresume.PaymentResumeDialog;
import mercadolibre.paymentapp.model.payment.Payment;
import mercadolibre.paymentapp.util.PaymentAppConfig;

public class PaymentAmountActivity extends BaseActivity {

  @BindView(R.id.editAmount)
  EditText editAmount;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_payment_amount);
    getSupportActionBar().setTitle(R.string.amount_info);
    Payment payment = PaymentApp.getPayment();
    if (payment.getAmount() != null && payment.getBank()!= null && payment.getCard()!= null && payment.getPayerCost()!= null){
      PaymentResumeDialog.show(getSupportFragmentManager());
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.save_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_save) {
      saveAmount();
    }
    return super.onOptionsItemSelected(item);
  }

  @SuppressLint("StringFormatMatches")
  private void saveAmount() {
    if (!TextUtils.isEmpty(editAmount.getText().toString())) {
      hideKeyboard();
      disableError();
      Double amount = Double.valueOf(editAmount.getText().toString());
      if (amount> PaymentAppConfig.Validation.CreditCard.MAX_ALLOWED_AMOUNT){
        showMessage(getString(R.string.error_amount_exceed,PaymentAppConfig.Validation.CreditCard.MAX_ALLOWED_AMOUNT));
        return;
      } else if (amount< PaymentAppConfig.Validation.CreditCard.MIN_ALLOWED_AMOUNT){
        showMessage(getString(R.string.error_amount_exceed, PaymentAppConfig.Validation.CreditCard.MIN_ALLOWED_AMOUNT));
        return;
      }
      Payment payment = PaymentApp.getPayment();
      payment.setAmount(amount);
      Intent paymentAmountIntent = new Intent(this, PaymentMethodActivity.class);
      startActivity(paymentAmountIntent);

    } else {
      showEmptyError();
    }
  }

  public void showEmptyError() {
    editAmount.setError(getString(R.string.error_amount_empty));
    editAmount.requestFocus();
  }

  public void disableError() {
    editAmount.setError(null);
  }
}
