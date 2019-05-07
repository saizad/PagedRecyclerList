package sa.zad.pagedrecyclerlistexample

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.list_item_activity_type.view.*
import sa.zad.pagedrecyclerlist.AppListAdapter
import sa.zad.pagedrecyclerlistexample.models.ActivityType

class ActivitiesView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    ConstraintLayout(context, attrs, defStyleAttr), AppListAdapter.AppAdapterItem<ActivityType> {

    init {
        Utils.inflate(context, R.layout.list_item_activity_type, this, true)
        layoutParams = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun bind(activityType: ActivityType) {
        activity_name.text = activityType.activity
    }

    override fun hideDivider(hide: Boolean) {

    }

    override fun getItem(): ActivityType? {
        return null
    }

    override fun select(select: Boolean) {

    }
}
