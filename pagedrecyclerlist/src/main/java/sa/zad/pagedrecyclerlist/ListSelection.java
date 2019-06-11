package sa.zad.pagedrecyclerlist;

import android.content.Context;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sa-ZAD on 2018-01-07.
 */

public abstract class ListSelection<T, I extends View & AppListAdapter.AppAdapterItem<T>>
    extends AppList<T, I> {

  private boolean mSingleSelection;

  private SelectedCountListener selectedCountListener = new SelectedCountListener() {
    @Override
    public void count(int count) {

    }
  };

  private List<T> mSelected = new ArrayList<>();
  private ListSelectionListener<T> mListSelectorListener =
      new ListSelectionListener<T>() {
        @Override
        public void selected(T item, List<T> selectedList) {

        }

        @Override
        public void unSelected(T item, List<T> selectedList) {

        }
      };

  public ListSelection(Context context) {
    this(context, null);
  }

  public ListSelection(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ListSelection(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  final public void setSingleSelection(boolean singleSelection) {
    mSingleSelection = singleSelection;
  }

  final public void setSelectionListener(ListSelectionListener<T> listSelectorListener) {
    mListSelectorListener = listSelectorListener;
  }

  final public void setSelectedCountListener(SelectedCountListener selectedCountListener){
    this.selectedCountListener = selectedCountListener;
  }

  @CallSuper
  public void showSelectedItem(T item){
    showSelectedItems(Collections.singletonList(item));
  }

  @CallSuper
  public void showSelectedItems(List<T> items){
    addSelectedItems(items);
    getListAdapter().setItems(items);
  }

  public final void addSelectedItem(T item){
    if(compareItems(item) == -1){
      mSelected.add(item);
      selectedCountListener.count(mSelected.size());
    }
  }

  public final void addSelectedItems(List<T> items){
    for(T item: items){
      if(compareItems(item) == -1){
        mSelected.add(item);
      }
    }
    selectedCountListener.count(mSelected.size());
  }
  final public void removeSelectedItems(List<T> items) {
    for (T item : items) {
      removeSelectedItem(item);
    }
  }

  final public void removeSelectedItem(T item){
    final int foundItem = compareItems(item);
    if(foundItem != -1){
      itemUnSelected(item, foundItem);
    }
  }

  final public void removeAllSelected(){
    removeSelectedItems(getSelected());
    getAdapter().notifyDataSetChanged();
  }

  @Override
  public final I getItemView(Context context, int viewType) {
    final I selectorItem = getSelectorItem(context, viewType);
    selectorItem.setOnClickListener(__ -> selection(selectorItem));
    return selectorItem;
  }

  @CallSuper
  @Override
  public boolean onBindItemView(I view, T item, int itemIndex) {
    view.bind(item);
    final int foundItemIndex = compareItems(view.getItem());
    view.select(foundItemIndex != -1);
    view.hideDivider(getListAdapter().getItemCount() == 1);
//    view.hideDivider(getListAdapter().getItemCount()-1 == itemIndex);

    return true;
  }

  final public List<T> getSelected() {
    return mSelected;
  }

  private int compareItems(T compareWith) {
    for (int i = 0; i < mSelected.size(); i++) {
      if (compare(compareWith, mSelected.get(i))) {
        return i;
      }
    }
    return -1;
  }

  final public void selection(I itemView) {
    if(!isSelected(itemView.getItem()) && mSingleSelection){
      removeAllSelected();
    }
    final T item = itemView.getItem();
    final int foundItemIndex = compareItems(item);
    if (foundItemIndex != -1) {
      itemUnSelected(item, foundItemIndex);
      unSelected(itemView, item, mSelected);
      itemView.select(false);
    } else {
      itemSelected(item);
      selected(itemView, item, mSelected);
      itemView.select(true);
    }
    selectedCountListener.count(mSelected.size());
  }

  public void showOnlySelected(){
    getListAdapter().setItems(getSelected());
  }

  final public boolean isSingleSelection() {
    return mSingleSelection;
  }

  final public boolean isSelected(T item){
    final int foundItemIndex = compareItems(item);
    return foundItemIndex != -1;
  }

  private void itemSelected(T item) {
    mSelected.add(item);
    mListSelectorListener.selected(item, mSelected);
  }

  private void itemUnSelected(T item, int foundItemIndex) {
    mSelected.remove(foundItemIndex);
    mListSelectorListener.unSelected(item, mSelected);
  }

  public abstract boolean compare(T item1, T item2);
  public abstract void selected(I view, T item, List<T> selectedList);
  public abstract void unSelected(I view, T item, List<T> selectedList);
  public abstract I getSelectorItem(Context context, int viewType);

  public interface ListSelectionListener<T> {
    void selected(T item, List<T> selectedList);
    void unSelected(T item, List<T> selectedList);
  }

  public interface SelectedCountListener{
    void count(int count);
  }
}
