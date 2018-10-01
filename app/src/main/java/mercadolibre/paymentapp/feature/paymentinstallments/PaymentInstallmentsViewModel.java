package mercadolibre.paymentapp.feature.paymentinstallments;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.List;

import mercadolibre.paymentapp.model.installment.PayerCost;
import mercadolibre.paymentapp.network.api.ApiResource;

import static com.uber.autodispose.AutoDispose.autoDisposable;

public class PaymentInstallmentsViewModel extends ViewModel {

  private MutableLiveData<ApiResource<List<PayerCost>>> paymentInstallment= new MutableLiveData<>();
  private PaymentInstallmentsRepository paymentMethodRepository;

  public PaymentInstallmentsViewModel(PaymentInstallmentsRepository paymentMethodRepository) {
    this.paymentMethodRepository = paymentMethodRepository;
  }

  public void fetchPaymentInstallments (Lifecycle lifecycle, String typeCardId, String bankId) {
    paymentInstallment.setValue(ApiResource.loading());
    paymentMethodRepository.getPaymentInstallment(typeCardId, bankId)
        .as(autoDisposable(AndroidLifecycleScopeProvider.from(lifecycle)))
        .subscribe(installments -> {

          paymentInstallment.setValue(ApiResource.success(installments));
        }, error -> {
          paymentInstallment.setValue(ApiResource.error("",null));
        });
  }

  public LiveData<ApiResource<List<PayerCost>>> getPaymentInstallments() {
    return paymentInstallment;
  }
}
