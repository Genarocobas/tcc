package inatel.br.nfccontrol.data.model;

import static android.arch.persistence.room.ForeignKey.CASCADE;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.sql.Timestamp;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "journey", foreignKeys = @ForeignKey(entity = User.class,
    parentColumns = "id",
    childColumns = "user_id",
    onDelete = CASCADE))

public class Journey implements Serializable {

  @PrimaryKey(autoGenerate = true)
  @NonNull
  @ColumnInfo(name = "id")
  private long mId;

  @NonNull
  @ColumnInfo(name = "user_id")
  private long mUserId;

  @ColumnInfo(name = "enter_time_1")
  private Timestamp mEnterTime1;

  @ColumnInfo(name = "enter_exit_1")
  private Timestamp mExitTime1;

  @ColumnInfo(name = "enter_time_2")
  private Timestamp mEnterTime2;

  @ColumnInfo(name = "enter_exit_2")
  private Timestamp mExitTime2;


  public long getId() {
    return mId;
  }

  public void setId(long id) {
    mId = id;
  }

  public long getUserId() {
    return mUserId;
  }

  public void setUserId(long userId) {
    mUserId = userId;
  }

  public Timestamp getEnterTime1() {
    return mEnterTime1;
  }

  public void setEnterTime1(Timestamp enterTime1) {
    mEnterTime1 = enterTime1;
  }

  public Timestamp getExitTime1() {
    return mExitTime1;
  }

  public void setExitTime1(Timestamp exitTime1) {
    mExitTime1 = exitTime1;
  }

  public Timestamp getEnterTime2() {
    return mEnterTime2;
  }

  public void setEnterTime2(Timestamp enterTime2) {
    mEnterTime2 = enterTime2;
  }

  public Timestamp getExitTime2() {
    return mExitTime2;
  }

  public void setExitTime2(Timestamp exitTime2) {
    mExitTime2 = exitTime2;
  }
}
