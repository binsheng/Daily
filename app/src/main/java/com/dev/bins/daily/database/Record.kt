package com.dev.bins.daily.database

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.structure.BaseModel
import java.util.*

/**
 * Created by bin on 14/09/2017.
 */

@Table(database = Daily::class)
class Record : BaseModel() {
    @PrimaryKey(autoincrement = true)
    var id: Int = 0
    @Column
    var startDate: Date? = null
    @Column
    var endDate: Date? = null
    @Column
    var content: String? = null

}
