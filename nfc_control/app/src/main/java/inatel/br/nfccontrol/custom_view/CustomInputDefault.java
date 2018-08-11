package inatel.br.nfccontrol.custom_view;

/*
 * Copyright (C) 2018 Instituto Nacional de Telecomunicações
 *
 * All rights are reserved. Reproduction in whole or part is
 * prohibited without the written consent of the copyright owner.
 *
 */

import android.content.Context;
import android.databinding.InverseBindingListener;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import inatel.br.nfccontrol.databinding.CustomInputViewDefaultBinding;
import inatel.br.nfccontrol.utils.Logger;
import inatel.br.nfccontrol.utils.ValidationConstants;

/**
 * Compound View to handle all inputs in the application
 * Contains a TextView and a Custom Text Input Layout
 * Also deals with validation, mask, two-way data binding and max length, setting the error
 * according
 * to the regex
 *
 * @author Guilherme Yabu <guilhermeyabu@inatel.br>
 * @since 29/05/2018.
 */
public class CustomInputDefault extends LinearLayout implements TextWatcher {

  private static final String TAG = Logger.getTag();

  private String mMask = null;

  private boolean isRunning = false;

  private boolean isDeleting = false;

  private InverseBindingListener mDataInverseBindingListener;

  private InverseBindingListener mErrorInverseBindingListener;

  private String mRegex;

  private boolean mIsErrorEnabled;

  private CustomInputViewDefaultBinding mBinding;

  private TextView mLabel;

  private EditText mEditText;

  private TextView mTextViewError;

  private Drawable mDrawableErrorState;

  private Drawable mDrawableNormalState;

  private Integer mMaxNumber;

  private String mInputType;

  public CustomInputDefault(Context context) {
    super(context);
    initializeViews(context);
    setBackgroundsForStates();
  }

  public CustomInputDefault(Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
    initializeViews(context);
    setBackgroundsForStates();
  }

  public CustomInputDefault(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    initializeViews(context);
    setBackgroundsForStates();
  }

  public void setDataInverseBindingListener(InverseBindingListener dataListener) {
    mDataInverseBindingListener = dataListener;
  }

  public void serErrorInverseBindingListener(InverseBindingListener errorListener) {
    mErrorInverseBindingListener = errorListener;
  }

  public void setRegex(String regex) {
    mRegex = regex;
  }

  public String getInputData() {
    if (Logger.DEBUG) Log.d(TAG, "getInputData: " + mEditText.getText().toString());
    return mEditText.getText().toString();
  }

  public boolean isErrorEnabled() {
    return mIsErrorEnabled;
  }

  public void setErrorEnabled(boolean errorEnabled) {
    mIsErrorEnabled = errorEnabled;
  }

  public void setMask(String mask) {
    mMask = mask;
  }

  public void setMaxNumber(Integer maxNumber) {
    mMaxNumber = maxNumber;
  }

  public void setInputType(String inputType) {
    mInputType = inputType;
    switch (mInputType) {
      case ValidationConstants.INPUT_TYPE_DECIMAL:
        mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        break;
      case ValidationConstants.INPUT_TYPE_DECIMAL_PASSWORD:
        mEditText.setInputType(
            InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        break;
      case ValidationConstants.INPUT_TYPE_TEXT_NORMAL:
        mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
        break;
      case ValidationConstants.INPUT_TYPE_TEXT_PASSWORD:
        mEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        break;
      case ValidationConstants.INPUT_TYPE_NUMBER_PHONE:
        mEditText.setInputType(InputType.TYPE_CLASS_PHONE);
        break;
    }
  }

  private void setBackgroundsForStates() {
    if (Logger.DEBUG) {
      Logger.d(CustomInputDefault.class, "setBackgroundsForStates");
    }
    setDrawableNormalState(getResources().getDrawable(R.drawable.rounded_edit_text));
    setDrawableErrorState(getResources().getDrawable(R.drawable.rounded_edit_text_error));
  }

  private void initializeViews(Context context) {
    LayoutInflater inflater = LayoutInflater.from(context);
    mBinding = CustomInputViewDefaultBinding.inflate(inflater, this, true);
    mLabel = mBinding.tvLabel;
    mEditText = mBinding.etInput;
    mTextViewError = mBinding.tvErrorLabel;
    mEditText.removeTextChangedListener(this);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    mEditText.addTextChangedListener(this);
    Drawable drawable = mEditText.getBackground();
    if (drawable != null) {
      drawable.clearColorFilter();
    }
  }

  public CustomInputViewDefaultBinding getBinding() {
    return mBinding;
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    isDeleting = count > after;
  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {
    if (Logger.DEBUG) {
      Log.d(TAG, "onTextChanged :" + s.toString());
    }
    mBinding.setData(s.toString());
    if (mDataInverseBindingListener != null) {
      mDataInverseBindingListener.onChange();
    }
  }

  @Override
  public void afterTextChanged(Editable editable) {
    if (Logger.DEBUG) {
      Log.d(TAG, "afterTextChanged :" + editable.toString());
    }

    checkInputValidation(editable);

    if (mMask != null) {
      if (isRunning || isDeleting) {
        return;
      }
      isRunning = true;
      int editableLength = editable.length();
      if (editableLength < mMask.length()) {
        if (mMask.charAt(editableLength) != '#') {
          editable.append(mMask.charAt(editableLength));
        } else if (mMask.charAt(editableLength - 1) != '#') {
          editable.insert(editableLength - 1, mMask, editableLength - 1, editableLength);
        }
      }

      isRunning = false;
    }
  }

  private void checkInputValidation(Editable editable) {
    if (mMaxNumber == null || mRegex == null || mInputType == null) {
      return;
    }

    if (TextUtils.isEmpty(editable)) {
      enableError(getContext().getString(R.string.field_empty_error_string));
    } else if (checkMaxNumber()) {
      enableError(String.format(getContext().getString(R.string.field_max_value_error_string),
          String.valueOf(mMaxNumber)));
    } else if (!editable.toString().matches(mRegex)) {
      enableError(getContext().getString(R.string.field_regex_error_string));
    } else {
      disableError();
    }
  }

  private boolean checkMaxNumber() {
    if (mInputType.equals(ValidationConstants.INPUT_TYPE_DECIMAL) || mInputType.equals(
        ValidationConstants.INPUT_TYPE_DECIMAL_PASSWORD)) {
      return getIntegerFromString(mEditText.getText().toString()) > mMaxNumber;
    }

    return false;
  }

  private int getIntegerFromString(String value) {
    if (value != null && TextUtils.isDigitsOnly(value) && value.length() > 0) {
      return Integer.valueOf(value);
    }

    return 0;
  }

  public void enableError(String errorMessage) {
    if (Logger.DEBUG) Log.d(TAG, "enableError :");
    mLabel.setTextColor(getResources().getColor(R.color.field_error_color));
    mTextViewError.setVisibility(View.VISIBLE);
    mTextViewError.setText(errorMessage);
    mIsErrorEnabled = true;
    replaceBackground();
    if (mErrorInverseBindingListener != null) mErrorInverseBindingListener.onChange();
  }

  public void disableError() {
    if (Logger.DEBUG) Log.d(TAG, "disableError :");
    mLabel.setTextColor(getResources().getColor(R.color.black));
    mTextViewError.setVisibility(View.INVISIBLE);
    mIsErrorEnabled = false;
    replaceBackground();
    if (mErrorInverseBindingListener != null) mErrorInverseBindingListener.onChange();
  }

  /**
   * Change background of the {@link EditText} internal instance
   */
  private void replaceBackground() {
    if (Logger.DEBUG) {
      Logger.d(CustomInputDefault.class, "replaceBackground");
    }

    if (mEditText != null) {
      Drawable drawable = mEditText.getBackground();
      if (drawable != null) {
        drawable.clearColorFilter();
      }

      mEditText.setBackground(isErrorEnabled() ? mDrawableErrorState : mDrawableNormalState);
    }
  }

  private void setDrawableErrorState(Drawable mDrawableErrorState) {
    this.mDrawableErrorState = mDrawableErrorState;
  }

  private void setDrawableNormalState(Drawable mDrawableNormalState) {
    this.mDrawableNormalState = mDrawableNormalState;
  }

  public void setImage(String image) {
    switch (image) {
      case ValidationConstants.EMAIL_IMAGE:
        mEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_email, 0, 0, 0);
        mEditText.setCompoundDrawablePadding(20);
        break;
      case ValidationConstants.PASSWORD_IMAGE:
        mEditText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_password, 0, 0, 0);
        mEditText.setCompoundDrawablePadding(20);
        break;
      default:
        break;
    }
  }

  public void setIsFocusable(boolean isFocusable) {
    mEditText.setFocusable(isFocusable);
    if (!isFocusable) {
      mEditText.setTextColor(getResources().getColor(R.color.disable_text_color));
    }
  }
}

