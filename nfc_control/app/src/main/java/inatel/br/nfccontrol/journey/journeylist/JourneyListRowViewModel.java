package inatel.br.nfccontrol.journey.journeylist;

import android.arch.persistence.room.Insert;
import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.view.View;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import inatel.br.nfccontrol.R;
import inatel.br.nfccontrol.data.model.Journey;
import inatel.br.nfccontrol.utils.TimeUtils;

public class JourneyListRowViewModel extends BaseObservable {

  private Journey mJourney;

  public final ObservableField<String> day;

  public final ObservableField<String> enterTimeOne;

  public final ObservableField<String> exitTimeOne;

  public final ObservableField<String> enterTimeTwo;

  public final ObservableField<String> exitTimeTwo;

  public final ObservableField<Integer> exitTime1Visiblity;

  public final ObservableField<Integer> enterTime2Visiblity;

  public final ObservableField<Integer> exitTime2Visiblity;

  @Inject
  Context mContext;

  @Inject
  public JourneyListRowViewModel() {
    day = new ObservableField<>();
    enterTimeOne = new ObservableField<>();
    exitTimeOne = new ObservableField<>();
    enterTimeTwo = new ObservableField<>();
    exitTimeTwo = new ObservableField<>();
    exitTime1Visiblity = new ObservableField<>(View.GONE);
    enterTime2Visiblity = new ObservableField<>(View.GONE);
    exitTime2Visiblity = new ObservableField<>(View.GONE);
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
    } else if (difference == 1) {
      day = "Ontem";
    } else {
      SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeUtils.DATEFORMAT);
      day = simpleDateFormat.format(todayEnterTime);
    }

    return day;
  }

  public String getEnterTime1() {
    Date enterTime = mJourney.getEnterTime1();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TimeUtils.HOURFORMAT);
    String hour = simpleDateFormat.format(enterTime);

    return String.format(mContext.getResources().getString(R.string.enter_time_1), hour);
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
