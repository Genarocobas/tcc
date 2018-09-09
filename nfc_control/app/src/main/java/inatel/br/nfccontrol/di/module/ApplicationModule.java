package inatel.br.nfccontrol.di.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import inatel.br.nfccontrol.TccApplication;

/**
 * {@link Module} that exposes the {@link Application}.
 *
 * @author Guilherme Ribeiro de Melo Yabu <guilhermeyabu@inatel.br>
 * @since 15/08/2018.
 */
@Module
public class ApplicationModule {

  private final TccApplication mApplication;

  public ApplicationModule(TccApplication application) {
    mApplication = application;
  }

  @Provides
  @Singleton
  public Application provideApplication() {
    return mApplication;
  }

  @Provides
  @Singleton
  public Context provideContext() {
    return mApplication.getApplicationContext();
  }
}
