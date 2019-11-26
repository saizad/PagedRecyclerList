package sa.zad.pagedrecyclerlist;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

public abstract class IntegerPageKeyList<M, I extends ConstraintLayoutItem<M>> extends ConstraintLayoutList<M, I, Integer> {

    public IntegerPageKeyList(Context context) {
        super(context);
    }

    public IntegerPageKeyList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public IntegerPageKeyList(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

}
