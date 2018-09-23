package inatel.br.nfccontrol.journey_configuration;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;

import javax.inject.Inject;

import inatel.br.nfccontrol.account.AccountController;
import inatel.br.nfccontrol.data.model.User;

public class JourneyConfigurationViewModel {

  private LifecycleOwner mLifecycleOwner;

  private User mUser;

  @Inject
  AccountController mAccountController;

  @Inject
  Context mContext;

  @Inject
  public JourneyConfigurationViewModel(){
  }
}
