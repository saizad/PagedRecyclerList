package sa.zad.pagedrecyclerlist;

import android.view.View;

public interface ItemCallBack<M, I extends View & AppListAdapter.AppAdapterItem<M>> {
    /**
     * called before {@link AppListAdapter.AppAdapterItem#bind(M)}
     *
     * @param item      list model item
     * @param itemView  list item view
     * @param itemIndex index in list
     */
    void itemCall(M item, I itemView, int itemIndex);
}