package sa.zad.pagedrecyclerlist;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

public abstract class PagedKeyedList<M, I extends View & AppListAdapter.AppAdapterItem<M>> extends ListSelection<M, I> implements
    PageKeyedListDataSource.PageKeyedListDataSourceListener<Integer, M> {

  private LifecycleOwner lifecycleOwner;

  public PagedKeyedList(Context context) {
    this(context, null);
  }

  public PagedKeyedList(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public PagedKeyedList(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  public void recreateList() {
    getListAdapter().setPageKeyedDataSource(lifecycleOwner, this);
  }

  public void init(@NonNull LifecycleOwner lifecycleOwner) {
    this.lifecycleOwner = lifecycleOwner;
  }

  @Override
  public abstract void getData(Integer next, @NonNull Action1<PageDataModel<Integer, List<M>>> callBack);
}
