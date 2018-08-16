package inatel.br.nfccontrol.account.login;

import android.arch.lifecycle.LifecycleOwner;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import javax.inject.Inject;

import inatel.br.nfccontrol.utils.Logger;
import inatel.br.nfccontrol.utils.SingleLiveEvent;

public class LoginViewModel {

  private static final String TAG = Logger.getTag();

  public final ObservableField<String> userEmail;

  public final ObservableField<String> userPassword;

  public final ObservableField<Boolean> emailError;

  public final ObservableField<Boolean> passwordError;

  private SingleLiveEvent<String> mAccountSubject;

  private LifecycleOwner mLifecycleOwner;

  @Inject
  public LoginViewModel(){
    userEmail =  new ObservableField<>();
    userPassword = new ObservableField<>();
    emailError =  new ObservableField<>(false);
    passwordError = new ObservableField<>(false);
    mAccountSubject = new SingleLiveEvent<>();
  }

  public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
    mLifecycleOwner = lifecycleOwner;
  }


  public void onResume() {
    if (Logger.DEBUG) Log.d(TAG, "onResume");
    userEmail.set("");
    userPassword.set("");
    emailError.set(false);
    passwordError.set(false);
  }

  public View.OnClickListener onClickLogin(){
    return v -> {
      if (Logger.DEBUG) Log.d(TAG, "onClickLogin");
      mAccountSubject.setValue("on Next");
    };
  }

  public SingleLiveEvent<String> getAccountSubject() {
    return mAccountSubject;
  }
}
