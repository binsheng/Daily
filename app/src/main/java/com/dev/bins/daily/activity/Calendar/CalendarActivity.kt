package com.dev.bins.daily.activity.main.Calendar

import com.dev.bins.daily.R
import com.dev.bins.daily.activity.BaseActivity
import com.dev.bins.daily.activity.Calendar.CustomeDayView
import com.ldf.calendar.component.CalendarAttr
import com.ldf.calendar.component.CalendarViewAdapter
import com.ldf.calendar.interf.OnSelectDateListener
import com.ldf.calendar.model.CalendarDate
import com.ldf.calendar.view.MonthPager
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlinx.android.synthetic.main.activity_calendar.*


class CalendarActivity : BaseActivity() {


    var calendarDate: CalendarDate? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_calendar
    }

    override fun init() {
        initToolbar(toolbar,true)
        calendarDate = CalendarDate()
        val adapter = CalendarViewAdapter(this, object : OnSelectDateListener {
            override fun onSelectOtherMonth(offset: Int) {
                calendarview.selectOtherMonth(offset)
            }

            override fun onSelectDate(calendarDate: CalendarDate?) {
                refreshClickDate(calendarDate!!)
            }

        }, CalendarAttr.CalendayType.MONTH, CustomeDayView(this, R.layout.custom_day))
        calendarview.adapter = adapter
        calendarview.currentItem = MonthPager.CURRENT_DAY_INDEX

    }


    private fun refreshClickDate(date: CalendarDate) {
        calendarDate = date
    }

}
