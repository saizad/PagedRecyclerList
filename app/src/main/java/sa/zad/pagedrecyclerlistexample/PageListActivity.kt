package sa.zad.pagedrecyclerlistexample

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_example_list.*
import sa.zad.pagedrecyclerlist.ConstraintLayoutList
import sa.zad.pagedrecyclerlist.PageKeyedListDataSource
import sa.zad.pagedrecyclerlistexample.models.Items


class PageListActivity : ListExampleActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadingDialog.show()
        list.init(this,
            ConstraintLayoutList.CallbackPageKeyedList<Items, String> { next, callback ->
                apiService.list("https://www.reddit.com/r/all.json?limit=20&after=$next")
                    .doFinally {
                        loadingDialog.show(false)
                    }
                    .subscribe { redditPagedModel ->
                        val keyDataCallback =
                            PageKeyedListDataSource.KeyDataCallback<Items, String>(
                                redditPagedModel.data.children,
                                redditPagedModel.data.before,
                                redditPagedModel.data.after
                            )
                        callback.call(keyDataCallback)
                    }
            })
    }
}
