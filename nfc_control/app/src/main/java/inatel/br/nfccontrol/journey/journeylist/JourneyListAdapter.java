package inatel.br.nfccontrol.journey.journeylist;

import android.content.Context;
import android.view.ViewGroup;

import javax.inject.Inject;
import javax.inject.Provider;

import inatel.br.nfccontrol.data.model.Journey;
import inatel.br.nfccontrol.databinding.CardJourneyBinding;
import inatel.br.nfccontrol.utils.Logger;
import inatel.br.nfccontrol.utils.commom.adapter.BindingHolder;
import inatel.br.nfccontrol.utils.commom.adapter.GenericAdapter;

public class JourneyListAdapter extends GenericAdapter<Journey, BindingHolder<CardJourneyBinding>> {

  private static final String TAG = Logger.getTag();

  @Inject
  Provider<JourneyListRowViewModel> mViewModel;

  @Inject
  Context mContext;

  @Inject
  public JourneyListAdapter(){
  }

  @Override
  public BindingHolder<CardJourneyBinding> onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override
  public void onBindViewHolder(BindingHolder<CardJourneyBinding> holder, int position) {

  }
}
