package com.dev.bins.daily.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.bins.daily.R
import com.dev.bins.daily.database.Record
import kotlinx.android.synthetic.main.daily_item.view.*

/**
 * Created by bin on 27/11/2017.
 */
class CalendarAdapter(datas: ArrayList<Record>) : RecyclerView.Adapter<CalendarAdapter.Holder>() {


    var datas: ArrayList<Record>

    init {
        this.datas = datas
    }


    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        holder?.tvContent?.text  = datas.get(position).content

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.calendar_item, parent, false);
        return Holder(view)
    }


    class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        val tvContent = itemView!!.tvContent

    }

}