package sa.zad.pagedrecyclerlistexample

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_example_list.*

class SimpleListActivity : ListExampleActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialog.show()
        apiService.list("https://www.reddit.com/r/all.json?limit=30")
            .exception {
                log(it.message)
            }
            .doFinally {
                loadingDialog.show(false)
            }
            .subscribe {
                list.listAdapter.items = it.data.children
            }

        list.setSelectedCountListener {
            updateSelectionCount(it)
        }
    }
}
