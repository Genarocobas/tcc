package inatel.br.nfccontrol.custom_view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import inatel.br.nfccontrol.utils.Logger;

/**
 * Generic {@link RecyclerView} with empty message;.
 *
 * @author Diógenes Aparecido Rezende <diogenes@inatel.br>
 * @since : 19/07/2018.
 */
public class CustomRecyclerView extends RecyclerView {

  private static final String TAG = Logger.getTag();

  private View mEmptyView;

  final AdapterDataObserver observer = new AdapterDataObserver() {
    @Override
    public void onChanged() {
      if (Logger.DEBUG) Log.d(TAG, "onChanged");
      super.onChanged();
      initEmptyView();
    }

    @Override
    public void onItemRangeInserted(int positionStart, int itemCount) {
      super.onItemRangeInserted(positionStart, itemCount);
      if (Logger.DEBUG) Log.d(TAG, "onItemRangeInserted");
      initEmptyView();
    }

    @Override
    public void onItemRangeRemoved(int positionStart, int itemCount) {
      super.onItemRangeRemoved(positionStart, itemCount);
      if (Logger.DEBUG) Log.d(TAG, "onItemRangeRemoved");
      initEmptyView();
    }
  };

  public CustomRecyclerView(Context context) {
    super(context);
    if (Logger.DEBUG) Log.d(TAG, "CustomRecyclerView");
  }

  public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    if (Logger.DEBUG) Log.d(TAG, "CustomRecyclerView");
  }

  public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    if (Logger.DEBUG) Log.d(TAG, "CustomRecyclerView");
  }

  private void initEmptyView() {
    if (Logger.DEBUG) Log.d(TAG, "initEmptyView");
    if (mEmptyView != null) {
      mEmptyView.setVisibility(
          getAdapter() == null || getAdapter().getItemCount() == 0 ? VISIBLE : GONE);
      CustomRecyclerView.this.setVisibility(
          getAdapter() == null || getAdapter().getItemCount() == 0 ? GONE : VISIBLE);
    }
  }

  @Override
  public void setAdapter(Adapter adapter) {
    if (Logger.DEBUG) Log.d(TAG, "setAdapter");
    Adapter oldAdapter = getAdapter();
    super.setAdapter(adapter);

    if (oldAdapter != null) {
      oldAdapter.unregisterAdapterDataObserver(observer);
    }

    if (adapter != null) {
      adapter.registerAdapterDataObserver(observer);
    }
  }

  public void setEmptyView(View view) {
    if (Logger.DEBUG) Log.d(TAG, "setAdapter");
    this.mEmptyView = view;
    initEmptyView();
  }
}
