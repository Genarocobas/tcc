package inatel.br.nfccontrol.account;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import inatel.br.nfccontrol.di.Injector;
import inatel.br.nfccontrol.R;
import inatel.br.nfccontrol.account.login.LoginFragment;
import inatel.br.nfccontrol.utils.FragmentHelper;
import inatel.br.nfccontrol.utils.LoadingConstants;
import inatel.br.nfccontrol.utils.LoadingDialogFragment;
import inatel.br.nfccontrol.utils.Logger;

/**
 * Account Activity that manage login.
 *
 * @author Guilherme Ribeiro de Melo Yabu <guilhermeyabu@inatel.br>
 * @since 15/08/2018.
 */
public class AccountActivity extends AppCompatActivity {

  public static final String TAG = Logger.getTag();

  @Inject
  LoginFragment mLoginFragment;

  @Inject
  LoadingDialogFragment mLoadingDialogFragment;

  @Inject
  AccountViewModel mViewModel;

  public AccountActivity() {
  }

  public static void startActivity(Context context) {
    if (Logger.DEBUG) Log.d(TAG, "startActivity");
    Intent intent = new Intent(context, AccountActivity.class)
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_account);
    Injector.getApplicationComponent().inject(this);

    mLoginFragment.getAccountSubject().observe(this, this::nextStep);

    attachContainerFragment();
  }

  private void nextStep(String nextStep) {
    if (Logger.DEBUG) Log.d(TAG, "nextStep: " + nextStep);
    if (nextStep.equals(LoadingConstants.SHOW_LOADING)){
      mLoadingDialogFragment.show(getSupportFragmentManager(), LoadingDialogFragment.TAG);
    } else {
      mLoadingDialogFragment.dismiss();
    }
  }

  private void attachContainerFragment() {
    if (Logger.DEBUG) Log.d(TAG, "attachContainerFragment");
    FragmentHelper.replaceFragment(getSupportFragmentManager(), mLoginFragment, R.id.contentFrame,
        null);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    int fragmentBackStackCount = getSupportFragmentManager().getBackStackEntryCount();
    if (Logger.DEBUG) Log.d(TAG, "onBackPressed: " + fragmentBackStackCount);
    String fragmentTag;

    if (fragmentBackStackCount > 0) {
      fragmentTag = getSupportFragmentManager()
          .getBackStackEntryAt(fragmentBackStackCount - 1).getName();
      if (Logger.DEBUG) Log.d(TAG, "onBackPressed2: " + fragmentTag);
    } else {
      fragmentTag = this.getClass().getSimpleName();
    }
  }
}
