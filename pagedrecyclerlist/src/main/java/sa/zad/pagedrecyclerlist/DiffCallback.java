package sa.zad.pagedrecyclerlist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

class DiffCallback<Item> extends DiffUtil.Callback {

  private final List<Item> oldList;
  private final List<Item> newList;


  DiffCallback(@NonNull List<Item> newList, @NonNull List<Item> oldList) {
    this.oldList = oldList;
    this.newList = newList;
  }

  @Override
  public int getOldListSize() {
    return oldList.size();
  }

  @Override
  public int getNewListSize() {
    return newList.size();
  }

  @Override
  public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
    final Item oldItem = oldList.get(oldItemPosition);
    final Item newItem = newList.get(newItemPosition);
    if (oldItem instanceof AppListAdapter.CompareItem) {
      return ((AppListAdapter.CompareItem) oldItem).areItemsTheSame(oldItem, newItem);
    }
    return false;
  }

  @Override
  public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
    return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
  }

  @Nullable
  @Override
  public Object getChangePayload(int oldItemPosition, int newItemPosition) {
    final Item oldItem = oldList.get(oldItemPosition);
    final Item newItem = newList.get(newItemPosition);
    if (oldItem instanceof AppListAdapter.CompareItem) {
      return ((AppListAdapter.CompareItem) oldItem).getChangePayload(oldItem, newItem);
    }
    return super.getChangePayload(oldItemPosition, newItemPosition);
  }
}