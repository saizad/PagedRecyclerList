package sa.zad.pagedrecyclerlistexample

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pageKeyListSample.setOnClickListener {
            startActivity(getActivityIntent(PageListActivity::class.java, this))
        }

        simpleListSample.setOnClickListener {
            startActivity(getActivityIntent(SimpleListActivity::class.java, this))
        }
    }
}
