package sa.zad.pagedrecyclerlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;

import java.util.List;

public abstract class PagedKeyedList<M, I extends View & AppListAdapter.AppAdapterItem<M>, Key> extends ListSelection<M, I> implements
        PageKeyedListDataSource.PageKeyedListDataSourceListener<Key, M> {

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

    public void createList() {
        getListAdapter().setPageKeyedDataSource(lifecycleOwner, this);
    }

    public void init(@NonNull LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
    }

    @Override
    public abstract void getData(Key next, @NonNull CallAction<PageKeyedListDataSource.KeyDataCallback<M, Key>> callBack);

    abstract protected List<M> data(PageKeyedListDataSource.KeyDataCallback<M, Key> keyDataCallback);

    abstract protected Key previousPage(PageKeyedListDataSource.KeyDataCallback<M, Key> keyDataCallback);

    abstract protected Key nextPage(PageKeyedListDataSource.KeyDataCallback<M, Key> keyDataCallback);
}
