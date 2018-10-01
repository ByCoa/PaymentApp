package mercadolibre.paymentapp.feature.paymentmethod;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.List;

import butterknife.BindView;
import mercadolibre.paymentapp.R;
import mercadolibre.paymentapp.ViewModelFactory;
import mercadolibre.paymentapp.base.BaseActivity;
import mercadolibre.paymentapp.model.card.Card;
import mercadolibre.paymentapp.network.api.ApiResource;

public class PaymentMethodActivity extends BaseActivity{
  @BindView(R.id.recyclerPayment)
  RecyclerView recyclerPaymentMethod;

  private PaymentMethodAdapter paymentMethodAdapter;
  private PaymentMethodAdapter.PaymentMethodViewHolder paymentMethodViewHolder;
  private PaymentMethodViewModel methodPaymentViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_payment);
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
    getSupportActionBar().setTitle(R.string.card_info);
    setupViewModel();
    setupPaymentMethodList();
    setupPaymentMethods();
    methodPaymentViewModel.fetchPaymentMethod(getLifecycle());
  }

  @SuppressWarnings("ConstantConditions")
  private void setupViewModel() {
    methodPaymentViewModel = ViewModelFactory.getPaymentMethod(this);
  }

  private void setupPaymentMethodList() {
    paymentMethodAdapter = new PaymentMethodAdapter();
    recyclerPaymentMethod.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    recyclerPaymentMethod.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
  }

  private void setupPaymentMethods(){
    methodPaymentViewModel.getPaymentMethods().observe(this,this::handlePaymentMethods);
  }

  private void handlePaymentMethods(ApiResource<List<Card>> resource) {
    switch (resource.status) {
      case LOADING:
        showProgressBar();
        break;
      case SUCCESS:
        hideProgressBar();
        recyclerPaymentMethod.setAdapter(paymentMethodAdapter);
        paymentMethodAdapter.addItems(resource.data);
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
