package sa.zad.pagedrecyclerlist;

import androidx.paging.PageKeyedDataSource;
import androidx.annotation.NonNull;

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
      callback.onResult(p.data, p.previousPage, p.nextPage);
    });
  }

  @Override
  public void loadAfter(@NonNull LoadParams<Key> params,
      @NonNull LoadCallback<Key, Item> callback) {
      pageKeyedListDataSourceListener.getData(params.key, p -> {
        callback.onResult(p.data, p.nextPage);
      });
  }

  @Override
  public void loadBefore(@NonNull LoadParams<Key> params,
      @NonNull LoadCallback<Key, Item> callback) {

  }

  public interface PageKeyedListDataSourceListener<Key, Item> {
    void getData(Key next, @NonNull CallAction<KeyDataCallback<Item, Key>> callBack);
  }

  public static class KeyDataCallback<Item, Key>{

    private final List<Item> data;
    private final Key previousPage;
    private final Key nextPage;

    public KeyDataCallback(List<Item> data, Key previousPage, Key nextPage) {
      this.data = data;
      this.previousPage = previousPage;
      this.nextPage = nextPage;
    }
  }
}
