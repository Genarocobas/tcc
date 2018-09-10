package inatel.br.nfccontrol.data.model;

import java.io.Serializable;

public class JourneyConfig implements Serializable {

  private long mId;

  private long mUserId;

  private int mLunchInterval;

  private int mContinuousInterval;

  private int mJourneyInterval;

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
