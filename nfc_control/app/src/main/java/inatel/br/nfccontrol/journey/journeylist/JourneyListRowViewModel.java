package inatel.br.nfccontrol.journey.journeylist;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import inatel.br.nfccontrol.R;
import inatel.br.nfccontrol.data.model.Journey;
import inatel.br.nfccontrol.utils.TimeUtils;

public class JourneyListRowViewModel extends BaseObservable {

  private Journey mJourney;

  public final ObservableField<Integer> exitTime1Visibility;

  public final ObservableField<Integer> lunchVisibility;

  public final ObservableField<Integer> viewColor;

  public final ObservableField<String> lunchTime;

  public final ObservableField<Integer> enterTime2Visibility;

  public final ObservableField<Integer> exitTime2Visibility;

  @Inject
  Context mContext;

  @Inject
  public JourneyListRowViewModel() {
    exitTime1Visibility = new ObservableField<>(View.GONE);
    lunchVisibility = new ObservableField<>(View.GONE);
    lunchTime = new ObservableField<>();
    enterTime2Visibility = new ObservableField<>(View.GONE);
    exitTime2Visibility = new ObservableField<>(View.GONE);
    viewColor =  new ObservableField<>();
  }

  public void setJourney(Journey journey) {
    mJourney = journey;
  }

  public String getJourneyDay() {
    Date todayEnterTime = mJourney.getEnterTime1();
    String day = "";
    int difference = checkIfActualJourney(todayEnterTime);
    if (difference == 0) {
      day = "Hoje";
      viewColor.set(mContext.getResources().getColor(R.color.view_day_today));
    } else if (difference == 1) {
      day = "Ontem";
      viewColor.set(mContext.getResources().getColor(R.color.gray));
    } else {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeUtils.DATEFORMAT);
      day = simpleDateFormat.format(todayEnterTime);
      viewColor.set(mContext.getResources().getColor(R.color.gray));
    }

    return day;
  }

  public String getEnterTime1() {
    Date enterTime = mJourney.getEnterTime1();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeUtils.HOURFORMAT);
    String hour = simpleDateFormat.format(enterTime);

    return String.format(mContext.getResources().getString(R.string.enter_time_1), hour);
  }

  public String getExitTime1() {
    String timeString = "";
    if (mJourney.getExitTime1() != null) {
      exitTime1Visibility.set(View.VISIBLE);

      Date exitTime = mJourney.getExitTime1();
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeUtils.HOURFORMAT);
      String hour = simpleDateFormat.format(exitTime);

      timeString = String.format(mContext.getResources().getString(R.string.exit_time_1), hour);
    }

    return timeString;
  }

  public String getEnterTime2() {
    String timeString = "";
    if (mJourney.getEnterTime2() != null) {
      enterTime2Visibility.set(View.VISIBLE);
      lunchVisibility.set(View.VISIBLE);

      lunchTime.set(String.format(mContext.getResources().getString(R.string.lunch_time),
          mJourney.getLunchTime()));

      Date exitTime = mJourney.getEnterTime2();
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeUtils.HOURFORMAT);
      String hour = simpleDateFormat.format(exitTime);

      timeString = String.format(mContext.getResources().getString(R.string.enter_time_2), hour);
    }

    return timeString;
  }

  public String getExitTime2() {
    String timeString = "";
    if (mJourney.getExitTime2() != null) {
      exitTime2Visibility.set(View.VISIBLE);

      Date exitTime = mJourney.getExitTime2();
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeUtils.HOURFORMAT);
      String hour = simpleDateFormat.format(exitTime);

      timeString = String.format(mContext.getResources().getString(R.string.exit_time_2), hour);
    }

    return timeString;
  }

  private int checkIfActualJourney(Date today) {

    Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(today.getTime());
    int journeyDay = cal.get(Calendar.DAY_OF_YEAR);

    Date now = Calendar.getInstance().getTime();
    cal.setTime(now);
    int day = cal.get(Calendar.DAY_OF_YEAR);

    return day - journeyDay;
  }
}
