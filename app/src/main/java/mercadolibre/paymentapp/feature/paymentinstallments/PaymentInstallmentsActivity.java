package mercadolibre.paymentapp.feature.paymentinstallments;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;

import java.util.List;

import butterknife.BindView;
import mercadolibre.paymentapp.PaymentApp;
import mercadolibre.paymentapp.R;
import mercadolibre.paymentapp.ViewModelFactory;
import mercadolibre.paymentapp.base.BaseActivity;
import mercadolibre.paymentapp.model.installment.Installment;
import mercadolibre.paymentapp.model.installment.PayerCost;
import mercadolibre.paymentapp.network.api.ApiResource;

public class PaymentInstallmentsActivity extends BaseActivity {
  @BindView(R.id.recyclerPayment)
  RecyclerView recyclerPaymentInstallments;

  private PaymentInstallmentsAdapter paymentInstallmentsAdapter;
  private PaymentInstallmentsViewModel paymentInstallmentsViewModel;
  private String typeCardId;
  private String bankId;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_payment);
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
    getSupportActionBar().setTitle(R.string.installment_info);
    typeCardId = PaymentApp.getPayment().getCard().getId();
    bankId = PaymentApp.getPayment().getBank().getId();
    setupViewModel();
    setupPaymentInstallmentsList();
    setupPaymentInstallments();
    paymentInstallmentsViewModel.fetchPaymentInstallments(getLifecycle(),typeCardId,bankId);
  }

  @SuppressWarnings("ConstantConditions")
  private void setupViewModel() {
    paymentInstallmentsViewModel = ViewModelFactory.getPaymentInstallments(this);
  }

  private void setupPaymentInstallmentsList() {
    paymentInstallmentsAdapter = new PaymentInstallmentsAdapter();
    recyclerPaymentInstallments.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    recyclerPaymentInstallments.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
  }

  private void setupPaymentInstallments(){
    paymentInstallmentsViewModel.getPaymentInstallments().observe(this,this::handlePaymentInstallments);
  }

  private void handlePaymentInstallments(ApiResource<List<PayerCost>> resource) {
    Log.d("Rsource", ""+resource.status);
    switch (resource.status) {
      case LOADING:
        showProgressBar();
        break;
      case SUCCESS:
        hideProgressBar();
        recyclerPaymentInstallments.setAdapter(paymentInstallmentsAdapter);
        paymentInstallmentsAdapter.addItems(resource.data);
        break;
      case ERROR:
        hideProgressBar();
        break;
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
    }

    return super.onOptionsItemSelected(item);
  }
}
