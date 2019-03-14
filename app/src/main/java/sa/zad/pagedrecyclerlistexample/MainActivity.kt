package sa.zad.pagedrecyclerlistexample

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        item_key_list_sample.setOnClickListener {
            startActivity(getActivityIntent(ItemKeyedListActivity::class.java, this))
        }

        page_key_list_sample.setOnClickListener {
            startActivity(getActivityIntent(PageKeyedListActivity::class.java, this))
        }

        simple_list_sample.setOnClickListener {
            startActivity(getActivityIntent(SimpleListActivity::class.java, this))
        }
    }
}
