package inatel.br.nfccontrol.journey_configuration;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import javax.inject.Inject;

import inatel.br.nfccontrol.R;
import inatel.br.nfccontrol.account.AccountController;
import inatel.br.nfccontrol.data.model.JourneyConfig;
import inatel.br.nfccontrol.data.model.User;
import inatel.br.nfccontrol.databinding.ActivityJourneyConfigurationBinding;
import inatel.br.nfccontrol.journey.JourneyActivity;
import inatel.br.nfccontrol.utils.Logger;

public class JourneyConfigurationViewModel {

  public static final String TAG = Logger.getTag();

  private LifecycleOwner mLifecycleOwner;

  public final ObservableField<String> welcomeUser;

  private User mUser;

  private ActivityJourneyConfigurationBinding mBinding;

  private JourneyConfig mJourneyConfig;

  @Inject
  AccountController mAccountController;

  @Inject
  Context mContext;

  @Inject
  public JourneyConfigurationViewModel() {
    welcomeUser = new ObservableField<>();
  }

  public void setBinding(ActivityJourneyConfigurationBinding binding) {
    mBinding = binding;
  }

  public void onResume() {
    mUser = mAccountController.getConnectedUser();

    welcomeUser.set(String.format(mContext.getResources().getString(R.string.welcome_user_string),
        mUser.getName()));
  }

  public View.OnClickListener getJourneyConfiguration() {
    return v -> {
      createJourneyConfig();
      JourneyActivity.startActivity(mContext);
    };
  }

  private void createJourneyConfig() {
    int lunchInterval = mBinding.dsbLunchInterval.getProgress();
    int continuousTime = mBinding.dsbContinuousInterval.getProgress();
    int journeyInterval = mBinding.dsbJourneyInterval.getProgress();
    mJourneyConfig = new JourneyConfig(mUser.getId(), lunchInterval, continuousTime,
        journeyInterval);
    mAccountController.insertJourneyConfig(mJourneyConfig);
    Log.d(TAG, "createJourneyConfig: " + mAccountController.getConnectedUser().getName() + "/"
        + mAccountController.getConnectedUser().getJourneyConfig());
  }
}
