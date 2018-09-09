package inatel.br.nfccontrol.custom_view;

/*
 * Copyright (C) 2018 Instituto Nacional de Telecomunicações
 *
 * All rights are reserved. Reproduction in whole or part is
 * prohibited without the written consent of the copyright owner.
 *
 */

import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.ObservableField;
import android.util.Log;

import inatel.br.nfccontrol.utils.Logger;

/**
 * Binding Adapters for the {@Link CustomInputViewGroup}
 * Deals with the one-way and two-way data binding, handling the information setting on the xml file
 * of the respective view to the class
 *
 * @author Guilherme Yabu <guilhermeyabu@inatel.br>
 * @since 29/05/2018.
 */
public class CustomInputDefaultBindingAdapters {

  private static final String TAG = Logger.getTag();

  @BindingAdapter("data")
  public static void setData(CustomInputDefault customInput, ObservableField<String> data) {
    if (Logger.DEBUG) Log.d(TAG, "setData: " + data.get());
    if (!customInput.getInputData().equals(data.get())) {
      customInput.getBinding().setData(data.get());
    }
  }

  @BindingAdapter("data")
  public static void setData(CustomInputDefault customInput, String data) {
    if (Logger.DEBUG) Log.d(TAG, "setData: " + data);
    if (!customInput.getInputData().equals(data)) {
      customInput.getBinding().setData(data);
    }
  }

  @InverseBindingAdapter(attribute = "data", event = "dataAttrChanged")
  public static String getData(CustomInputDefault inCustomView) {
    if (Logger.DEBUG) Log.d(TAG, "getData");
    return inCustomView.getInputData();
  }

  @InverseBindingAdapter(attribute = "isErrorEnabled", event = "isErrorEnabledAttrChanged")
  public static Boolean getError(CustomInputDefault inCustomView) {
    if (Logger.DEBUG) Log.d(TAG, "getData");
    return inCustomView.isErrorEnabled();
  }

  @BindingAdapter( {"dataAttrChanged"})
  public static void setDataChanged(CustomInputDefault customInput,
      final InverseBindingListener listener) {
    if (Logger.DEBUG) Log.d(TAG, "setDataChanged");
    customInput.setDataInverseBindingListener(listener);
  }

  @BindingAdapter("isErrorEnabled")
  public static void setError(CustomInputDefault customInput, ObservableField<Boolean> error) {
    customInput.setErrorEnabled(error.get());
  }

  @BindingAdapter( {"isErrorEnabledAttrChanged"})
  public static void setErrorChanged(CustomInputDefault customInput,
      final InverseBindingListener listener) {
    if (Logger.DEBUG) Log.d(TAG, "setDataChanged");
    customInput.serErrorInverseBindingListener(listener);
  }

  @BindingAdapter("labelText")
  public static void setLabelText(CustomInputDefault customInput, String text) {
    if (Logger.DEBUG) Log.d(TAG, "setLabelText: " + text);
    customInput.getBinding().setLabelText(text);
  }

  @BindingAdapter("hintText")
  public static void setHintText(CustomInputDefault customInput, String text) {
    if (Logger.DEBUG) Log.d(TAG, "setHintText: " + text);
    customInput.getBinding().setHintText(text);
  }

  @BindingAdapter("regex")
  public static void setRegex(CustomInputDefault customInput, String regex) {
    if (Logger.DEBUG) Log.d(TAG, "setRegex: " + regex);
    customInput.setRegex(regex);
  }

  @BindingAdapter("maxLength")
  public static void setMaxLength(CustomInputDefault customInput, Integer maxLength) {
    if (Logger.DEBUG) Log.d(TAG, "setMaxLength");
    customInput.getBinding().setMaxLength(maxLength);
  }

  @BindingAdapter("mask")
  public static void setMask(CustomInputDefault customInput, String mask) {
    if (Logger.DEBUG) Log.d(TAG, "setMask");
    customInput.setMask(mask);
  }

  @BindingAdapter("maxNumber")
  public static void setMaxNumber(CustomInputDefault customInput, Integer maxNumber) {
    if (Logger.DEBUG) Log.d(TAG, "setMask");
    customInput.setMaxNumber(maxNumber);
  }

  @BindingAdapter("inputType")
  public static void setInputType(CustomInputDefault customInput, String inputType) {
    if (Logger.DEBUG) Log.d(TAG, "setInputType");
    customInput.setInputType(inputType);
  }

  @BindingAdapter("image")
  public static void setImage(CustomInputDefault customInput, String imageType) {
    if (Logger.DEBUG) Log.d(TAG, "setInputType");
    customInput.setImage(imageType);
  }

  @BindingAdapter("focusable")
  public static void setIsFocusable(CustomInputDefault customInput, boolean focusable) {
    if (Logger.DEBUG) Log.d(TAG, "setInputType");
    customInput.setIsFocusable(focusable);
  }
}

