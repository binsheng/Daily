package com.dev.bins.daily.data

import com.dev.bins.daily.data.Daily.Companion.DB_NAME
import com.dev.bins.daily.data.Daily.Companion.DB_VERSION
import com.raizlabs.android.dbflow.annotation.Database

/**
 * Created by bin on 14/09/2017.
 */
@Database(name = DB_NAME, version = DB_VERSION)
class Daily{
    companion object {
        const val DB_NAME = "Daily"
        const val DB_VERSION = 1
    }
}
