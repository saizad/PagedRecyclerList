package sa.zad.pagedrecyclerlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;

import java.util.List;


abstract public class ConstraintLayoutList<M, I extends ConstraintLayoutItem<M>, K> extends PagedKeyedList<M, I, K> {

  public final static int CLICK_LISTENER = -1;

  protected CallbackPageKeyedList<M, K> callbackPageKeyedList;

  private ItemOptionSelected<M, I> itemOptionSelectedListener = (selected, item, view, payload) -> {
  };

  public ConstraintLayoutList(Context context) {
    super(context);
  }

  public ConstraintLayoutList(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public ConstraintLayoutList(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  public boolean onBindItemView(@NonNull I view, @NonNull M item, int itemIndex) {
    //Todo Index will lost integrity if notifyItemRemoved or notifyItemInserted is called
    view.setItemIndex(itemIndex);
    view.setItemOptionSelectedListener(itemOptionSelectedListener);
    view.isSelectMode = isSelectionList();
    return super.onBindItemView(view, item, itemIndex);
  }

  protected List<M> finalizePageData(List<M> list) {
    return list;
  }

  @Override
  public boolean compare(M item1, M item2) {
    return false;
  }

  @Override
  public void getData(K next, @NonNull CallAction<PageKeyedListDataSource.KeyDataCallback<M, K>> callBack) {
    callbackPageKeyedList.call(next, keyListPageDataModel -> {
      callBack.call(new PageKeyedListDataSource.KeyDataCallback<>(finalizePageData(data(keyListPageDataModel)), previousPage(keyListPageDataModel), nextPage(keyListPageDataModel)));
    });
  }

  @Override
  protected K nextPage(PageKeyedListDataSource.KeyDataCallback<M, K> keyDataCallback) {
    return keyDataCallback.getNextPageKey();
  }

  @Override
  protected K previousPage(PageKeyedListDataSource.KeyDataCallback<M, K> keyDataCallback) {
    return keyDataCallback.getPreviousPageKey();
  }

  @Override
  protected List<M> data(PageKeyedListDataSource.KeyDataCallback<M, K> keyDataCallback) {
    return keyDataCallback.getData();
  }

  public void setItemOptionSelectedListener(ItemOptionSelected<M, I> itemOptionSelectedListener) {
    this.itemOptionSelectedListener = itemOptionSelectedListener;
  }

  public void init(@NonNull LifecycleOwner lifecycleOwner,
                   @NonNull CallbackPageKeyedList<M, K> callBackPageKeyedList) {
    init(lifecycleOwner, callBackPageKeyedList, 100);
  }

  public void init(@NonNull LifecycleOwner lifecycleOwner,
                   @NonNull CallbackPageKeyedList<M, K> callBackPageKeyedList, int initCount) {
    this.callbackPageKeyedList = callBackPageKeyedList;
    init(lifecycleOwner);
    getListAdapter().setPageKeyedDataSource(lifecycleOwner, this, initCount, initCount);
  }

  public interface CallbackPageKeyedList<M, Key> {
    void call(Key next, @NonNull CallBack<PageKeyedListDataSource.KeyDataCallback<M, Key>> callback);
  }

  public interface ItemOptionSelected<M, I extends View & AppListAdapter.AppAdapterItem<M>> {
    void selected(int selected, M item, I view, Object payload);
  }

  public interface CallBack<T> {
    void call(T t);
  }
}
