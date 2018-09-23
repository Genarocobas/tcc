package inatel.br.nfccontrol.journey_configuration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.inject.Inject;

import inatel.br.nfccontrol.R;
import inatel.br.nfccontrol.di.Injector;
import inatel.br.nfccontrol.utils.Logger;

public class JourneyConfigurationActivity extends AppCompatActivity {

  public static final String TAG = Logger.getTag();

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
    setContentView(R.layout.activity_journey_configuration);
    Injector.getApplicationComponent().inject(this);

  }
}
