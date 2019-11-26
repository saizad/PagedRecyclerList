package sa.zad.pagedrecyclerlistexample

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.android.synthetic.main.list_dialog.*

class ListOptionDialog(context: Context) : BaseDialog(context, R.layout.list_dialog) {

    private val mutableLiveData: MutableLiveData<Int> = MutableLiveData()
    private var ogCount: Int = 0

    init {
        setOnShowListener {

            if(ogCount == -1){
                allSelection.isChecked = true
            }else if(ogCount == 0){
                removeCustomCount()
                radioGroup.clearCheck()
            }else if(ogCount == 1){
                radioGroup.check(singleSelection.id)
            }
            else{
                customCount.setText(ogCount.toString())
            }

            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when(checkedId){
                    allSelection.id -> {
                        removeCustomCount()
                        ogCount = -1
                    }
                    singleSelection.id -> {
                        removeCustomCount()
                        ogCount = 1
                    }
                }
            }

            resetButton.setOnClickListener {
                ogCount = 0
                mutableLiveData.value = ogCount
                dismiss()
            }

            doneButton.setOnClickListener {
                mutableLiveData.value = ogCount
                dismiss()
            }

            customCount.addTextChangedListener(object : TextWatcher{
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if(s != null && s.isNotEmpty()) {
//                        radioGroup.clearCheck()
                        ogCount = s.toString().toInt()
                    }
                }
            })
        }
    }

    private fun removeCustomCount(){
        customCount.text = null
    }

    fun show(count: Int): LiveData<Int> {
        ogCount = count
        super.show()
        return mutableLiveData
    }
}
