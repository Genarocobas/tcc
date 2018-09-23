package inatel.br.nfccontrol.journey_configuration;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.databinding.ObservableField;

import javax.inject.Inject;

import inatel.br.nfccontrol.R;
import inatel.br.nfccontrol.account.AccountController;
import inatel.br.nfccontrol.data.model.User;
import inatel.br.nfccontrol.databinding.ActivityJourneyConfigurationBinding;

public class JourneyConfigurationViewModel {

  private LifecycleOwner mLifecycleOwner;

  public final ObservableField<String> welcomeUser;

  public final ObservableField<Integer> lunchIntervalValue;

  public final ObservableField<Integer> continuousIntervalValue;

  public final ObservableField<Integer> journeyIntervalValue;

  private User mUser;

  private ActivityJourneyConfigurationBinding mBinding;

  @Inject
  AccountController mAccountController;

  @Inject
  Context mContext;

  @Inject
  public JourneyConfigurationViewModel() {
    welcomeUser = new ObservableField<>();
    lunchIntervalValue = new ObservableField<>();
    continuousIntervalValue = new ObservableField<>();
    journeyIntervalValue = new ObservableField<>();
  }

  public void setBinding(ActivityJourneyConfigurationBinding binding){
    mBinding = binding;
  }

  public void onResume() {
    mUser = mAccountController.getConnectedUser();

    welcomeUser.set(String.format(mContext.getResources().getString(R.string.welcome_user_string),
        mUser.getName()));
  }
}
