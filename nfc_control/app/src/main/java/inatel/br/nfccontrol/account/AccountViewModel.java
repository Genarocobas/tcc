package inatel.br.nfccontrol.account;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import inatel.br.nfccontrol.data.model.User;
import inatel.br.nfccontrol.network.NetworkViewModel;
import inatel.br.nfccontrol.network.UserContract;
import inatel.br.nfccontrol.utils.LoadingConstants;
import inatel.br.nfccontrol.utils.Logger;
import inatel.br.nfccontrol.utils.SingleLiveEvent;
import io.reactivex.Observable;

public class AccountViewModel extends NetworkViewModel<User> {

  private static final String TAG = Logger.getTag();

  private User mConnectedUser;

  private SingleLiveEvent<String> mAccountSubject;

  @Inject
  UserContract mContract;

  @Inject
  AccountController mAccountController;

  @Inject
  Context mContext;

  @Inject
  public AccountViewModel(){
    mAccountSubject =  new SingleLiveEvent<>();
  }

  @Override
  public Observable<User> getRequestObservable() {
    return mContract.getAuthenticatedUser(mConnectedUser);
  }

  @Override
  public void onResult(User result) {
    if (Logger.DEBUG) Log.d(TAG, "onResult: logged as: " + result.getName());
    mAccountSubject.setValue(LoadingConstants.DISMISS_LOADING);
    Toast.makeText(mContext, "Usuário pego com sucesso - LOGADO como: " + result.getName() , Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onError(Throwable throwable) {
    if (Logger.DEBUG) Log.e(TAG, "onError: ", throwable.getCause());
    mAccountSubject.setValue(LoadingConstants.DISMISS_LOADING);
    Toast.makeText(mContext, "Falha ao acessar servidor", Toast.LENGTH_SHORT).show();
  }

  public void getAuthenticatedUser() {
    mConnectedUser = mAccountController.getConnectedUser();
    makeRequest();
  }

  public SingleLiveEvent<String> getAccountSubject() {
    return mAccountSubject;
  }
}
