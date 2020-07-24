package sa.zad.pagedrecyclerlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;

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
    public abstract void dataCallBack(Key next, @NonNull CallAction<List<M>> callBack);

    @Override
    public abstract Key getKey(@NonNull M activityType);
}
