package inatel.br.nfccontrol.account.login;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

import javax.inject.Inject;

import inatel.br.nfccontrol.R;
import inatel.br.nfccontrol.account.AccountController;
import inatel.br.nfccontrol.network.NetworkViewModel;
import inatel.br.nfccontrol.data.model.User;
import inatel.br.nfccontrol.network.UserContract;
import inatel.br.nfccontrol.utils.Logger;
import inatel.br.nfccontrol.utils.SingleLiveEvent;
import io.reactivex.Observable;

public class LoginViewModel extends NetworkViewModel<User> {

  private static final String TAG = Logger.getTag();

  public final ObservableField<String> userEmail;

  public final ObservableField<String> userPassword;

  public final ObservableField<Boolean> emailError;

  public final ObservableField<Boolean> passwordError;

  private SingleLiveEvent<String> mAccountSubject;

  private LifecycleOwner mLifecycleOwner;

  private User mUser;

  @Inject
  UserContract mContract;

  @Inject
  AccountController mAccountController;

  @Inject
  Context mContext;

  @Inject
  public LoginViewModel() {
    userEmail = new ObservableField<>();
    userPassword = new ObservableField<>();
    emailError = new ObservableField<>(false);
    passwordError = new ObservableField<>(false);
    mAccountSubject = new SingleLiveEvent<>();
  }

  @Override
  public Observable<User> getRequestObservable() {
    return mContract.login(mUser);
  }

  @Override
  public void onResult(User userFromServer) {
    if (Logger.DEBUG) Log.d(TAG, "onResult: " + userFromServer);
    mAccountController.insertAccount(userFromServer);
  }

  @Override
  public void onError(Throwable throwable) {
    if (Logger.DEBUG) Log.d(TAG, "onError: " + throwable.getMessage());
    Toast.makeText(mContext, R.string.server_connection_error, Toast.LENGTH_SHORT).show();
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

  public View.OnClickListener onClickLogin() {
    return v -> {
      if (Logger.DEBUG) Log.d(TAG, "onClickLogin");

      if (emailError.get() || passwordError.get()){
        Toast.makeText(mContext, R.string.invalid_fields_text, Toast.LENGTH_SHORT).show();
      } else {
        buildRequestUser();
      }
      mAccountSubject.setValue("on Next");
    };
  }

  private void buildRequestUser() {
    mUser =  new User();
    mUser.setEmail(Objects.requireNonNull(userEmail.get()));
    mUser.setPassword(userPassword.get());
  }

  public SingleLiveEvent<String> getAccountSubject() {
    return mAccountSubject;
  }
}
