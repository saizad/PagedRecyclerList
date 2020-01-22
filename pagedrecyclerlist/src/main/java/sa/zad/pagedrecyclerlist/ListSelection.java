package sa.zad.pagedrecyclerlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sa-ZAD on 2018-01-07.
 */

public abstract class ListSelection<M, I extends View & AppListAdapter.AppAdapterItem<M>>
        extends AppList<M, I> {

  private int selectionCount = 0;

  private SelectedCountListener selectedCountListener = count -> {

  };

  private ItemOnBindListener<M, I> itemOnBindListener = (item, itemView, index) -> {
  };

  private ItemOnClickListener<M, I> itemOnClickListener = (item, itemView, index) -> {
  };


  private ItemDoubleTapListener<M, I> itemDoubleTapListener = (item, itemView, itemIndex) -> false;

  private List<M> mSelected = new ArrayList<>();
  private ListSelectionListener<M> mListSelectorListener =
          new ListSelectionListener<M>() {
            @Override
            public void selected(@NonNull M item, @NonNull List<M> selectedList) {

            }

            @Override
            public void unSelected(@NonNull M item, @NonNull List<M> selectedList) {

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

  public int getCount() {
    return selectionCount;
  }

  /**
   * call this method will reset selections
   */
  final public void setSelectionCount(int selectionCount) {
    this.selectionCount = selectionCount;
    removeAllSelected();
  }

  /**
   * call this method will reset selections
   */
  final public void setMaxSelection() {
    setSelectionCount(-1);
  }

  /**
   * call this method will reset selections
   */
  final public void setSingleSelection() {
    setSelectionCount(1);
  }

  final public void setSelectionListener(ListSelectionListener<M> listSelectorListener) {
    mListSelectorListener = listSelectorListener;
  }

  final public void setSelectedCountListener(SelectedCountListener selectedCountListener) {
    this.selectedCountListener = selectedCountListener;
  }

  public final void addSelectedItem(M item) {
    if (compareItems(item) == -1) {
      mSelected.add(item);
      selectedCountListener.count(mSelected.size());
    }
  }

  public final void addSelectedItems(List<M> items) {
    for (M item : items) {
      addSelectedItem(item);
    }
    getAdapter().notifyDataSetChanged();
  }

  final public void removeSelectedItems(List<M> items) {
    for (M item : items) {
      removeSelectedItem(item);
    }
  }

  final public void removeSelectedItem(M item) {
    final int foundItemIndex = compareItems(item);
    if (foundItemIndex != -1) {
      mSelected.remove(foundItemIndex);
      selectedCountListener.count(mSelected.size());
    }
  }

  final public void removeAllSelected() {
    mSelected.clear();
    selectedCountListener.count(mSelected.size());
    getAdapter().notifyDataSetChanged();
  }

  @Override
  public final I getItemView(Context context, int viewType) {
    return getSelectorItem(context, viewType);
  }

  private void setDoubleTap(I view, int itemIndex){
    view.setOnTouchListener(new OnTouchListener() {
      private GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener(){
        @Override
        public boolean onDoubleTap(MotionEvent e) {
          return itemDoubleTapListener.itemCall(view.getItem(), view, itemIndex);
        }
      });

      @Override
      public boolean onTouch(View v, MotionEvent event) {
        final boolean b = gestureDetector.onTouchEvent(event);
        return true;
      }
    });
  }

  @CallSuper
  @Override
  public boolean onBindItemView(I view, M item, int itemIndex) {
    final View selectionView = view.selectionView();

    if (selectionView == view) {
      selectionView.setOnClickListener(__ -> {
        selection(view);
        itemOnClickListener.itemCall(view.getItem(), view, itemIndex);
      });
    } else {
      selectionView.setOnClickListener(__ -> {
        selection(view);
      });
      view.setOnClickListener(__ -> {
        itemOnClickListener.itemCall(view.getItem(), view, itemIndex);
      });
      setDoubleTap(view, itemIndex);
    }

    itemOnBindListener.itemCall(item, view, itemIndex);
    view.bind(item);
    final int foundItemIndex = compareItems(view.getItem());
    view.select(foundItemIndex != -1);
    view.lastItem(getListAdapter().getItemCount() - 1 == itemIndex);
    return true;
  }

  final public List<M> getSelected() {
    return mSelected;
  }

  private int compareItems(M compareWith) {
    for (int i = 0; i < mSelected.size(); i++) {
      if (compare(compareWith, mSelected.get(i))) {
        return i;
      }
    }
    return -1;
  }

  final public void selection(I itemView) {
    if (selectionCount == 0) {
      return;
    }

    int count = selectionCount == -1 ? getListAdapter().getItemCount() : selectionCount;

    final M item = itemView.getItem();
    final int foundItemIndex = compareItems(item);
    if (foundItemIndex != -1) {
      removeSelection(foundItemIndex);
      unSelected(itemView, item, mSelected);
    } else {
      if (mSelected.size() >= count) {
        //remove first selected item
        removeSelection(0);
      }
      addSelection(item);
      selected(itemView, item, mSelected);
    }
    getListAdapter().notifyDataSetChanged();
    selectedCountListener.count(mSelected.size());
  }

  final public boolean isSelectionList() {
    return selectionCount != 0;
  }

  final public boolean isSingleSelection() {
    return selectionCount == 1;
  }

  final public boolean isSelected(M item) {
    final int foundItemIndex = compareItems(item);
    return foundItemIndex != -1;
  }

  private void addSelection(M item) {
    mSelected.add(item);
    mListSelectorListener.selected(item, mSelected);
  }

  private void removeSelection(int foundItemIndex) {
    final M remove = mSelected.remove(foundItemIndex);
    mListSelectorListener.unSelected(remove, mSelected);
  }

  public void setItemOnBindListener(ItemOnBindListener<M, I> itemOnBindListener) {
    this.itemOnBindListener = itemOnBindListener;
  }

  public void setItemOnClickListener(ItemOnClickListener<M, I> itemOnClickListener) {
    this.itemOnClickListener = itemOnClickListener;
  }

  public void setItemDoubleTapListener(ItemDoubleTapListener<M, I> itemDoubleTapListener) {
    this.itemDoubleTapListener = itemDoubleTapListener;
  }

  public abstract boolean compare(M item1, M item2);

  public void selected(I view, M item, List<M> selectedList) {
  }

  public void unSelected(I view, M item, List<M> selectedList) {
  }

  public abstract I getSelectorItem(Context context, int viewType);

  public interface ListSelectionListener<T> {
    void selected(@NonNull T item, @NonNull List<T> selectedList);

    void unSelected(@NonNull T item, @NonNull List<T> selectedList);
  }

  public interface SelectedCountListener {
    void count(int count);
  }
}
