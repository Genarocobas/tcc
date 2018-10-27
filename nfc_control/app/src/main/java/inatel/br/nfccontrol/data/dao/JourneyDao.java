package inatel.br.nfccontrol.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import inatel.br.nfccontrol.data.model.Journey;

@Dao
public interface JourneyDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  long insert(Journey journey);

  @Update
  void update(Journey journey);

  @Query("SELECT * FROM journey WHERE user_id = :userId")
  LiveData<List<Journey>> getJourneys(long userId);
}
