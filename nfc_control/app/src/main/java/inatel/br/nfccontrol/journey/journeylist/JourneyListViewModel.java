package inatel.br.nfccontrol.journey.journeylist;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import javax.inject.Inject;

import inatel.br.nfccontrol.utils.Logger;

public class JourneyListViewModel extends RecyclerView.Adapter<JourneyListViewHolder> {

  private static final String TAG = Logger.getTag();

  @Inject
  public JourneyListViewModel(){

  }

  @Override
  public JourneyListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override
  public void onBindViewHolder(JourneyListViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return 0;
  }
}
