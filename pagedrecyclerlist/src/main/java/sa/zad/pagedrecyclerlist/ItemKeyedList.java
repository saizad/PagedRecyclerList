package sa.zad.pagedrecyclerlist;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

public abstract class ItemKeyedList<M, ItemView extends View & AppListAdapter.AppAdapterItem<M>, Key> extends ListSelection<M, ItemView> implements
    ItemKeyedListDataSource.ItemKeyedListDataSourceListener<Key, M> {

  private LifecycleOwner lifecycleOwner;

  public ItemKeyedList(Context context) {
    this(context, null);
  }

  public ItemKeyedList(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ItemKeyedList(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  public void recreateList() {
    getListAdapter().setItemKeyedDataSource(lifecycleOwner, this);
  }

  public void init(@NonNull LifecycleOwner lifecycleOwner) {
    this.lifecycleOwner = lifecycleOwner;
  }

  @Override
  public abstract void dataCallBack(Key next, @NonNull Action1<List<M>> callBack);

  @Override
  public abstract Key getKey(@NonNull M activityType);
}
