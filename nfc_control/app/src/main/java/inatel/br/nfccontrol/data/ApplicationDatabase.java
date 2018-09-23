package inatel.br.nfccontrol.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import inatel.br.nfccontrol.data.dao.UserDao;
import inatel.br.nfccontrol.data.model.User;

/**
 * @author Guilherme Ribeiro de Melo Yabu <guilhermeyabu@inatel.br>
 * @since 15/08/2018.
 */

@Database(entities = {User.class}, version = 4)
public abstract class ApplicationDatabase extends RoomDatabase {

  public abstract UserDao userDao();

}
