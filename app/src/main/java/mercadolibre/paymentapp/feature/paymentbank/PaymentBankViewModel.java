package mercadolibre.paymentapp.feature.paymentbank;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.List;

import mercadolibre.paymentapp.model.bank.Bank;
import mercadolibre.paymentapp.network.api.ApiResource;

import static com.uber.autodispose.AutoDispose.autoDisposable;

public class PaymentBankViewModel extends ViewModel{
  private MutableLiveData<ApiResource<List<Bank>>> paymentBank = new MutableLiveData<>();
  private PaymentBankRepository paymentMethodRepository;

  public PaymentBankViewModel(PaymentBankRepository paymentMethodRepository) {
    this.paymentMethodRepository = paymentMethodRepository;
  }

  public void fetchPaymentBank (Lifecycle lifecycle,String typeCardId) {
    paymentBank.setValue(ApiResource.loading());
    paymentMethodRepository.getPaymentBank(typeCardId)
        .as(autoDisposable(AndroidLifecycleScopeProvider.from(lifecycle)))
        .subscribe(banks -> {
          paymentBank.setValue(ApiResource.success(banks));
        }, error -> {

        });
  }

  public LiveData<ApiResource<List<Bank>>> getPaymentBanks() {
    return paymentBank;
  }
}
