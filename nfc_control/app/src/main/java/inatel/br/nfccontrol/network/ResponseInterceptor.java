/*
 * Copyright (C) 2018 Instituto Nacional de Telecomunicações
 *
 * All rights are reserved. Reproduction in whole or part is
 * prohibited without the written consent of the copyright owner.
 *
 */

package inatel.br.nfccontrol.network;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import javax.inject.Inject;

import inatel.br.nfccontrol.di.Injector;
import inatel.br.nfccontrol.utils.Logger;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Create interceptor to add redirect the user to the login screen if the token is invalid.
 */
public class ResponseInterceptor implements Interceptor {

  private static final String TAG = Logger.getTag();

  private static final String HTTP_BASIC_AUTHORIZATION_PREFIX = "Basic ";
  private static final int HTTP_RESPONSE_CODE_401 = 401;
  private static final int HTTP_RESPONSE_CODE_200 = 200;

  @Inject
  Context mContext;

  public ResponseInterceptor() {
    if (Logger.DEBUG) Log.d(TAG, "constructor");
    Injector.getApplicationComponent().inject(this);
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    if (Logger.DEBUG) Log.d(TAG, "intercept:" + chain.toString());
    Request request = chain.request();
    Response response = chain.proceed(request);

    if (response.code() == HTTP_RESPONSE_CODE_401) {
      Log.d(TAG, "intercept: " + HTTP_RESPONSE_CODE_401);
    }

    return response;
  }
}