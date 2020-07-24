package sa.zad.pagedrecyclerlistexample

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CheckBox
import androidx.annotation.CallSuper
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_example_list.*
import sa.zad.pagedrecyclerlistexample.models.Items
import kotlin.math.min

abstract class ListExampleActivity : BaseActivity() {
    private var selectionOption: MenuItem? = null
    lateinit var loadingDialog: LoadingDialog
    lateinit var apiService: ApiService
    lateinit var favSubRedditList: MutableList<Items>

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example_list)
        loadingDialog = LoadingDialog(this)
        apiService = ExampleApplication.getInstance().service()
        favSubRedditList = ExampleApplication.getInstance().favSubRedditList
        this.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        list.setSelectedCountListener {
            updateSelectionCount(it)
        }

        list.setItemOnBindListener { item, itemView, itemIndex ->
            val find = favSubRedditList.find {
                it.data.subredditId.equals(item.data.subredditId)
            }
            itemView.isFav = find != null
        }

        list.setItemOnClickListener { item, itemView, itemIndex ->
            toast(item.data.subreddit)
        }

        list.setItemOptionSelectedListener { selected, item, view, payload ->
            val listAdapter = list.listAdapter
            when (selected) {
                SubRedditNameList.FAV_OPTION -> {
                    val find = favSubRedditList.find {
                        it.data.subredditId.equals(item.data.subredditId)
                    }
                    if (find != null) {
                        favSubRedditList.remove(find)
                    } else {
                        favSubRedditList.add(item)
                    }
                    listAdapter.notifyItemChanged(listAdapter.items.indexOf(item))
                }
                SubRedditNameList.DELETE_ITEM_OPTION -> {
                    val itemAt = listAdapter.items.find {
                        it.areItemsTheSame(it, view.item)
                    }
                    val indexOf = listAdapter.items.indexOf(itemAt)
                    listAdapter.items.removeAt(indexOf)
                    listAdapter.notifyItemRemoved(indexOf)
                }
            }
        }
        loadList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.list_menu, menu)
        selectionOption = menu.findItem(R.id.options)
        val checkBox = menu.findItem(R.id.reselect).actionView as CheckBox
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            list.radioSelection = isChecked
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.options) {
            ListOptionDialog(this).show(count())
                .observe(this, Observer {
                    this.countSelected(it.first, it.second)
                })
        } else if (item.itemId == android.R.id.home) {
            finish()
        } else if(item.itemId == R.id.refresh){
            loadList()
        }
        return super.onOptionsItemSelected(item)
    }

    fun count(): Int {
        return list.count
    }

    fun countSelected(count: Int, randomSelect: Boolean = false) {
        updateSelectionCount(0)
        if (randomSelect) {
            list.setSelectionCount(count, count == 1)
            val items = list.listAdapter.items
            if (items.isNotEmpty() && count >= 0) {
                list.addSelectedItems(items.subList(0, min(items.size, count)))
            }
        } else {
            list.setSelectionCount(count, count == 1)
        }
    }

    abstract fun loadList()

    protected fun updateSelectionCount(count: Int) {
        CountDrawable.setCount(this, selectionOption!!.icon, count)
    }
}
