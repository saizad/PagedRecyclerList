package sa.zad.pagedrecyclerlist;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import java.util.List;

public class PageKeyedListDataSource<Key, Item> extends PageKeyedDataSource<Key, Item> {

  private final PageKeyedListDataSourceListener<Key, Item> pageKeyedListDataSourceListener;

  PageKeyedListDataSource(PageKeyedListDataSourceListener<Key, Item> pageKeyedListDataSourceListener) {
    this.pageKeyedListDataSourceListener = pageKeyedListDataSourceListener;
  }

  @Override
  public void loadInitial(@NonNull LoadInitialParams<Key> params,
      @NonNull LoadInitialCallback<Key, Item> callback) {
    pageKeyedListDataSourceListener.getData(null, p -> {
      callback.onResult(p.data, p.previous_page, p.next_page);
    });
  }

  @Override
  public void loadAfter(@NonNull LoadParams<Key> params,
      @NonNull LoadCallback<Key, Item> callback) {
      pageKeyedListDataSourceListener.getData(params.key, p -> {
        callback.onResult(p.data, p.next_page);
      });
  }

  @Override
  public void loadBefore(@NonNull LoadParams<Key> params,
      @NonNull LoadCallback<Key, Item> callback) {

  }

  public interface PageKeyedListDataSourceListener<Key, Item> {
    void getData(Key next, @NonNull Action1<PageDataModel<Key, List<Item>>> callBack);
  }
}
