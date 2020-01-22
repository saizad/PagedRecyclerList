package sa.zad.pagedrecyclerlist;

import android.view.View;

public interface ItemDoubleTapListener<M, I extends View & AppListAdapter.AppAdapterItem<M>>{

  boolean itemCall(M item, I itemView, int itemIndex);

}