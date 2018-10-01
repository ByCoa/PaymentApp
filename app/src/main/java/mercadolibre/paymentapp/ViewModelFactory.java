package mercadolibre.paymentapp;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import mercadolibre.paymentapp.feature.paymentbank.PaymentBankRepository;
import mercadolibre.paymentapp.feature.paymentbank.PaymentBankViewModel;
import mercadolibre.paymentapp.feature.paymentinstallments.PaymentInstallmentsRepository;
import mercadolibre.paymentapp.feature.paymentinstallments.PaymentInstallmentsViewModel;
import mercadolibre.paymentapp.feature.paymentmethod.PaymentMethodRepository;
import mercadolibre.paymentapp.feature.paymentmethod.PaymentMethodViewModel;
import mercadolibre.paymentapp.network.api.ServiceFactory;
import mercadolibre.paymentapp.network.service.PaymentMethodService;
import mercadolibre.paymentapp.storage.datasource.paymentmethod.PaymentMethodRemoteDataSource;

public class ViewModelFactory {
  private static final Factory MODEL_FACTORY = new Factory();

  public static PaymentMethodViewModel getPaymentMethod(FragmentActivity owner) {
    return ViewModelProviders.of(owner, MODEL_FACTORY).get(PaymentMethodViewModel.class);
  }

  public static PaymentBankViewModel getPaymentBank(FragmentActivity owner) {
    return ViewModelProviders.of(owner, MODEL_FACTORY).get(PaymentBankViewModel.class);
  }

  public static PaymentInstallmentsViewModel getPaymentInstallments(FragmentActivity owner) {
    return ViewModelProviders.of(owner, MODEL_FACTORY).get(PaymentInstallmentsViewModel.class);
  }

  @SuppressWarnings("unchecked")
  private static class Factory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
      if (modelClass.isAssignableFrom(PaymentMethodViewModel.class)) {
        return (T) new PaymentMethodViewModel(new PaymentMethodRepository(new PaymentMethodRemoteDataSource(ServiceFactory.createService(PaymentMethodService.class))));
      }

      if (modelClass.isAssignableFrom(PaymentBankViewModel.class)) {
        return (T) new PaymentBankViewModel(new PaymentBankRepository(new PaymentMethodRemoteDataSource(ServiceFactory.createService(PaymentMethodService.class))));
      }

      if (modelClass.isAssignableFrom(PaymentInstallmentsViewModel.class)) {
        return (T) new PaymentInstallmentsViewModel(new PaymentInstallmentsRepository(new PaymentMethodRemoteDataSource(ServiceFactory.createService(PaymentMethodService.class))));
      }

      throw new IllegalArgumentException("View Model given doesn't exist.");
    }
  }
}
