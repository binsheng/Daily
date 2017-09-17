package com.dev.bins.daily.addrecord

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.transition.Explode
import android.transition.Slide
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import butterknife.bindView
import com.dev.bins.daily.R
import com.dev.bins.daily.database.Record

class AddRecordActivity : AppCompatActivity() {


    val mBtnSave: Button by bindView(R.id.btn_save)
    val mBtnCancel: Button by bindView(R.id.btn_cancel)
    val mEtContent: EditText by bindView(R.id.et_content)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val slide = Slide()
        slide.duration = 200
        window.enterTransition = slide
        window.exitTransition = slide
        setContentView(R.layout.activity_add_record)
        init()
    }

    private fun init() {
        mBtnSave.setOnClickListener {
            val content = mEtContent.text.toString().trim()
            if (TextUtils.isEmpty(content)) {
                Toast.makeText(this, getString(R.string.content_empty), Toast.LENGTH_SHORT)
                return@setOnClickListener
            }
            var record = Record()
            record.content = content
            record.save()
            finish()
        }

    }


}
