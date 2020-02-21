package sa.zad.pagedrecyclerlistexample

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.list_item_view.view.*
import sa.zad.pagedrecyclerlist.ConstraintLayoutItem
import sa.zad.pagedrecyclerlistexample.models.Items

class SubRedditNameItem @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayoutItem<Items>(context, attrs, defStyleAttr) {

    public var isFav = false

    init {
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun bind(items: Items) {
        super.bind(items)
        content.text = items.data.subreddit
        favImageView.isSelected = isFav
        Utils.switchVisibility(selectImageView, isSelectMode)

        favImageView.setOnClickListener {
            callOption(SubRedditNameList.FAV_OPTION)
        }
    }

    override fun lastItem(hide: Boolean) {
        if(hide){
            divider.visibility = View.GONE
        }else{
            divider.visibility = View.VISIBLE
        }
    }

    override fun select(select: Boolean) {
        selectImageView.isSelected = select
    }

    override fun selectionView(): View {
        if(itemIndex % 2 == 0){
            return this
        }
        return selectImageView
    }
}
