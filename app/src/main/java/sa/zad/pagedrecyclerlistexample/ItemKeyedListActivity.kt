package sa.zad.pagedrecyclerlistexample

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_item_key_list.*

class ItemKeyedListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_key_list)
        list.init(this, apiService)
    }
}
