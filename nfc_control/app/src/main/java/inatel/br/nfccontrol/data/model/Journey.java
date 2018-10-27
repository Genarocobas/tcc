package inatel.br.nfccontrol.data.model;

import static android.arch.persistence.room.ForeignKey.CASCADE;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

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
  private Date mEnterTime1;

  @ColumnInfo(name = "enter_exit_1")
  private Date mExitTime1;

  @ColumnInfo(name = "enter_time_2")
  private Date mEnterTime2;

  @ColumnInfo(name = "enter_exit_2")
  private Date mExitTime2;


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

  public Date getEnterTime1() {
    return mEnterTime1;
  }

  public void setEnterTime1(Date enterTime1) {
    mEnterTime1 = enterTime1;
  }

  public Date getExitTime1() {
    return mExitTime1;
  }

  public void setExitTime1(Date exitTime1) {
    mExitTime1 = exitTime1;
  }

  public Date getEnterTime2() {
    return mEnterTime2;
  }

  public void setEnterTime2(Date enterTime2) {
    mEnterTime2 = enterTime2;
  }

  public Date getExitTime2() {
    return mExitTime2;
  }

  public void setExitTime2(Date exitTime2) {
    mExitTime2 = exitTime2;
  }
}
