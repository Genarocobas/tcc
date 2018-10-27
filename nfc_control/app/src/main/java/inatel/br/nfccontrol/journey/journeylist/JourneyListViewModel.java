package inatel.br.nfccontrol.journey.journeylist;

import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import inatel.br.nfccontrol.R;
import inatel.br.nfccontrol.account.AccountController;
import inatel.br.nfccontrol.data.model.Journey;
import inatel.br.nfccontrol.data.model.User;
import inatel.br.nfccontrol.utils.Logger;

public class JourneyListViewModel {

  private static final String TAG = Logger.getTag();

  public final ObservableField<String> welcomeText;

  private User mUser;

  private List<Journey> mJourneyList;

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
  }

  public void onResume() {
    if (Logger.DEBUG) Log.d(TAG, "onResume: ");

    mUser = mController.getConnectedUser();

    Journey journey = new Journey();
    mJourneyList.add(journey);
    setData(mJourneyList);
    welcomeText.set(
        String.format(mContext.getResources().getString(R.string.welcome_user), mUser.getName()));
  }

  public View.OnClickListener onClickAdd() {
    return v -> {
      mJourneyList.add(new Journey());
      setData(mJourneyList);
    };
  }


  private void setData(List<Journey> journeyList) {
    if (Logger.DEBUG) Log.d(TAG, "setData");
    mJourneyListAdapter.repopulate(journeyList);
  }

}
