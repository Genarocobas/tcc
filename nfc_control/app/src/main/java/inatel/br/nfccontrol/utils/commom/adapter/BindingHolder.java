package inatel.br.nfccontrol.utils.commom.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Generic ViewHolder for implementation of RecyclerView.
 */
public class BindingHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

  public final T binding;

  public BindingHolder(@NonNull Context context, @NonNull ViewGroup parent, @LayoutRes int
      layoutResId) {
    super(LayoutInflater.from(context).inflate(layoutResId, parent, false));
    this.binding = DataBindingUtil.bind(itemView);
  }
}
