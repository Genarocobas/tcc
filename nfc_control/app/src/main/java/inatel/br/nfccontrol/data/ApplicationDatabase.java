package inatel.br.nfccontrol.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.databinding.adapters.Converters;

import inatel.br.nfccontrol.data.dao.JourneyConfigDao;
import inatel.br.nfccontrol.data.dao.JourneyDao;
import inatel.br.nfccontrol.data.dao.UserDao;
import inatel.br.nfccontrol.data.model.Journey;
import inatel.br.nfccontrol.data.model.JourneyConfig;
import inatel.br.nfccontrol.data.model.User;
import inatel.br.nfccontrol.utils.Converter;

/**
 * @author Guilherme Ribeiro de Melo Yabu <guilhermeyabu@inatel.br>
 * @since 15/08/2018.
 */

@Database(entities = {User.class, JourneyConfig.class, Journey.class}, version = 8)
@TypeConverters({Converter.class})
public abstract class ApplicationDatabase extends RoomDatabase {

  public abstract UserDao userDao();

  public abstract JourneyConfigDao journeyConfigDao();

  public abstract JourneyDao journeyDao();

}
