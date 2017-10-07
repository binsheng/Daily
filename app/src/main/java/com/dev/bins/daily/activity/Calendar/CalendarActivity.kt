package com.dev.bins.daily.activity.main.Calendar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.dev.bins.daily.R
import com.dev.bins.daily.activity.BaseActivity
import kotlinx.android.synthetic.main.layout_toolbar.*

class CalendarActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_calendar
    }

    override fun init() {
        initToolbar(toolbar,true)
    }


}
