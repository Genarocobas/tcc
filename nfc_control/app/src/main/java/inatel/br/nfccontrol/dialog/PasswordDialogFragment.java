package inatel.br.nfccontrol.dialog;

import android.app.Dialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;

import java.util.Objects;

import javax.inject.Inject;

import inatel.br.nfccontrol.R;
import inatel.br.nfccontrol.databinding.DialogFragmentPasswordBinding;
import inatel.br.nfccontrol.utils.Logger;
import io.reactivex.subjects.PublishSubject;

public class PasswordDialogFragment extends DialogFragment {
  public static final String TAG = Logger.getTag();

  /**
   * Instance of EndTripCashValueViewModel.
   */
  @Inject
  PasswordDialogViewModel mViewModel;

  /**
   * Object for observe a event click of positive button.
   */
  PublishSubject<String> mPositiveButtonClickedSubject;

  private DialogFragmentPasswordBinding mBinding;

  @Inject
  public PasswordDialogFragment() {
    if (Logger.DEBUG) Log.d(TAG, "EndTripCashValueDialogFragment: ");
    mPositiveButtonClickedSubject = PublishSubject.create();
  }

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    if (Logger.DEBUG) Log.d(TAG, "onCreateDialog: ");

    LayoutInflater inflater = LayoutInflater.from(getContext());

    mBinding = DataBindingUtil.inflate(inflater,
        R.layout.dialog_fragment_password, null, false);

    mBinding.setViewModel(mViewModel);

    mViewModel.onResume();

    mViewModel.getPositiveButtonClickedSubject().subscribe(
        password -> subscribeOnPositiveButtonClick(password));

    AlertDialog dialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
        .setTitle("Autenticação")
        .setCancelable(false)
        .setView(mBinding.getRoot())
        .setPositiveButton("OK", mViewModel.onClickPositive())
        .setNegativeButton("CANCELAR", mViewModel.onClickNegative())
        .create();

    setCancelable(false);

    /*dialog.setOnShowListener(dialogInterface -> dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
        .setTextColor(getResources().getColor(R.color
            .negative_button_dialog))
    );*/

    return dialog;
  }

  /**
   * Subscribe on observer the event click of the positive button in view.
   */
  public void subscribeOnPositiveButtonClick(String password) {
    if (Logger.DEBUG) Log.d(TAG, "subscribeOnPositiveButtonClick");
    mPositiveButtonClickedSubject.onNext(password);
  }

  public PublishSubject<String> getPositiveButtonClickedSubject() {
    if (Logger.DEBUG) Log.d(TAG, "getClickedPositiveButtonSubject");
    return mPositiveButtonClickedSubject;
  }
}
