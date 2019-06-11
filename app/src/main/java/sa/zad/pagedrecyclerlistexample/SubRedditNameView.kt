package sa.zad.pagedrecyclerlistexample

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.list_item_view.view.*
import sa.zad.pagedrecyclerlist.AppListAdapter
import sa.zad.pagedrecyclerlistexample.models.Items

class SubRedditNameView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr), AppListAdapter.AppAdapterItem<Items> {

    init {
        Utils.inflate(context, R.layout.list_item_view, this, true)
        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun bind(items: Items) {
        content.text = items.data.subreddit
    }

    override fun hideDivider(hide: Boolean) {
        if(hide){
            divider.visibility = View.GONE
        }else{
            divider.visibility = View.VISIBLE
        }
    }

    override fun getItem(): Items? {
        return null
    }

    override fun select(select: Boolean) {

    }
}
