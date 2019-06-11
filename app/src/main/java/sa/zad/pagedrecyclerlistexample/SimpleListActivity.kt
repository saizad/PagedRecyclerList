package sa.zad.pagedrecyclerlistexample

import android.content.Context
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_simple_list.*
import sa.zad.pagedrecyclerlist.AppList
import sa.zad.pagedrecyclerlist.AppListAdapter
import sa.zad.pagedrecyclerlistexample.models.Items

class SimpleListActivity : BaseActivity(), AppListAdapter.RecyclerViewAdapterListener<Items, SubRedditNameView>{

    lateinit var appList: AppList<Items, SubRedditNameView>

    override fun getItemView(context: Context?, viewType: Int): SubRedditNameView {
        return SubRedditNameView(this)
    }

    override fun onBindItemView(view: SubRedditNameView?, item: Items?, itemIndex: Int): Boolean {
        return false
    }

    override fun preGetItemView(itemIndex: Int, item: Items?): Int {
        return 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appList = simple_list as AppList<Items, SubRedditNameView>
        appList.listAdapter = AppListAdapter<Items, SubRedditNameView>(this)

        apiService.list("https://www.reddit.com/r/all.json?limit=100")
            .exception {
                log(it.message)
            }
            .subscribe {
                appList.listAdapter.items = it.data.children
            }
    }
}
