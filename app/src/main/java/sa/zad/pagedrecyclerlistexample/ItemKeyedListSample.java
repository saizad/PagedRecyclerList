package sa.zad.pagedrecyclerlistexample;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import sa.zad.pagedrecyclerlist.CallAction;
import sa.zad.pagedrecyclerlist.ItemKeyedList;
import sa.zad.pagedrecyclerlistexample.models.ActivityType;

import java.util.List;

public class ItemKeyedListSample extends ItemKeyedList<ActivityType, ActivitiesView, String> {

  private ApiService apiService;

  public ItemKeyedListSample(Context context) {
    super(context);
  }

  public ItemKeyedListSample(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public ItemKeyedListSample(Context context, @Nullable AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
  }

  public void init(@NonNull LifecycleOwner lifecycleOwner, @NonNull ApiService apiService) {
    this.apiService = apiService;
    init(lifecycleOwner);
    getListAdapter().setItemKeyedDataSource(lifecycleOwner,this);
  }

  @Override
  public void dataCallBack(String next, @NonNull CallAction<List<ActivityType>> callBack) {
    apiService.findActivities("a",next, null)
            .map(listDataModel -> listDataModel.data)
            .subscribe(callBack::call);
  }

  @Override
  public String getKey(@NonNull ActivityType activityType) {
    return activityType.activity;
  }

  @Override
  public boolean compare(ActivityType item1, ActivityType item2) {
    return false;
  }

  @Override
  public void selected(ActivitiesView view, ActivityType item, List<ActivityType> selectedList) {

  }

  @Override
  public void unSelected(ActivitiesView view, ActivityType item, List<ActivityType> selectedList) {

  }

  @Override
  public ActivitiesView getSelectorItem(Context context, int viewType) {
    return new ActivitiesView(context, null, 0);
  }
}
