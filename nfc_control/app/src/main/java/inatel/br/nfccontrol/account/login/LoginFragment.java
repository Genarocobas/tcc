package inatel.br.nfccontrol.account.login;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import inatel.br.nfccontrol.R;
import inatel.br.nfccontrol.data.model.User;
import inatel.br.nfccontrol.databinding.FragmentLayout2Binding;
import inatel.br.nfccontrol.databinding.FragmentLoginBinding;
import inatel.br.nfccontrol.utils.LoadingDialogFragment;
import inatel.br.nfccontrol.utils.Logger;
import inatel.br.nfccontrol.utils.SingleLiveEvent;

public class LoginFragment extends Fragment {

  public static final String TAG = Logger.getTag();

  private FragmentLayout2Binding mBinding;

  @Inject
  LoginViewModel mViewModel;

  @Inject
  public LoginFragment(){}

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
      Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    if (Logger.DEBUG) Log.d(TAG, "onCreateView");

    mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_layout_2, container, false);
    mBinding.setViewModel(mViewModel);

    mViewModel.setLifecycleOwner(this);

    return mBinding.getRoot();
  }

  @Override
  public void onResume() {
    super.onResume();
    mViewModel.onResume();
  }

  public SingleLiveEvent<String> getAccountSubject(){
    return mViewModel.getAccountSubject();
  }
}
