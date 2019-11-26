package sa.zad.pagedrecyclerlistexample

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

open abstract class BaseDialog(context: Context, @LayoutRes layoutRes: Int) : Dialog(context) {


    init {
        val inflate = View.inflate(context, layoutRes, null)
        setContentView(inflate)
        window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}
