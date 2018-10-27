package inatel.br.nfccontrol.journey;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import javax.inject.Inject;

import inatel.br.nfccontrol.R;
import inatel.br.nfccontrol.databinding.ActivityJourneyBinding;
import inatel.br.nfccontrol.di.Injector;
import inatel.br.nfccontrol.journey.journeylist.JourneyListFragment;
import inatel.br.nfccontrol.utils.FragmentHelper;
import inatel.br.nfccontrol.utils.Logger;

public class JourneyActivity extends AppCompatActivity {

  public static final String TAG = Logger.getTag();

  private ActivityJourneyBinding mBinding;

  private ActionBar mActionBar;

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
    Injector.getApplicationComponent().inject(this);
    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_journey);
    mBinding.setViewModel(mViewModel);

    setupToolbar();
    attachContainerFragment();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.journey_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (Logger.DEBUG) Log.d(TAG, "onOptionsItemSelected: " + item.getItemId());
    switch (item.getItemId()) {
      case R.id.edit_configuration:
        Toast.makeText(this, "Editar", Toast.LENGTH_SHORT).show();
        return true;
      case R.id.exit_application:
        Toast.makeText(this, "Sair", Toast.LENGTH_SHORT).show();
        return true;
      default:
        break;
    }

    return false;
  }

  private void setupToolbar() {
    Toolbar toolbar = findViewById(R.id.toolbar);
    toolbar.setTitleTextColor(getResources().getColor(R.color.white));
    toolbar.setTitle(R.string.journey_activity_title);
    setSupportActionBar(toolbar);
    mActionBar = getSupportActionBar();

    if (mActionBar != null) {
      mActionBar.setDisplayHomeAsUpEnabled(false);
    }
  }

  private void attachContainerFragment() {
    if (Logger.DEBUG) Log.d(TAG, "attachContainerFragment");
    FragmentHelper.replaceFragment(getSupportFragmentManager(), mJourneyListFragment,
        R.id.contentFrame, null);
  }
}
