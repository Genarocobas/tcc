/*
 * Copyright (C) 2018 Instituto Nacional de Telecomunicações
 *
 * All rights are reserved. Reproduction in whole or part is
 * prohibited without the written consent of the copyright owner.
 *
 */

package inatel.br.nfccontrol.account;

import android.app.Application;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import inatel.br.nfccontrol.data.ApplicationDatabase;
import inatel.br.nfccontrol.data.model.User;
import inatel.br.nfccontrol.di.qualifier.RetrofitQualifier;
import retrofit2.Retrofit;

/**
 * Class responsible to handle the data related to the Account Service and the RoomDatabase.
 */
@Singleton
public class AccountController {

  static ApplicationDatabase mApplicationDatabase;

  @Inject
  Application mApplication;

  private User mAuthenticatedUser;

  @Inject
  public AccountController(@RetrofitQualifier Retrofit retrofit,
      ApplicationDatabase applicationDatabase) {
    mApplicationDatabase = applicationDatabase;
  }

  public void insertAccount(User user) {
    Executor myExecutor = Executors.newSingleThreadExecutor();
    mAuthenticatedUser = user;
    myExecutor.execute(() -> mAuthenticatedUser.setId(mApplicationDatabase.userDao().insert(user)));
  }

  public void updateUser(User user) {
    Executor myExecutor = Executors.newSingleThreadExecutor();
    mAuthenticatedUser = user;
    myExecutor.execute(() -> mApplicationDatabase.userDao().update(user));
  }
}