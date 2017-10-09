package com.dev.bins.daily.activity.Calendar

import android.content.Context
import android.graphics.Color
import android.view.View
import com.ldf.calendar.interf.IDayRenderer
import com.ldf.calendar.view.DayView
import com.ldf.calendar.Utils
import com.ldf.calendar.model.CalendarDate
import com.ldf.calendar.component.State
import kotlinx.android.synthetic.main.custom_day.*
import com.ldf.calendar.Utils.loadMarkData
import kotlinx.android.synthetic.main.custom_day.view.*


/**
 * Created by bin on 09/10/2017.
 */
class CustomeDayView : DayView {


    private val today = CalendarDate()


    constructor(context: Context, layoutResource: Int) : super(context, layoutResource) {


    }


    override fun refreshContent() {
        renderToday(day.date)
        renderSelect(day.state)
        renderMarker(day.date, day.state)
        super.refreshContent()
    }

    private fun renderMarker(date: CalendarDate, state: State) {
        if (Utils.loadMarkData().containsKey(date.toString())) {
            if (state === State.SELECT || date.toString() == today.toString()) {
                ivMaker.setVisibility(View.GONE)
            } else {
                ivMaker.setVisibility(View.VISIBLE)
                if (Utils.loadMarkData().get(date.toString()).equals("0")) {
                    ivMaker.setEnabled(true)
                } else {
                    ivMaker.setEnabled(false)
                }
            }
        } else {
            ivMaker.setVisibility(View.GONE)
        }
    }


    private fun renderSelect(state: State) {
        if (state === State.SELECT) {
            selectedBackground.setVisibility(View.VISIBLE)
            tvDate.setTextColor(Color.WHITE)
        } else if (state === State.NEXT_MONTH || state === State.PAST_MONTH) {
            selectedBackground.setVisibility(View.GONE)
            tvDate.setTextColor(Color.parseColor("#d5d5d5"))
        } else {
            selectedBackground.setVisibility(View.GONE)
            tvDate.setTextColor(Color.parseColor("#111111"))
        }
    }

    private fun renderToday(date: CalendarDate?) {
        if (date != null) {
            if (date.equals(today)) {
                tvDate.setText("今")
                todayBackground.setVisibility(View.VISIBLE)
            } else {
                tvDate.setText(date.day.toString() + "")
                todayBackground.setVisibility(View.GONE)
            }
        }
    }

    override fun copy(): IDayRenderer {
        return CustomeDayView(context, layoutResource)
    }

}