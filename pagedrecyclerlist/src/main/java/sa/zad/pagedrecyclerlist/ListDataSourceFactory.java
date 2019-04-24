package sa.zad.pagedrecyclerlist;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.annotation.NonNull;

public class ListDataSourceFactory<Key,Item> extends DataSource.Factory<Key, Item> {

  private MutableLiveData<DataSource<Key, Item>> dataSourceMutableLiveData =
      new MutableLiveData<>();

  private DataSource<Key, Item> listDataSource;

  public ListDataSourceFactory(
      ItemKeyedListDataSource.ItemKeyedListDataSourceListener<Key, Item> itemItemKeyedListDataSourceListener) {
    listDataSource = new ItemKeyedListDataSource<>(itemItemKeyedListDataSourceListener);
  }

  public ListDataSourceFactory(
      PageKeyedListDataSource.PageKeyedListDataSourceListener<Key, Item> pageKeyedListDataSourceListener) {
    listDataSource = new PageKeyedListDataSource<>(pageKeyedListDataSourceListener);
  }

  @Override
  public DataSource<Key, Item> create() {
    dataSourceMutableLiveData.postValue(listDataSource);
    return listDataSource;
  }

  @NonNull
  public MutableLiveData<DataSource<Key, Item>> getDataSourceMutableLiveData() {
    return dataSourceMutableLiveData;
  }
}
