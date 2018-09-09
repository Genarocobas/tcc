/*
 * Copyright (C) 2018 Instituto Nacional de Telecomunicações
 *
 * All rights are reserved. Reproduction in whole or part is
 * prohibited without the written consent of the copyright owner.
 *
 */

package inatel.br.nfccontrol.utils;

import android.app.Dialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.inject.Inject;
import javax.inject.Singleton;

import inatel.br.nfccontrol.R;
import inatel.br.nfccontrol.databinding.LoadingDialogFragmentBinding;

/**
 * Generic {@link DialogFragment} for presents loading status in app.
 *
 * @author Diógenes Aparecido Rezende <diogenes@inatel.br>
 * @since : 14/06/2018.
 */
@Singleton
public class LoadingDialogFragment extends DialogFragment {

  public static final String TAG = Logger.getTag();

  private final AtomicBoolean mIsShowing = new AtomicBoolean(false);

  /**
   * {@link ObservableField} for set status message in show this fragment.
   */
  public ObservableField<String> mMessage;

  @Inject
  public LoadingDialogFragment() {
    if (Logger.DEBUG) Log.d(TAG, "constructor");
    mMessage = new ObservableField<>();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    if (Logger.DEBUG) Log.d(TAG, "onCreateDialog");
    //Create and inflate a custom view of dialog fragment
    LayoutInflater inflater = LayoutInflater.from(getContext());
    final LoadingDialogFragmentBinding binding = DataBindingUtil.inflate(inflater,
        R.layout.loading_dialog_fragment, null, false);

    binding.setLoadingDialogFragment(this);

    //Initial Message
    mMessage.set(getContext().getResources().getString(
        R.string.connection_message_connecting));

    AlertDialog dialog = new AlertDialog.Builder(getActivity())
        .setTitle(null)
        .setCancelable(false)
        .setView(binding.getRoot())
        .create();
    setCancelable(false);
    return dialog;
  }

  public void setMessage(String message) {
    mMessage.set(message);
  }

  @Override
  public void show(FragmentManager manager, String tag) {
    mIsShowing.set(true);
    super.show(manager, tag);
  }

  @Override
  public void onResume() {
    super.onResume();
    mIsShowing.set(true);
  }

  @Override
  public void onDismiss(DialogInterface dialog) {
    super.onDismiss(dialog);
    mIsShowing.set(false);
  }

  public boolean isShowing() {
    return mIsShowing.get();
  }
}
