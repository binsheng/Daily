package com.dev.bins.daily.main


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.animation.AccelerateDecelerateInterpolator
import com.dev.bins.daily.R
import com.dev.bins.daily.R.id.recycleView
import com.dev.bins.daily.adapter.DailyAdapter
import com.dev.bins.daily.database.Record
import com.raizlabs.android.dbflow.sql.language.SQLite
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


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
        val data = SQLite.select()
                .from(Record::class.java).queryList()
        datas.addAll(data)
        adapter = DailyAdapter(datas)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter
        fab.setOnClickListener {
            addDialog = AddDialog()
            addDialog!!.show(supportFragmentManager, "add")
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
