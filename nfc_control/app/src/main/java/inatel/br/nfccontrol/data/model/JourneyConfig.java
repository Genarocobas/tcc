package inatel.br.nfccontrol.data.model;

import static android.arch.persistence.room.ForeignKey.CASCADE;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "journey_config", foreignKeys = @ForeignKey(entity = User.class,
    parentColumns = "id",
    childColumns = "user_id",
    onDelete = CASCADE))
public class JourneyConfig implements Serializable {

  @PrimaryKey(autoGenerate = true)
  @NonNull
  @ColumnInfo(name = "id")
  private long mId;

  @NonNull
  @ColumnInfo(name = "user_id")
  private long mUserId;

  @ColumnInfo(name = "lunch_interval")
  private int mLunchInterval;

  @ColumnInfo(name = "continuous_interval")
  private int mContinuousInterval;

  @ColumnInfo(name = "journey_interval")
  private int mJourneyInterval;

  public JourneyConfig() {
  }

  @Ignore
  public JourneyConfig(long userId, int lunchInterval, int continuousInterval,
      int journeyInterval) {
    this.mUserId = userId;
    this.mLunchInterval = lunchInterval;
    this.mContinuousInterval = continuousInterval;
    this.mJourneyInterval = journeyInterval;
  }

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

  public int getLunchInterval() {
    return mLunchInterval;
  }

  public void setLunchInterval(int lunchInterval) {
    mLunchInterval = lunchInterval;
  }

  public int getContinuousInterval() {
    return mContinuousInterval;
  }

  public void setContinuousInterval(int continuousInterval) {
    mContinuousInterval = continuousInterval;
  }

  public int getJourneyInterval() {
    return mJourneyInterval;
  }

  public void setJourneyInterval(int journeyInterval) {
    mJourneyInterval = journeyInterval;
  }
}
