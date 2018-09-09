/*
 * Copyright (C) 2018 Instituto Nacional de Telecomunicações
 *
 * All rights are reserved. Reproduction in whole or part is
 * prohibited without the written consent of the copyright owner.
 *
 */

package inatel.br.nfccontrol.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import inatel.br.nfccontrol.BuildConfig;
import inatel.br.nfccontrol.di.qualifier.RetrofitQualifier;
import inatel.br.nfccontrol.network.HeaderInterceptor;
import inatel.br.nfccontrol.network.ResponseInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * {@link Module} containing classes needed to do network operations.
 *
 * @author Daniela Mara Silva <danielamara@inatel.br>
 * @since 30/01/2018.
 */
@Module
public class NetworkModule {

  @RetrofitQualifier
  @Provides
  @Singleton
  Retrofit providesRetrofitAdapter() {
    final OkHttpClient okHttpClient = new OkHttpClient.Builder()
        .addInterceptor(new HeaderInterceptor())
        .addInterceptor(new ResponseInterceptor())
        .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .build();

    return new Retrofit.Builder()
        .baseUrl(BuildConfig.API_ENDPOINT)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
  }
}