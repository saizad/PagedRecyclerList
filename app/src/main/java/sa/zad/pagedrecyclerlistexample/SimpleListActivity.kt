package sa.zad.pagedrecyclerlistexample

import android.content.Context
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_simple_list.*
import sa.zad.pagedrecyclerlist.AppList
import sa.zad.pagedrecyclerlist.AppListAdapter
import sa.zad.pagedrecyclerlistexample.models.Items

class SimpleListActivity : BaseActivity(), AppListAdapter.RecyclerViewAdapterListener<Items, ItemsTitleView>{

    lateinit var appList: AppList<Items, ItemsTitleView>

    override fun getItemView(context: Context?, viewType: Int): ItemsTitleView {
        return ItemsTitleView(this)
    }

    override fun onBindItemView(view: ItemsTitleView?, item: Items?, itemIndex: Int): Boolean {
        return false
    }

    override fun preGetItemView(itemIndex: Int, item: Items?): Int {
        return 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appList = simple_list as AppList<Items, ItemsTitleView>
        appList.listAdapter = AppListAdapter<Items, ItemsTitleView>(this)

        apiService.list("https://www.reddit.com/r/all.json?limit=100")
            .exception {
                log(it.message)
            }
            .subscribe {
                appList.listAdapter.items = it.data.children
            }
    }
}
