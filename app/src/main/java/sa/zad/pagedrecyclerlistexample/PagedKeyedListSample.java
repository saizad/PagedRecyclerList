package sa.zad.pagedrecyclerlistexample;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;

import java.util.List;

import sa.zad.pagedrecyclerlist.CallAction;
import sa.zad.pagedrecyclerlist.PageKeyedListDataSource;
import sa.zad.pagedrecyclerlist.PagedKeyedList;
import sa.zad.pagedrecyclerlistexample.models.Items;

public class PagedKeyedListSample extends PagedKeyedList<Items, SubRedditNameView, String> {

  ApiService apiService;

  public PagedKeyedListSample(Context context) {
    super(context);
  }

  public PagedKeyedListSample(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public PagedKeyedListSample(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  public void init(@NonNull LifecycleOwner lifecycleOwner, @NonNull ApiService apiService) {
    this.apiService = apiService;
    init(lifecycleOwner);
    getListAdapter().setPageKeyedDataSource(lifecycleOwner,this);
  }

  @Override
  public void getData(String next, @NonNull CallAction<PageKeyedListDataSource.KeyDataCallback<Items, String>> callBack) {
      apiService.list("https://www.reddit.com/r/all.json?limit=20&after="+next)
              .subscribe(redditPagedModel -> {
                callBack.call(new PageKeyedListDataSource.KeyDataCallback<>(redditPagedModel.data.children, redditPagedModel.data.before, redditPagedModel.data.after));
              });
  }

  @Override
  public boolean compare(Items item1, Items item2) {
    return false;
  }

  @Override
  public void selected(SubRedditNameView view, Items item, List<Items> selectedList) {

  }

  @Override
  public void unSelected(SubRedditNameView view, Items item, List<Items> selectedList) {

  }

  @Override
  public SubRedditNameView getSelectorItem(Context context, int viewType) {
    return new SubRedditNameView(context, null, 0);
  }
}
