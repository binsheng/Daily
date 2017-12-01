package com.dev.bins.daily.activity.main.Calendar

import android.support.v7.widget.LinearLayoutManager
import com.dev.bins.daily.R
import com.dev.bins.daily.activity.BaseActivity
import com.dev.bins.daily.adapter.CalendarAdapter
import com.dev.bins.daily.database.Record
import com.dev.bins.daily.database.Record_Table
import com.raizlabs.android.dbflow.kotlinextensions.or
import com.raizlabs.android.dbflow.sql.language.SQLite
import kotlinx.android.synthetic.main.activity_calendar.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import java.util.*


class CalendarActivity : BaseActivity() {

    val datas = ArrayList<Record>()

    var adapter: CalendarAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_calendar
    }

    override fun init() {
        initToolbar(toolbar,true)
        val startCalendar = Calendar.getInstance()
        startCalendar.set(Calendar.HOUR_OF_DAY, 0)
        startCalendar.set(Calendar.MINUTE, 0)
        startCalendar.set(Calendar.SECOND, 0)
        val dayStartTime = startCalendar.time
        val endCalendar = Calendar.getInstance()
        endCalendar.set(Calendar.HOUR_OF_DAY, 23)
        endCalendar.set(Calendar.MINUTE, 59)
        endCalendar.set(Calendar.SECOND, 59)
        val dayEndTime = endCalendar.time
        val data = SQLite.select()
                .from(Record::class.java)
                .where(Record_Table.startDate.greaterThan(dayStartTime))
                .and(Record_Table.endDate.lessThan(dayEndTime).or(Record_Table.endDate.isNull))
                .orderBy(Record_Table.startDate, false)
                .queryList()

        datas.addAll(data)
        calendarview.setOnItemClickListener { position, date ->
            startCalendar.time = date
            startCalendar.set(Calendar.HOUR_OF_DAY, 0)
            startCalendar.set(Calendar.MINUTE, 0)
            startCalendar.set(Calendar.SECOND, 0)

            endCalendar.time = date
            endCalendar.set(Calendar.HOUR_OF_DAY, 23)
            endCalendar.set(Calendar.MINUTE, 59)
            endCalendar.set(Calendar.SECOND, 59)

            datas.clear()
            val data = SQLite.select()
                    .from(Record::class.java)
                    .where(Record_Table.startDate.greaterThan(dayStartTime))
                    .and(Record_Table.endDate.lessThan(dayEndTime).or(Record_Table.endDate.isNull))
                    .orderBy(Record_Table.startDate, false)
                    .queryList()

            datas.addAll(data)
            adapter!!.notifyDataSetChanged()
        }
        adapter = CalendarAdapter(datas)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter

    }

}
