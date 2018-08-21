package inatel.br.nfccontrol.di.component;

import javax.inject.Singleton;

import dagger.Component;
import inatel.br.nfccontrol.TccApplication;
import inatel.br.nfccontrol.account.AccountActivity;
import inatel.br.nfccontrol.di.module.ApplicationModule;
import inatel.br.nfccontrol.di.module.NetworkModule;
import inatel.br.nfccontrol.di.module.RoomModule;
import inatel.br.nfccontrol.network.HeaderInterceptor;
import inatel.br.nfccontrol.network.ResponseInterceptor;

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
    NetworkModule.class,
})
public interface ApplicationComponent {
  void inject(TccApplication tccApplication);

  void inject(AccountActivity accountActivity);

  void inject(HeaderInterceptor headerInterceptor);

  void inject(ResponseInterceptor responseInterceptor);
}
