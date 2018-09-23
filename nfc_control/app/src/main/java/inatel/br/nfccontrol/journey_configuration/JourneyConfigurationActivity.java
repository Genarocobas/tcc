package inatel.br.nfccontrol.journey_configuration;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import javax.inject.Inject;

import inatel.br.nfccontrol.R;
import inatel.br.nfccontrol.databinding.ActivityJourneyConfigurationBinding;
import inatel.br.nfccontrol.di.Injector;
import inatel.br.nfccontrol.utils.Logger;
import inatel.br.nfccontrol.utils.Util;

public class JourneyConfigurationActivity extends AppCompatActivity {

  public static final String TAG = Logger.getTag();

  private ActivityJourneyConfigurationBinding mBinding;

  @Inject
  JourneyConfigurationViewModel mViewModel;

  @Inject
  public JourneyConfigurationActivity(){
  }

  public static void startActivity(Context context){
    if (Logger.DEBUG) Log.d(TAG, "startActivity");
    Intent intent = new Intent(context, JourneyConfigurationActivity.class)
        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Injector.getApplicationComponent().inject(this);

    mBinding = DataBindingUtil.setContentView(this, R.layout.activity_journey_configuration);
    mBinding.setViewModel(mViewModel);
    mViewModel.setBinding(mBinding);

    setupSeekBars();
  }

  @Override
  protected void onResume() {
    super.onResume();
    mViewModel.onResume();
  }

  private void setupSeekBars(){
    setupLunchSeekBar();
    setupContinuousSeekBar();
    setupJourneySeekBar();
  }

  private void setupLunchSeekBar(){
    DiscreteSeekBar lunchSeekBar = mBinding.dsbLunchInterval;
    lunchSeekBar.setIndicatorFormatter("01:00");
    lunchSeekBar.setMin(0);
    lunchSeekBar.setMax(Util.getStepsTime(1, 0, 2, 0, 10));
    lunchSeekBar.setProgress(0);

    lunchSeekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
      @Override
      public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
        seekBar.setIndicatorFormatter(Util.getLunchIntervalBubbleText(value));
      }

      @Override
      public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

      }
    });
  }

  private void setupContinuousSeekBar(){
    DiscreteSeekBar continuousSeekBar = mBinding.dsbContinuousInterval;
    continuousSeekBar.setIndicatorFormatter("06:00");
    continuousSeekBar.setMin(0);
    continuousSeekBar.setMax(Util.getStepsTime(4, 0, 7, 0, 10));
    continuousSeekBar.setProgress(12);

    continuousSeekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
      @Override
      public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
        seekBar.setIndicatorFormatter(Util.getContinuousTimeBubbleText(value));
      }

      @Override
      public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

      }
    });
  }

  private void setupJourneySeekBar(){
    DiscreteSeekBar journeySeekBar = mBinding.dsbJourneyInterval;
    journeySeekBar.setIndicatorFormatter("11:00");
    journeySeekBar.setMin(0);
    journeySeekBar.setMax(Util.getStepsTime(10, 0, 12, 0, 10));
    journeySeekBar.setProgress(6);

    journeySeekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
      @Override
      public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
        seekBar.setIndicatorFormatter(Util.getJourneyIntervalBubbleText(value));
      }

      @Override
      public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(DiscreteSeekBar seekBar) {

      }
    });
  }
}
