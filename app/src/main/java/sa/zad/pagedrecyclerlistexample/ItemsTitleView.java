package sa.zad.pagedrecyclerlistexample;

import android.content.Context;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import sa.zad.pagedrecyclerlist.AppListAdapter;
import sa.zad.pagedrecyclerlistexample.models.Items;

public class ItemsTitleView extends androidx.appcompat.widget.AppCompatTextView implements AppListAdapter.AppAdapterItem<Items> {

  public ItemsTitleView(Context context) {
    this (context, null);
  }

  public ItemsTitleView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public ItemsTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public void bind(Items items) {
    setText(items.data.subreddit);
  }

  @Override
  public void hideDivider(boolean hide) {

  }

  @Override
  public Items getItem() {
    return null;
  }

  @Override
  public void select(boolean select) {

  }
}
