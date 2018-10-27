package inatel.br.nfccontrol.journey.journeylist;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import inatel.br.nfccontrol.R;
import inatel.br.nfccontrol.databinding.FragmentJourneyListBinding;
import inatel.br.nfccontrol.utils.Logger;

public class JourneyListFragment extends Fragment {

  public static final String TAG = Logger.getTag();

  private FragmentJourneyListBinding mBinding;

  @Inject
  JourneyListViewModel mViewModel;

  @Inject
  public JourneyListFragment() {
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_journey_list, container, false);

    mBinding.setViewModel(mViewModel);
    mBinding.rvJourneyList.setEmptyView(mBinding.emptyView);
    mBinding.rvJourneyList.setAdapter(mViewModel.mJourneyListAdapter);
    mViewModel.setLifecycleOwner(this);

    return mBinding.getRoot();
  }

  @Override
  public void onResume() {
    super.onResume();
    mViewModel.onResume();
  }
}
