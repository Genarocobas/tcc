package inatel.br.nfccontrol.journey;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import javax.inject.Inject;

import inatel.br.nfccontrol.TccApplication;
import inatel.br.nfccontrol.account.AccountController;
import inatel.br.nfccontrol.data.model.User;

public class JourneyViewModel {

  public final ObservableField<Integer> enableButton;

  @Inject
  AccountController mController;

  @Inject
  Context mContext;

  @Inject
  public JourneyViewModel(){
    enableButton = new ObservableField<>();
  }

  public void onResume() {
    configureButton();
  }

  public void configureButton() {
    boolean authIsOk = TccApplication.prefs.getCanRegister();
    enableButton.set(authIsOk ? View.GONE : View.VISIBLE);
  }

  public void checkPassword(String password) {
    User user = mController.getConnectedUser();

    if (password.equals(user.getPassword())){
      Toast.makeText(mContext , "Autenticação feita com sucesso", Toast.LENGTH_SHORT).show();
      TccApplication.prefs.setCanRegister(true);
      configureButton();
    } else {
      Toast.makeText(mContext , "Senha incorreta", Toast.LENGTH_SHORT).show();
    }
  }
}
