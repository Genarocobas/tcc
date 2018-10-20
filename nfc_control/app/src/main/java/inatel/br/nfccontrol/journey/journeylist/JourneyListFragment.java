package inatel.br.nfccontrol.journey.journeylist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import inatel.br.nfccontrol.account.AccountController;
import inatel.br.nfccontrol.utils.Logger;

public class JourneyListFragment extends Fragment {

  public static final String TAG = Logger.getTag();

  @Inject
  AccountController mController;

  @Inject
  JourneyListViewModel mViewModel;

  @Inject
  public JourneyListFragment(){
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override
  public void onResume() {
    super.onResume();
    mViewModel.onResume();
  }
}
