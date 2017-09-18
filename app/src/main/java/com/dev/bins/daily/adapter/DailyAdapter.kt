package com.dev.bins.daily.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.bindView
import com.dev.bins.daily.R
import com.dev.bins.daily.database.Record

/**
 * Created by bin on 16/09/2017.
 */
class DailyAdapter(datas: ArrayList<Record>) : RecyclerView.Adapter<DailyAdapter.Holder>() {

    var datas: ArrayList<Record>


    init {
        this.datas = datas
    }


    override fun onBindViewHolder(holder: Holder?, position: Int) {
        val data = datas[position]
        holder!!.mTvContent.text = data.content
        holder.mTvStartTime.text = data.startDate.toString()
        holder.mTvEndTime.text = data.endDate.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.daily_item, parent, false);
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }


    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mTvStartTime: TextView by bindView(R.id.tv_start_time)
        val mTvEndTime: TextView by bindView(R.id.tv_end_time)
        val mTvContent: TextView by bindView(R.id.tv_content)

    }


}

