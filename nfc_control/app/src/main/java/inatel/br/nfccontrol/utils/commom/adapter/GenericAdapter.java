package inatel.br.nfccontrol.utils.commom.adapter;

import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;

import java.util.Collection;
import java.util.List;

import inatel.br.nfccontrol.utils.Logger;

public abstract class GenericAdapter<T, VH extends RecyclerView.ViewHolder> extends
    RecyclerView.Adapter<VH> {

  public static final String TAG = Logger.getTag();

  protected List<T> mList;

  @Override
  public int getItemCount() {
    return mList.size();
  }

  /**
   * Reset of Dataset in RecyclerView.
   *
   * @param items Collection of items.
   */
  @UiThread
  public void repopulate(Collection<T> items) {
    clear();
    addAll(items);
    notifyDataSetChanged();
  }

  public T getItem(int position) {
    return mList.get(position);
  }

  /**
   * Add a Collection of the items in Dataset of the RecyclerView.
   *
   * @param items Collection of items.
   */
  public void addAll(Collection<T> items) {
    mList.addAll(items);
  }

  /**
   * Clear all items of the Dataset of the RecyclerView.
   */
  public void clear() {
    mList.clear();
  }

}
