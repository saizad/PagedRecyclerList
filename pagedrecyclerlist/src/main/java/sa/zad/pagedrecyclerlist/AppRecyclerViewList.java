package sa.zad.pagedrecyclerlist;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Sa-ZAD on 2017-11-27.
 */

public class AppRecyclerViewList<T> extends RecyclerView {

    private T mListAdapter;

    public AppRecyclerViewList(Context context) {
        this(context, null);
    }

    public AppRecyclerViewList(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppRecyclerViewList(final Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if(getLayoutManager() == null) {
            setLayoutManager(new LinearLayoutManager(context));
        }
    }

    public T getListAdapter() {
        return mListAdapter;
    }

    public void setListAdapter(T adapter) {
        super.setAdapter((Adapter) adapter);
        mListAdapter = adapter;
    }
}
