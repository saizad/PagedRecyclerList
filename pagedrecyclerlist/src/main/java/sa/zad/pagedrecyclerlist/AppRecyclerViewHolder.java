package sa.zad.pagedrecyclerlist;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Sa-ZAD on 2017-11-29.
 */

public class AppRecyclerViewHolder<T, I extends View & AppListAdapter.AppAdapterItem<T>> extends RecyclerView.ViewHolder {

    public final I appAdapterItem;

    public AppRecyclerViewHolder(I itemView) {
        super(itemView);
        appAdapterItem = itemView;
    }

    public I getItemView() {
        return appAdapterItem;
    }
}
