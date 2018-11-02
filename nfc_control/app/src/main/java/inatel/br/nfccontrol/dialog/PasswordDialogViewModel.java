package inatel.br.nfccontrol.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.util.Log;

import javax.inject.Inject;

import inatel.br.nfccontrol.TccApplication;
import inatel.br.nfccontrol.account.AccountController;
import inatel.br.nfccontrol.data.model.User;
import inatel.br.nfccontrol.utils.Logger;
import io.reactivex.subjects.PublishSubject;

public class PasswordDialogViewModel extends BaseObservable {

  public static final String TAG = Logger.getTag();

  /**
   * {@link String} Object created to validated password databinding.
   */
  public final ObservableField<String> password;

  /**
   * Object for observe a event click of positive button.
   */
  PublishSubject<String> mPositiveButtonClickedSubject;

  @Inject
  AccountController mController;

  @Inject
  Context mContext;

  @Inject
  public PasswordDialogViewModel() {
    if (Logger.DEBUG) Log.d(TAG, "EndTripCashValueViewModel: ");

    password = new ObservableField<>();

    mPositiveButtonClickedSubject = PublishSubject.create();
  }

  public void onResume() {
    password.set("");
  }

  /**
   * Method to listen user click on positive button in dialog fragment.
   *
   * @return object DialogInterface.
   */
  public DialogInterface.OnClickListener onClickPositive() {
    return (dialogInterface, i) -> {
      if (Logger.DEBUG) Log.d(TAG, "onClickPositive: ");
      String passwordValue = password.get() != null ? password.get() : "0";
      mPositiveButtonClickedSubject.onNext(passwordValue);
    };
  }

  /**
   * Method to listen user click on negative button in dialog fragment.
   *
   * @return object DialogInterface.
   */
  public DialogInterface.OnClickListener onClickNegative() {
    return (dialogInterface, i) -> {
      if (Logger.DEBUG) Log.d(TAG, "onClickNegative");
    };
  }

  public PublishSubject<String> getPositiveButtonClickedSubject() {
    if (Logger.DEBUG) Log.d(TAG, "getPositiveButtonClickedSubject");
    return mPositiveButtonClickedSubject;
  }
}
