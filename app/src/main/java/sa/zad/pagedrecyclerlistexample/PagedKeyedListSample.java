package sa.zad.pagedrecyclerlistexample;

import androidx.lifecycle.LifecycleOwner;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import sa.zad.pagedrecyclerlist.CallAction;
import sa.zad.pagedrecyclerlist.PageKeyedListDataSource;
import sa.zad.pagedrecyclerlist.PagedKeyedList;
import sa.zad.pagedrecyclerlistexample.models.Items;

import java.util.List;

public class PagedKeyedListSample extends PagedKeyedList<Items, ItemsTitleView, String> {

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
  public void selected(ItemsTitleView view, Items item, List<Items> selectedList) {

  }

  @Override
  public void unSelected(ItemsTitleView view, Items item, List<Items> selectedList) {

  }

  @Override
  public ItemsTitleView getSelectorItem(Context context, int viewType) {
    return new ItemsTitleView(context, null, 0);
  }
}
