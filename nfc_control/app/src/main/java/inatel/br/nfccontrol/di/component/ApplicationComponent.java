package inatel.br.nfccontrol.di.component;

import inatel.br.nfccontrol.di.module.DataModule;
import inatel.br.nfccontrol.di.module.SharedPreferenceModule;
import javax.inject.Singleton;

import dagger.Component;
import inatel.br.nfccontrol.TccApplication;
import inatel.br.nfccontrol.account.AccountActivity;
import inatel.br.nfccontrol.di.module.ApplicationModule;
import inatel.br.nfccontrol.di.module.NetworkModule;
import inatel.br.nfccontrol.di.module.RoomModule;
import inatel.br.nfccontrol.journey_configuration.JourneyConfigurationActivity;
import inatel.br.nfccontrol.journey_list.JourneyActivity;
import inatel.br.nfccontrol.network.HeaderInterceptor;
import inatel.br.nfccontrol.network.ResponseInterceptor;
import io.reactivex.annotations.NonNull;

/**
 * Main application {@link Component}.
 *
 * @author Guilherme Ribeiro de Melo Yabu <guilhermeyabu@inatel.br>
 * @since 15/08/2018.
 */
@Singleton
@Component(modules = {
    ApplicationModule.class,
    RoomModule.class,
    DataModule.class,
    SharedPreferenceModule.class,
    NetworkModule.class,
})
public interface ApplicationComponent {
  void inject(@NonNull TccApplication tccApplication);

  void inject(@NonNull AccountActivity accountActivity);

  void inject(@NonNull HeaderInterceptor headerInterceptor);

  void inject(@NonNull ResponseInterceptor responseInterceptor);

  void inject(@NonNull JourneyConfigurationActivity journeyConfigurationActivity);

  void inject(@NonNull JourneyActivity journeyActivity);
}
