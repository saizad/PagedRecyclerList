package sa.zad.pagedrecyclerlistexample

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_paged_key_list.*

class PageKeyedListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paged_key_list)
        list.init(this, apiService)
        //simple
        /*apiService.list("https://www.reddit.com/r/all.json?limit=20&after=")
            .exception {
            }.subscribe {
                list.listAdapter.items = it.data.children
            }*/
    }
}
