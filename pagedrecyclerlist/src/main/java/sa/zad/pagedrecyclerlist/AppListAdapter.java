package sa.zad.pagedrecyclerlist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sa-ZAD on 2017-11-27.
 */

public class AppListAdapter<Item, LV extends View & AppListAdapter.AppAdapterItem<Item>>
        extends PagedListAdapter<Item, AppRecyclerViewHolder<Item, LV>> {

  private List<Item> mItems = new ArrayList<>();
  private RecyclerViewAdapterListener<Item, LV> mRecyclerViewAdapterListener;

  public AppListAdapter(RecyclerViewAdapterListener<Item, LV> recyclerViewAdapterListener) {
    super(new DiffUtil.ItemCallback<Item>() {
      @Override
      public boolean areItemsTheSame(Item oldItem, Item newItem) {
        return false;
      }

      @Override
      public boolean areContentsTheSame(Item oldItem, Item newItem) {
        return false;
      }
    });
    this.mRecyclerViewAdapterListener = recyclerViewAdapterListener;
  }

  public <Key> void setPageKeyedDataSource(@NonNull LifecycleOwner lifecycleOwner,
                                           @NonNull final PageKeyedListDataSource.PageKeyedListDataSourceListener<Key, Item> listener) {
    setPageKeyedDataSource(lifecycleOwner, listener, 100, 100);
  }

  public <Key> void setPageKeyedDataSource(@NonNull LifecycleOwner lifecycleOwner,
                                           @NonNull final PageKeyedListDataSource.PageKeyedListDataSourceListener<Key, Item> listener, int initialItems, int pageSize) {
    ListDataSourceFactory<Key, Item> dataSourceFactory = new ListDataSourceFactory<>(listener);
    config(lifecycleOwner, dataSourceFactory, initialItems, pageSize);
  }

  public <Key> void setItemKeyedDataSource(@NonNull LifecycleOwner lifecycleOwner,
                                           @NonNull final ItemKeyedListDataSource.ItemKeyedListDataSourceListener<Key, Item> listener) {
    setItemKeyedDataSource(lifecycleOwner, listener, 100, 100);
  }

  public <Key> void setItemKeyedDataSource(@NonNull LifecycleOwner lifecycleOwner,
                                           @NonNull final ItemKeyedListDataSource.ItemKeyedListDataSourceListener<Key, Item> listener, int initialItems, int pageSize) {
    ListDataSourceFactory<Key, Item> dataSourceFactory = new ListDataSourceFactory<>(listener);
    config(lifecycleOwner, dataSourceFactory, initialItems, pageSize);
  }

  private <Key> void config(@NonNull LifecycleOwner lifecycleOwner,
                            DataSource.Factory<Key, Item> dataSource, int initialItems, int pageSize) {
    setItems(new ArrayList<>());
    PagedList.Config config = new PagedList.Config.Builder().setPageSize(pageSize)
            .setInitialLoadSizeHint(initialItems)
            .setEnablePlaceholders(false)
            .build();
    LiveData<PagedList<Item>> liveData = new LivePagedListBuilder<>(dataSource, config).build();
    liveData.observe(lifecycleOwner, this::submitList);
  }


  @NonNull
  @Override
  public AppRecyclerViewHolder<Item, LV> onCreateViewHolder(@NonNull ViewGroup parent,
                                                            int viewType) {
    return new AppRecyclerViewHolder<>(
            mRecyclerViewAdapterListener.getItemView(parent.getContext(), viewType));
  }

  @Override
  public void onBindViewHolder(@NonNull AppRecyclerViewHolder<Item, LV> holder, int position) {
    if (!mRecyclerViewAdapterListener.onBindItemView(holder.getItemView(), getItem(position),
            position)) {
      holder.appAdapterItem.bind(getItem(position));
    }
  }

  @Override
  public int getItemViewType(int position) {
    return mRecyclerViewAdapterListener.preGetItemView(position, getItem(position));
  }

  @Override
  public int getItemCount() {
    return getItems().size();
  }

  public boolean isEmpty() {
    return getItemCount() == 0;
  }

  @Nullable
  @Override
  protected Item getItem(int position) {
    if (isNotPagedList()) {
      return mItems.get(position);
    }
    return super.getItem(position);
  }

  public void clear() {
    if (isNotPagedList()) {
      getItems().clear();
      setItems(getItems());
    }
  }

  @Override
  public void submitList(PagedList<Item> pagedList) {
    mItems = pagedList;
    super.submitList(pagedList);
  }

  public void appendItems(@NonNull List<Item> items) {
    if (isNotPagedList()) {
      int prevSize = getItems().size();
      getItems().addAll(items);
      notifyItemRangeInserted(prevSize, items.size());
    }
  }

  public void appendItem(@NonNull Item item) {
    if (isNotPagedList()) {
      getItems().add(item);
      notifyItemInserted(getItems().size() - 1);
    }
  }

  public void removeItem(int index) {
    if (index >= getItemCount() && isNotPagedList()) {
      getItems().remove(index);
      notifyItemRemoved(index);
    }
  }

  public boolean isNotPagedList() {
    return getCurrentList() == null || getCurrentList().isEmpty();
  }

  public List<Item> getItems() {
    if (isNotPagedList()) {
      return mItems;
    }
    return super.getCurrentList();
  }

  public void setItems(@NonNull List<Item> items) {
    if (isNotPagedList()) {
      DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallback<>(items, getItems()));
      diffResult.dispatchUpdatesTo(this);
      getItems().clear();
      getItems().addAll(items);
    }
  }

  public interface RecyclerViewAdapterListener<Item, LV extends View & AppListAdapter.AppAdapterItem<Item>> {
    LV getItemView(@NonNull Context context, int viewType);

    boolean onBindItemView(@NonNull LV view, @NonNull Item item, int itemIndex);

    /**
     * Return the view type of the item at pagePosition for the purposes of view recycling.
     * <p>
     * The default implementation of this method returns 0, making the assumption of a single view
     * type for the adapter. Unlike ListView adapters, types need not be contiguous. Consider using
     * id resources to uniquely identify item view types.
     *
     * @param itemIndex pagePosition of item
     * @return if your are displaying different view types use this or just 0
     */
    int preGetItemView(int itemIndex, @NonNull Item item);
  }

  public interface AppAdapterItem<Item> {
    void bind(@NonNull Item item);

    void lastItem(boolean hide);

    @NonNull
    Item getItem();

    void select(boolean select);

    @NonNull
    View selectionView();
  }

  public interface CompareItem<Item> {

    boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem);

    @Nullable
    Object getChangePayload(@NonNull Item oldItem, @NonNull Item newItem);

     boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem);
  }
}
