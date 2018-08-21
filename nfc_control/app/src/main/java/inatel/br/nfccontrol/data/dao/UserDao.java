package inatel.br.nfccontrol.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import inatel.br.nfccontrol.data.model.User;

@Dao
public interface UserDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  long insert(User user);

  @Update
  void update(User user);

  @Query("SELECT * FROM users WHERE is_authenticated = :status")
  LiveData<User> getAuthenticatedUser(boolean status);
}
