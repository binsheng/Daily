package com.dev.bins.daily.main

import android.animation.Animator
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import com.dev.bins.daily.R
import com.dev.bins.daily.database.Record
import kotlinx.android.synthetic.main.dialog_add_record.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by bin on 17/09/2017.
 */
class AddDialog : DialogFragment() {


    var exitAnimator: Animator? = null

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
            createExitAnimator(view)
        }
        btnSave.setOnClickListener {
            var content = etContent.text.toString().trim()
            if (TextUtils.isEmpty(content)) {
                Toast.makeText(activity, getText(R.string.content_empty), Toast.LENGTH_SHORT)
                return@setOnClickListener
            }
            var record = Record()
            record.content = content
            record.save()
            EventBus.getDefault().post(MainActivity.AddRecordEvent(record))
            exitAnimator!!.start()
        }
        btnCancel.setOnClickListener {
            exitAnimator!!.start()
        }
        btnAddTime.setOnClickListener {

        }


    }

    private fun createExitAnimator(view: View) {
        exitAnimator = ViewAnimationUtils.createCircularReveal(view, view!!.width, view.height, view.height.toFloat(), 0f)
        exitAnimator!!.duration = 200
        exitAnimator!!.addListener(object : Animator.AnimatorListener {
            override fun onAnimationEnd(p0: Animator?) {
                dismiss()
            }

            override fun onAnimationStart(p0: Animator?) {

            }

            override fun onAnimationRepeat(p0: Animator?) {

            }

            override fun onAnimationCancel(p0: Animator?) {

            }
        })
    }


}