package inatel.br.nfccontrol.journey;

import android.databinding.ObservableField;
import android.view.View;

import javax.inject.Inject;

import inatel.br.nfccontrol.TccApplication;

public class JourneyViewModel {

  public final ObservableField<Integer> enableButton;

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
}
