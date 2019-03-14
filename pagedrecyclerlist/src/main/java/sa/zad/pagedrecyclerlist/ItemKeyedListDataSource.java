package sa.zad.pagedrecyclerlist;

import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;

import java.util.List;

public class ItemKeyedListDataSource<Key,Item> extends ItemKeyedDataSource<Key, Item> {

  private @NonNull
  final ItemKeyedListDataSourceListener<Key, Item> listener;

  public ItemKeyedListDataSource(@NonNull ItemKeyedListDataSourceListener<Key, Item> listener) {
    this.listener = listener;
  }

  @Override
  public void loadInitial(@NonNull LoadInitialParams<Key> params,
      @NonNull LoadInitialCallback<Item> callback) {
      listener.dataCallBack(params.requestedInitialKey, callback::onResult);
  }

  @Override
  public void loadAfter(@NonNull LoadParams<Key> params, @NonNull LoadCallback<Item> callback) {
    listener.dataCallBack(params.key, callback::onResult);
  }

  @Override
  public void loadBefore(@NonNull LoadParams<Key> params,
      @NonNull LoadCallback<Item> callback) {
//    listener.dataCallBack(params.key, callback::onResult);
  }

  @NonNull
  @Override
  public Key getKey(@NonNull Item item) {
    return listener.getKey(item);
  }

  public interface ItemKeyedListDataSourceListener<Key, Item> {
    void dataCallBack(Key next, @NonNull CallAction<List<Item>> callBack);
    Key getKey(@NonNull Item item);
  }
}
