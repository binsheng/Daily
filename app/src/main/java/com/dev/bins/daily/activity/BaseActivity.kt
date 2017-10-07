package com.dev.bins.daily.activity

import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.transition.Explode
import android.transition.Slide
import android.view.MenuItem

/**
 * Created by bin on 07/10/2017.
 */
abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val explode = Explode()
        explode.duration = 200
        val slide = Slide()
        slide.duration = 200
        window.enterTransition = slide
        window.exitTransition = explode

        setContentView(getLayoutId())
        init()
    }


    abstract fun init()

    fun initToolbar(toolbar: Toolbar) {
        initToolbar(toolbar, false)
    }

    fun initToolbar(toolbar: Toolbar, back: Boolean) {
        setSupportActionBar(toolbar)
        if (back) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    abstract fun getLayoutId(): Int

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item!!.itemId == android.R.id.home) {
            ActivityCompat.finishAfterTransition(this)
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}