/*
 * Copyright (C) 2018 Instituto Nacional de Telecomunicações
 *
 * All rights are reserved. Reproduction in whole or part is
 * prohibited without the written consent of the copyright owner.
 *
 */

package inatel.br.nfccontrol.account;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.util.Log;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

import inatel.br.nfccontrol.data.ApplicationDatabase;
import inatel.br.nfccontrol.data.model.JourneyConfig;
import inatel.br.nfccontrol.data.model.User;
import inatel.br.nfccontrol.di.qualifier.RetrofitQualifier;
import inatel.br.nfccontrol.utils.Logger;
import inatel.br.nfccontrol.utils.SecurityHelper;
import retrofit2.Retrofit;

/**
 * Class responsible to handle the data related to the Account Service and the RoomDatabase.
 */
@Singleton
public class AccountController {

  public static final String TAG = Logger.getTag();

  static ApplicationDatabase mApplicationDatabase;

  private User mAuthenticatedUser;

  @Inject
  Application mApplication;

  @Inject
  SecurityHelper mSecurityHelper;

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
    myExecutor.execute(() -> mApplicationDatabase.userDao().update(user));
  }

  public void insertJourneyConfig(JourneyConfig journeyConfig) {
    Executor myExecutor = Executors.newSingleThreadExecutor();
    myExecutor.execute(() -> {
      journeyConfig.setId(mApplicationDatabase.journeyConfigDao().insert(journeyConfig));
      mAuthenticatedUser.setJourneyConfig(journeyConfig);
      mAuthenticatedUser.setConfigured(true);
      updateUser(mAuthenticatedUser);
      Log.d(TAG, "insertJourneyConfig: " + journeyConfig.getId() + "/" + mAuthenticatedUser.getJourneyConfig().getId());
    });
  }

  /**
   * Save the access and renew token got from JFL server.
   *
   * @param accessToken Token used to access the server API.
   * @throws Exception All exceptions.
   */
  public void setApplicationAccessToken(String accessToken) throws Exception {
    mSecurityHelper.persistApplicationApiToken(accessToken);
  }

  public MediatorLiveData<User> getAuthenticatedUser() {
    MediatorLiveData<User> mediatorLiveData = new MediatorLiveData<>();
    LiveData<User> userLiveData = mApplicationDatabase.userDao().getAuthenticatedUser(true);
    mediatorLiveData.addSource(userLiveData,
        mediatorLiveData::setValue);

    return mediatorLiveData;
  }

  public void setConnectedUser(User user) {
    mAuthenticatedUser = user;
  }

  public User getConnectedUser() {
    return mAuthenticatedUser;
  }
}