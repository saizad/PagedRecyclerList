package sa.zad.pagedrecyclerlist;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Sa-ZAD on 2017-12-19.
 */

public class AppList<T, I extends View & AppListAdapter.AppAdapterItem<T>> extends AppRecyclerViewList<AppListAdapter<T, I>>
        implements AppListAdapter.RecyclerViewAdapterListener<T, I> {

    private AppListAdapter.RecyclerViewAdapterListener<T, I> mAdapterListener = new AppListAdapter.RecyclerViewAdapterListener<T, I>() {
        @Override
        public I getItemView(Context context, int viewType) {
            return null;
        }

        @Override
        public boolean onBindItemView(I view, T item, int itemIndex) {
            return false;
        }

        @Override
        public int preGetItemView(int itemIndex, T item) {
            return 0;
        }
    };

    public AppList(Context context) {
        this(context, null);
    }

    public AppList(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppList(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setListAdapter(new AppListAdapter<>(this));
    }

    public void setAdapterListener(AppListAdapter.RecyclerViewAdapterListener<T, I> adapterListener) {
        this.mAdapterListener = adapterListener;
    }

    @Override
    public I getItemView(Context context, int viewType) {
        return mAdapterListener.getItemView(context, viewType);
    }

    @Override
    public boolean onBindItemView(I view, T item, int itemIndex) {
        return mAdapterListener.onBindItemView(view, item, itemIndex);
    }

    @Override
    public int preGetItemView(int itemIndex, T item) {
        return mAdapterListener.preGetItemView(itemIndex, item);
    }
}
