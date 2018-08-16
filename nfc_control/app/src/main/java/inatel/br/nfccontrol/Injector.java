package inatel.br.nfccontrol;

import java.util.Objects;

import inatel.br.nfccontrol.di.component.ApplicationComponent;
import inatel.br.nfccontrol.di.component.DaggerApplicationComponent;
import inatel.br.nfccontrol.di.module.ApplicationModule;
import inatel.br.nfccontrol.di.module.RoomModule;

/**
 * Initialized the {@link ApplicationComponent}, setting all application {@link dagger.Module}.
 *
 * @author Guilherme Ribeiro de Melo Yabu <guilhermeyabu@inatel.br>
 * @since 15/08/2018.
 */
public final class Injector {

  private static ApplicationComponent sApplicationComponent;

  private Injector() {
  }

  public static void initializeApplicationComponent(TccApplication app) {
    sApplicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(app))
        .build();
  }

  public static ApplicationComponent getApplicationComponent() {
    Objects.requireNonNull(sApplicationComponent, "Null ApplicationComponent");
    return sApplicationComponent;
  }
}
