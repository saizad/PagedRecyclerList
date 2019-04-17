package sa.zad.pagedrecyclerlistexample;

import android.content.Context;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
}
