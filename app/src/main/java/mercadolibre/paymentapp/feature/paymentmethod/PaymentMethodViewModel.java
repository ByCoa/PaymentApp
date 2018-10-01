package mercadolibre.paymentapp.feature.paymentmethod;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import java.util.List;

import mercadolibre.paymentapp.model.card.Card;
import mercadolibre.paymentapp.network.api.ApiResource;

import static com.uber.autodispose.AutoDispose.autoDisposable;

public class PaymentMethodViewModel extends ViewModel{
  private MutableLiveData<ApiResource<List<Card>>> paymentMethod = new MutableLiveData<>();
  private PaymentMethodRepository paymentMethodRepository;

  public PaymentMethodViewModel(PaymentMethodRepository paymentMethodRepository) {
    this.paymentMethodRepository = paymentMethodRepository;
  }

  public void fetchPaymentMethod (Lifecycle lifecycle) {
    paymentMethod.setValue(ApiResource.loading());
    paymentMethodRepository.getPaymentMethods()
        .as(autoDisposable(AndroidLifecycleScopeProvider.from(lifecycle)))
        .subscribe(cards -> {
          paymentMethod.setValue(ApiResource.success(cards));
        }, error -> {

        });
  }

  public LiveData<ApiResource<List<Card>>> getPaymentMethods() {
    return paymentMethod;
  }
}
