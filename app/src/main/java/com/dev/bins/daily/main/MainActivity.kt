package com.dev.bins.daily.main


import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.animation.AccelerateDecelerateInterpolator
import com.dev.bins.daily.R
import com.dev.bins.daily.adapter.DailyAdapter
import com.dev.bins.daily.database.Record
import com.dev.bins.daily.database.Record_Table
import com.raizlabs.android.dbflow.kotlinextensions.or
import com.raizlabs.android.dbflow.sql.language.SQLite
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*


class MainActivity : AppCompatActivity() {


    var adapter: DailyAdapter? = null
    val datas = ArrayList<Record>()

    var addDialog: AddDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
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
        adapter = DailyAdapter(datas)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter
        fab.setOnClickListener {
            addDialog = AddDialog()
            addDialog!!.show(supportFragmentManager, AddDialog.FRAGMENT_NAME)

        }

        val animate = fab.animate()
        animate.scaleX(1f)
                .scaleY(1f)
                .interpolator = AccelerateDecelerateInterpolator()
        animate.duration = 500
        animate.start()


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public fun onAddRecordEvent(recordEvent: AddRecordEvent) {
        datas.add(0, recordEvent.record)
        adapter!!.notifyItemInserted(0)
        recycleView.smoothScrollToPosition(0)
    }


    public override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    public override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }


    class AddRecordEvent(record: Record) {

        var record: Record = record
    }


}
