package inatel.br.nfccontrol.journey;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

import inatel.br.nfccontrol.R;
import inatel.br.nfccontrol.di.Injector;
import inatel.br.nfccontrol.journey.journeylist.JourneyListFragment;
import inatel.br.nfccontrol.utils.FragmentHelper;
import inatel.br.nfccontrol.utils.Logger;

public class JourneyActivity extends AppCompatActivity {

  public static final String TAG = Logger.getTag();

  @Inject
  JourneyViewModel mViewModel;

  @Inject
  JourneyListFragment mJourneyListFragment;

  public JourneyActivity() {
  }

  public static void startActivity(Context context) {
    if (Logger.DEBUG) {
      Log.d(TAG, "Start Journey Activity");
    }

    Intent intent = new Intent(context, JourneyActivity.class).addFlags(
        Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_journey);
    Injector.getApplicationComponent().inject(this);

    attachContainerFragment();
  }

  private void attachContainerFragment() {
    if (Logger.DEBUG) Log.d(TAG, "attachContainerFragment");
    FragmentHelper.replaceFragment(getSupportFragmentManager(), mJourneyListFragment,
        R.id.contentFrame, null);
  }
}
