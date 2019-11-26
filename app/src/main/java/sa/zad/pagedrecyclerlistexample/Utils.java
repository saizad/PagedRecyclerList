package sa.zad.pagedrecyclerlistexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

public class Utils {

  public static View inflate(Context context, @LayoutRes int layoutRes) {
    return inflate(context, layoutRes, null);
  }

  public static View inflate(Context context, @LayoutRes int layoutRes,
                             @Nullable ViewGroup viewGroup) {
    return inflate(context, layoutRes, viewGroup, false);
  }

  public static View inflate(Context context, @LayoutRes int layoutRes,
                             @Nullable ViewGroup viewGroup, boolean attachToRoot) {
    LayoutInflater mInflater =
            (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    return mInflater.inflate(layoutRes, viewGroup, attachToRoot);
  }

  public static void switchVisibility(View view, boolean visible) {
    if (visible) {
      view.setVisibility(View.VISIBLE);
    } else {
      view.setVisibility(View.GONE);
    }
  }
}
