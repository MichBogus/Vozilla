package com.vo.vozilla.dialog

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import com.vo.vozilla.application.VozillaApp
import com.vo.vozilla.application.maindi.modules.SingleChoiceDialogModule.SingleChoiceItemPicked
import com.vo.vozilla.ktextensions.argument
import io.reactivex.subjects.Subject
import javax.inject.Inject

class SingleChoiceDialog : DialogFragment() {

    companion object {

        private const val ITEMS_KEYS = "ITEMS_KEYS"
        private const val ITEMS_VALUES = "ITEMS_VALUES"
        private const val TITLE_KEY = "TITLE"

        fun create(keys: Array<String>, values: Array<String>, title: String? = "Select filter") =
                SingleChoiceDialog().apply {
                    arguments = Bundle().apply {
                        putStringArray(ITEMS_KEYS, keys)
                        putStringArray(ITEMS_VALUES, values)
                        putString(TITLE_KEY, title)
                    }
                }
    }

    @field:[Inject SingleChoiceItemPicked]
    @Suppress("LateinitUsage")
    lateinit var pickedItem: Subject<Pair<String, String>>

    private val title: String? by argument(TITLE_KEY)
    private val keys: Array<String> by argument(ITEMS_KEYS)
    private val values: Array<String> by argument(ITEMS_VALUES)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        (activity!!.application!! as VozillaApp).mainComponent!!.inject(this)
        return AlertDialog.Builder(activity!!)
                .setTitle(title)
                .setSingleChoiceItems(values, -1
                ) { dialog, item ->
                    pickedItem.onNext(Pair(keys[item], values[item]))
                    dialog.dismiss()
                }
                .create()
    }
}