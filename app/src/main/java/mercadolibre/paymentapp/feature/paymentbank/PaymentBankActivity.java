package mercadolibre.paymentapp.feature.paymentbank;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.List;

import butterknife.BindView;
import mercadolibre.paymentapp.PaymentApp;
import mercadolibre.paymentapp.R;
import mercadolibre.paymentapp.ViewModelFactory;
import mercadolibre.paymentapp.base.BaseActivity;
import mercadolibre.paymentapp.model.bank.Bank;
import mercadolibre.paymentapp.network.api.ApiResource;

public class PaymentBankActivity  extends BaseActivity {
  @BindView(R.id.recyclerPayment)
  RecyclerView recyclerPaymentBank;

  private PaymentBankAdapter paymentBankAdapter;
  private PaymentBankViewModel paymentBankViewModel;
  private String typeCardId;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_payment);
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
    getSupportActionBar().setTitle(R.string.bank_info);
    typeCardId = PaymentApp.getPayment().getCard().getId();
    setupViewModel();
    setupPaymentBankList();
    setupPaymentBanks();
    paymentBankViewModel.fetchPaymentBank(getLifecycle(),typeCardId);
  }

  @SuppressWarnings("ConstantConditions")
  private void setupViewModel() {
    paymentBankViewModel = ViewModelFactory.getPaymentBank(this);
  }

  private void setupPaymentBankList() {
    paymentBankAdapter = new PaymentBankAdapter();
    recyclerPaymentBank.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    recyclerPaymentBank.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
  }

  private void setupPaymentBanks(){
    paymentBankViewModel.getPaymentBanks().observe(this,this::handlePaymentBanks);
  }

  private void handlePaymentBanks(ApiResource<List<Bank>> resource) {
    switch (resource.status) {
      case LOADING:
        showProgressBar();
        break;
      case SUCCESS:
        hideProgressBar();
        recyclerPaymentBank.setAdapter(paymentBankAdapter);
        paymentBankAdapter.addItems(resource.data);
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

