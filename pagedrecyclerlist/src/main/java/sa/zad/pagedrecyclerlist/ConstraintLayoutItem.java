package sa.zad.pagedrecyclerlist;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

abstract public class ConstraintLayoutItem<M> extends ConstraintLayout implements AppListAdapter.AppAdapterItem<M> {

  private M item;
  private int itemIndex;
  public boolean isSelectMode = false;

  protected ConstraintLayoutList.ItemOptionSelected<M, ConstraintLayoutItem<M>> itemOptionSelectedListener = (selected, item, view, payload) -> {
  };

  public ConstraintLayoutItem(Context context) {
    this(context, null);
  }

  public ConstraintLayoutItem(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ConstraintLayoutItem(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  protected void callOption(int option) {
    callOption(option, null);
  }

  protected void callOption(int option, @Nullable Object payload) {
    itemOptionSelectedListener.selected(option, item, this, payload);
  }

  public void setItemIndex(int index) {
    this.itemIndex = index;
  }

  /**
   * It's highly discouraged to use {@link #getItemIndex()}
   * as it loses it's integrity in case of list modification
   *
   * @return index
   */
  @Deprecated
  public int getItemIndex() {
    return itemIndex;
  }

  @Override
  @CallSuper
  public void bind(@NonNull M i) {
    this.item = i;
  }

  @Override
  public void lastItem(boolean hide) {

  }

  @NonNull
  @Override
  public final M getItem() {
    return item;
  }

  @Override
  public void select(boolean select) {

  }

  @NonNull
  @Override
  public View selectionView() {
    return this;
  }


  public <V extends ConstraintLayoutItem<M>> void setItemOptionSelectedListener(ConstraintLayoutList.ItemOptionSelected<M, V> itemOptionSelectedListener) {
    this.itemOptionSelectedListener = (ConstraintLayoutList.ItemOptionSelected<M, ConstraintLayoutItem<M>>) itemOptionSelectedListener;
  }
}
