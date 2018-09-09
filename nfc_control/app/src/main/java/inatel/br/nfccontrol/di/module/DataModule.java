package inatel.br.nfccontrol.di.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class DataModule {

  @Provides
  @Singleton
  SharedPreferences provideSharedPreferences(Application app) {
    return PreferenceManager.getDefaultSharedPreferences(app);
  }
}
