package inatel.br.nfccontrol.di.component;

import javax.inject.Singleton;

import dagger.Component;
import inatel.br.nfccontrol.TccApplication;
import inatel.br.nfccontrol.account.AccountActivity;
import inatel.br.nfccontrol.di.module.ApplicationModule;
import inatel.br.nfccontrol.di.module.RoomModule;

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
})
public interface ApplicationComponent {
  void inject(TccApplication tccApplication);

  void inject(AccountActivity accountActivity);
}
