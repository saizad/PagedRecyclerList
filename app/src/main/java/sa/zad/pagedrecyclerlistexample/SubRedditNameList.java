package sa.zad.pagedrecyclerlistexample;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import sa.zad.pagedrecyclerlist.ConstraintLayoutList;
import sa.zad.pagedrecyclerlistexample.models.Items;

public class SubRedditNameList extends ConstraintLayoutList<Items, SubRedditNameItem, String> {

  public static final int FAV_OPTION = 100;
  public static final int DELETE_ITEM_OPTION = 200;

  public SubRedditNameList(Context context) {
    super(context);
  }

  public SubRedditNameList(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public SubRedditNameList(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  @Override
  public SubRedditNameItem getSelectorItem(Context context, int viewType) {
    return (SubRedditNameItem) Utils.inflate(context, R.layout.list_item_view, this, false);
  }

  @Override
  public boolean compare(@NonNull Items item1, @NonNull Items item2) {
    return item1.data.id.equalsIgnoreCase(item2.data.id);
  }
}
