package inatel.br.nfccontrol;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import inatel.br.nfccontrol.di.Injector;
import inatel.br.nfccontrol.utils.Logger;

public class TccApplication extends Application {

  private static final String TAG = Logger.getTag();

  @Override
  public void onCreate() {
    super.onCreate();
    if (Logger.DEBUG) Log.d(TAG, "onCreate");
  }

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    if (Logger.DEBUG) Log.d(TAG, "APJ app started, version: " + getPackageInfo().versionName);
    Injector.initializeApplicationComponent(this);
    Injector.getApplicationComponent().inject(this);
  }

  private PackageInfo getPackageInfo() {
    if (Logger.DEBUG) Log.d(TAG, "getPackageInfo");
    final PackageManager packageManager = getPackageManager();
    try {
      return packageManager.getPackageInfo(getPackageName(), 0);
    } catch (PackageManager.NameNotFoundException e) {
      throw new RuntimeException("Could not find package info of " + getPackageName());
    }
  }
}
