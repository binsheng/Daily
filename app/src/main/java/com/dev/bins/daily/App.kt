package com.dev.bins.daily

import android.app.Application
import com.raizlabs.android.dbflow.config.FlowManager

/**
 * Created by bin on 14/09/2017.
 */


class App: Application() {

    override fun onCreate() {
        super.onCreate()
        FlowManager.init(this)
    }


}