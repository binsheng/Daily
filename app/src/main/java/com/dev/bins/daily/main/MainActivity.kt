package com.dev.bins.daily.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.bindView
import com.dev.bins.daily.R
import com.dev.bins.daily.adapter.DailyAdapter
import com.dev.bins.daily.addrecord.AddRecordActivity
import com.dev.bins.daily.database.Record
import com.raizlabs.android.dbflow.sql.language.SQLite


class MainActivity : AppCompatActivity() {


    val recycleView: RecyclerView by bindView(R.id.recycleView)
    val fab: FloatingActionButton by bindView(R.id.fab)

    var adapter: DailyAdapter? = null
    val datas = ArrayList<Record>()


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
            val intent = Intent(applicationContext, AddRecordActivity::class.java)
            startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())
        }
    }


}
