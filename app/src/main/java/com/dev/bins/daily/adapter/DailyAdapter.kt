package com.dev.bins.daily.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.bins.daily.R
import com.dev.bins.daily.database.Record
import kotlinx.android.synthetic.main.daily_item.view.*
import java.text.SimpleDateFormat

/**
 * Created by bin on 16/09/2017.
 */
class DailyAdapter(datas: ArrayList<Record>) : RecyclerView.Adapter<DailyAdapter.Holder>() {

    var datas: ArrayList<Record>
    val sdf = SimpleDateFormat("HH:mm")

    init {
        this.datas = datas
    }


    override fun onBindViewHolder(holder: Holder?, position: Int) {
        holder?.bindData(datas[position], sdf)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        val view = LayoutInflater.from(parent!!.context).inflate(R.layout.daily_item, parent, false);
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return datas.size
    }


    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvContent = itemView.tvContent
        val tvStartTime = itemView.tvStartTime
        val tvEndTime = itemView.tvEndTime

        fun bindData(record: Record, sdf: SimpleDateFormat) {
            tvContent.text = record.content
            tvStartTime.text = sdf.format(record.startDate)
            if (null != record.endDate) {
                tvEndTime.text = sdf.format(record.endDate)
            } else {
                tvEndTime.text = itemView.context.getString(R.string.doing)
            }
        }

    }


}

