package com.dev.bins.daily.addrecord

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.Explode
import android.transition.Slide
import com.dev.bins.daily.R

class AddRecordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val explode = Explode()
        explode.duration = 200
        val slide = Slide()
        slide.duration = 200
        window.enterTransition = slide
        window.exitTransition = slide
        setContentView(R.layout.activity_add_record)
    }
}
