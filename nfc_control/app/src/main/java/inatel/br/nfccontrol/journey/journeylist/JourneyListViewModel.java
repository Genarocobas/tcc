package inatel.br.nfccontrol.journey.journeylist;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import inatel.br.nfccontrol.data.model.Journey;
import inatel.br.nfccontrol.utils.Logger;

public class JourneyListViewModel {

  private static final String TAG = Logger.getTag();

  private List<Journey> mJourneyList;

  @Inject
  Context mContext;

  @Inject
  JourneyListAdapter mJourneyListAdapter;

  @Inject
  public JourneyListViewModel(){
    mJourneyList = new ArrayList<>();
  }

  public void onResume(){

  }

  private void setData(List<Journey> journeyList){
    if (Logger.DEBUG) Log.d(TAG, "setData");

    mJourneyListAdapter.repopulate(journeyList);
  }

}
