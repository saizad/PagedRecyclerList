package sa.zad.pagedrecyclerlist;

import android.view.View;

public interface ItemOnClickListener<M, I extends View & AppListAdapter.AppAdapterItem<M>> extends ItemCallBack<M, I> {

}