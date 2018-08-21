package inatel.br.nfccontrol.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import inatel.br.nfccontrol.data.ApplicationDatabase;
import inatel.br.nfccontrol.data.dao.UserDao;

/**
 * @author Guilherme Ribeiro de Melo Yabu <guilhermeyabu@inatel.br>
 * @since 15/08/2018.
 */
@Module
public class RoomModule {

  private ApplicationDatabase mApplicationDatabase;

  public RoomModule(Application mApplication) {
    this.mApplicationDatabase = Room.databaseBuilder(mApplication, ApplicationDatabase.class,
        "tcc_database").fallbackToDestructiveMigration().build();
  }

  @Singleton
  @Provides
  ApplicationDatabase providesDb() {
    return mApplicationDatabase;
  }

  @Singleton
  @Provides
  UserDao providesUserDao(){
    return mApplicationDatabase.userDao();
  }

}
