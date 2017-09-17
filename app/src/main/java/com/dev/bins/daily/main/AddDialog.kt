package com.dev.bins.daily.main

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.TextUtils
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import butterknife.bindView
import com.dev.bins.daily.R
import com.dev.bins.daily.database.Record
import org.greenrobot.eventbus.EventBus

/**
 * Created by bin on 17/09/2017.
 */
class AddDialog : DialogFragment() {


    val mBtnSave: Button by bindView(R.id.btn_save)
    val mBtnCancel: Button by bindView(R.id.btn_cancel)
    val mEtContent: EditText by bindView(R.id.et_content)


    override fun onResume() {
        super.onResume()
        var params = dialog.window.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog.window.attributes = params
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCanceledOnTouchOutside(false)
        dialog.window.setGravity(Gravity.BOTTOM)
        return inflater!!.inflate(R.layout.dialog_add_record, container)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view!!.post {
            val animator = ViewAnimationUtils.createCircularReveal(view, view!!.width, view.height, 0f, view.height.toFloat())
            animator.duration = 200
            animator.start()
        }
        mBtnSave.setOnClickListener {
            var content = mEtContent.text.toString().trim()
            if (TextUtils.isEmpty(content)) {
                Toast.makeText(activity, getText(R.string.content_empty), Toast.LENGTH_SHORT)
                return@setOnClickListener
            }
            var record = Record()
            record.content = content
            record.save()
            EventBus.getDefault().post(MainActivity.AddRecordEvent(record))
            dismiss()
        }
        mBtnCancel.setOnClickListener {
            dismiss()
        }


    }


}