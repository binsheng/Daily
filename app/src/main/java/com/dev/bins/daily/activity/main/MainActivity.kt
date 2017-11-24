package com.dev.bins.daily.activity.main


import android.content.Intent
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AccelerateDecelerateInterpolator
import com.dev.bins.daily.R
import com.dev.bins.daily.activity.BaseActivity
import com.dev.bins.daily.activity.main.Calendar.CalendarActivity
import com.dev.bins.daily.adapter.DailyAdapter
import com.dev.bins.daily.database.Record
import com.dev.bins.daily.database.Record_Table
import com.raizlabs.android.dbflow.kotlinextensions.or
import com.raizlabs.android.dbflow.sql.language.SQLite
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*


class MainActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


    var adapter: DailyAdapter? = null
    val datas = ArrayList<Record>()

    var addDialog: AddDialog? = null


    override fun init() {
        initToolbar(toolbar)
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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (R.id.menu_calendar == item!!.itemId) {
            val intent = Intent(this, CalendarActivity::class.java)
            ActivityCompat.startActivity(this,intent,ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())
            return true
        }

        return super.onOptionsItemSelected(item)
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
