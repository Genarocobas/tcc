/*
 * Copyright (C) 2018 Instituto Nacional de Telecomunicações
 *
 * All rights are reserved. Reproduction in whole or part is
 * prohibited without the written consent of the copyright owner.
 *
 */

package inatel.br.nfccontrol.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import dagger.Module;
import dagger.Provides;
import inatel.br.nfccontrol.R;
import inatel.br.nfccontrol.di.preference.BooleanPreference;
import inatel.br.nfccontrol.di.preference.StringPreference;
import inatel.br.nfccontrol.di.qualifier.ApiTokenPreference;
import inatel.br.nfccontrol.di.qualifier.FirstTimeOnAppPreference;
import inatel.br.nfccontrol.di.qualifier.IsAuthenticatedPreference;
import inatel.br.nfccontrol.di.qualifier.RefreshTokenPreference;
import inatel.br.nfccontrol.di.qualifier.SecretKeyPreference;
import javax.inject.Singleton;

/**
 * {@link Module} to provide all application {@link SharedPreferences}.
 *
 * @author Everton Takashi Nishiyama <evertontn@inatel.br>
 * @since 23/01/2018.
 */
@Module(includes = DataModule.class)
public class SharedPreferenceModule {

  @Provides
  @Singleton
  @SecretKeyPreference
  StringPreference provideSecretKeyPreference(Context context,
                                              SharedPreferences prefs) {
    final String key = context.getString(R.string.shared_preferences_secret_key);
    return new StringPreference(prefs, key, null);
  }

  @Provides
  @Singleton
  @ApiTokenPreference
  StringPreference provideApiTokenPreference(Context context,
                                             SharedPreferences prefs) {
    final String key = context.getString(R.string.shared_preferences_api_token);
    return new StringPreference(prefs, key, null);
  }

  @Provides
  @Singleton
  @RefreshTokenPreference
  StringPreference provideRefreshTokenPreference(Context context,
                                                 SharedPreferences prefs) {
    final String key = context.getString(R.string.shared_preferences_refresh_token);
    return new StringPreference(prefs, key, null);
  }

  @Provides
  @Singleton
  @IsAuthenticatedPreference
  BooleanPreference provideIsAuthenticatedPreference(Context context,
                                                     SharedPreferences prefs) {
    final String key = context.getString(R.string.shared_preferences_is_authenticated);
    return new BooleanPreference(prefs, key, false);
  }

  @Provides
  @Singleton
  @FirstTimeOnAppPreference
  BooleanPreference provideFirstTimeOnAppPreference(Context context,
                                                    SharedPreferences prefs) {
    final String key = context.getString(R.string.shared_preferences_agreed_with_terms_of_use);
    return new BooleanPreference(prefs, key, true);
  }
}