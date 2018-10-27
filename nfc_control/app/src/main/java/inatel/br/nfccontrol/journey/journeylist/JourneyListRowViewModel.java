package inatel.br.nfccontrol.journey.journeylist;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import javax.inject.Inject;

public class JourneyListRowViewModel extends BaseObservable {

  public final ObservableField<String> day;

  public final ObservableField<String> enterTimeOne;

  public final ObservableField<String> exitTimeOne;

  public final ObservableField<String> enterTimeTwo;

  public final ObservableField<String> exitTimeTwo;

  @Inject
  public JourneyListRowViewModel() {
    day = new ObservableField<>();
    enterTimeOne = new ObservableField<>();
    exitTimeOne = new ObservableField<>();
    enterTimeTwo = new ObservableField<>();
    exitTimeTwo = new ObservableField<>();
  }


}
