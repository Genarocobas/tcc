package inatel.br.nfccontrol.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import inatel.br.nfccontrol.data.model.JourneyConfig;

@Dao
public interface JourneyConfigDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  long insert(JourneyConfig journeyConfig);

  @Update
  void update(JourneyConfig journeyConfig);

  @Query("SELECT * FROM journey_config WHERE user_id = :userId")
  LiveData<JourneyConfig> getJourneyConfiguration(long userId);
}
