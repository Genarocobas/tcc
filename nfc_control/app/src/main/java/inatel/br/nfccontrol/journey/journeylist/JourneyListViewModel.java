package inatel.br.nfccontrol.journey.journeylist;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import inatel.br.nfccontrol.R;
import inatel.br.nfccontrol.TccApplication;
import inatel.br.nfccontrol.account.AccountController;
import inatel.br.nfccontrol.data.model.Journey;
import inatel.br.nfccontrol.data.model.User;
import inatel.br.nfccontrol.utils.Logger;

public class JourneyListViewModel {

  private static final String TAG = Logger.getTag();

  public final ObservableField<String> welcomeText;

  public final ObservableField<String> dayTotal;

  private User mUser;

  private List<Journey> mJourneyList;

  private LifecycleOwner mLifecycleOwner;

  @Inject
  AccountController mController;

  @Inject
  Context mContext;

  @Inject
  JourneyListAdapter mJourneyListAdapter;

  @Inject
  public JourneyListViewModel() {
    mJourneyList = new ArrayList<>();
    welcomeText = new ObservableField<>();
    dayTotal = new ObservableField<>();
  }

  public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
    mLifecycleOwner = lifecycleOwner;
  }

  public void onResume() {
    if (Logger.DEBUG) Log.d(TAG, "onResume: ");

    mUser = mController.getConnectedUser();

    TccApplication.prefs.setUserId((int) mUser.getId());

    welcomeText.set(
        String.format(mContext.getResources().getString(R.string.welcome_user), mUser.getName()));

    mController.getUserJourneys().observe(mLifecycleOwner, journeys -> {
      if (journeys != null) {
        mJourneyList = journeys;
        setData(mJourneyList);
        getDayTotal();
      }
    });
  }

  public View.OnClickListener onClickAdd() {
    return v -> registerNewHourInJourney();
  }

  public void registerNewHourInJourney() {
    Log.d(TAG, "User id: " + TccApplication.prefs.getUserId());

    Journey lastJourney = null;

    if (mJourneyList.size() != 0) {
      lastJourney = mJourneyList.get(mJourneyList.size() - 1);
    }

    if (lastJourney == null || lastJourney.getExitTime2() != null) {
      defineNewJourney();
    } else if (lastJourney.getExitTime1() == null) {
      setFirstExitTime(lastJourney);
    } else if (lastJourney.getEnterTime2() == null) {
      setSecondEnterTime(lastJourney);
    } else if (lastJourney.getExitTime2() == null) {
      endJourney(lastJourney);
    }

    setData(mJourneyList);
    TccApplication.prefs.setCanRegister(false);
  }


  private void defineNewJourney() {
    Journey newJourney = new Journey();
    newJourney.setEnterTime1(Calendar.getInstance().getTime());
    mController.insertJourney(newJourney);
    mJourneyList.add(newJourney);
  }

  private void setFirstExitTime(Journey lastJourney) {
    lastJourney.setExitTime1(Calendar.getInstance().getTime());
    mController.updateJourney(lastJourney);
    updateJourney(lastJourney);
  }

  private void setSecondEnterTime(Journey lastJourney) {
    lastJourney.setEnterTime2(Calendar.getInstance().getTime());
    mController.updateJourney(lastJourney);
    updateJourney(lastJourney);
  }

  private void endJourney(Journey lastJourney) {
    lastJourney.setExitTime2(Calendar.getInstance().getTime());
    mController.updateJourney(lastJourney);
    updateJourney(lastJourney);
  }

  private void updateJourney(Journey lastJourney) {
    mJourneyList.set(mJourneyList.size() - 1, lastJourney);
  }


  public void getDayTotal() {
    dayTotal.set(String.format(mContext.getResources().getString(R.string.day_total), "00:00"));

    if (mJourneyList.size() != 0) {
      Journey lastJourney = mJourneyList.get(mJourneyList.size() - 1);
      dayTotal.set(String.format(mContext.getResources().getString(R.string.day_total),
          lastJourney.getJourneyTime()));
    }
  }

  private void setData(List<Journey> journeyList) {
    if (Logger.DEBUG) Log.d(TAG, "setData");
    mJourneyListAdapter.repopulate(journeyList);
  }
}
