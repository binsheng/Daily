package com.dev.bins.daily.main

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.AnimationUtils
import butterknife.bindView
import com.dev.bins.daily.R
import com.dev.bins.daily.addrecord.AddRecordActivity

class MainActivity : AppCompatActivity() {


    val recycleView: RecyclerView by bindView(R.id.recycleView)
    val fab: FloatingActionButton by bindView(R.id.fab)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab.setOnClickListener {
            
            val intent = Intent(this, AddRecordActivity::class.java)
            startActivity(intent)
        }
    }


}
