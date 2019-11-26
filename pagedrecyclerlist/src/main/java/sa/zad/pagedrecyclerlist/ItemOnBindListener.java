package sa.zad.pagedrecyclerlist;

import android.view.View;

public interface ItemOnBindListener<M, I extends View & AppListAdapter.AppAdapterItem<M>> extends ItemCallBack<M, I> {

}