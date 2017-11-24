package com.dev.bins.calendar;

import android.graphics.Color;
import android.support.annotation.IntDef;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by bin on 25/10/2017.
 */

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.Holder> {

    public static final int STATE_OPEN = 1;
    public static final int STATE_COLLAPSE = 2;
    @STATE
    int mCurrentState = STATE_OPEN;
    private List<Date> dates = new ArrayList<>();
    private Calendar mCalendar;
    private int mCurrentSelectionPosition = -1;

    public CalendarAdapter(Calendar calendar) {
        this.mCalendar = calendar;
        nextMonth();
    }

    public int getCurrentSelectionPosition() {
        return mCurrentSelectionPosition;
    }

    public void setCurrentSelectionPosition(int mCurrentSelectionPosition) {
        this.mCurrentSelectionPosition = mCurrentSelectionPosition;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_text_day, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dates.get(position));
        Calendar current = mCalendar;
        holder.textView.setText(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        if (mCurrentState == STATE_COLLAPSE) {
            holder.textView.setTextColor(Color.BLACK);
            return;
        }
        if (current.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)) {
            holder.textView.setTextColor(Color.BLACK);
        } else {
            holder.textView.setTextColor(Color.GRAY);
        }
        Calendar today = Calendar.getInstance();

        if (isToday(calendar, today)) {
            holder.textView.setTextColor(Color.RED);
            mCurrentSelectionPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }

    private boolean isToday(Calendar calendar, Calendar today) {
        return today.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) && today.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) && today.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getCurrentState() {
        return mCurrentState;
    }

    public void setCurrentState(int mCurrentState) {
        this.mCurrentState = mCurrentState;
    }

    public Date getDate(int position) {
        if (position >= 0 && position < dates.size()) {
            return dates.get(position);
        }
        return null;
    }

    private void nextWeek() {
        List<Date> tempList = new ArrayList<>();
        int row = mCurrentSelectionPosition / 7;
        for (int i = 0; i < 7; i++) {
            tempList.add(dates.get(row * 7 + i));
        }
        dates.clear();
        dates.addAll(tempList);
        notifyDataSetChanged();
    }

    private void prevCollapseMonth() {
        Calendar temp = (Calendar) mCalendar.clone();
        Date firstDay = dates.get(0);
        temp.setTime(firstDay);
        temp.add(Calendar.DAY_OF_MONTH, -4 * 7);
        dates.clear();
        for (int i = 0; i < 28; i++) {
            dates.add(temp.getTime());
            temp.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    private void nextCollapseMonth() {
        Calendar temp = (Calendar) mCalendar.clone();
        Date firstDay = dates.get(dates.size() - 1);
        temp.setTime(firstDay);
        temp.add(Calendar.DAY_OF_MONTH, 1);
        dates.clear();
        for (int i = 0; i < 28; i++) {
            dates.add(temp.getTime());
            temp.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    public void nextMonth() {
        dates.clear();
        Calendar temp = (Calendar) mCalendar.clone();
        temp.set(Calendar.DAY_OF_MONTH, 1);
        int preDays = temp.get(Calendar.DAY_OF_WEEK) - 1;
        int dayInMonth = temp.getActualMaximum(Calendar.DATE);
        int days = preDays + dayInMonth;
        if (days % 7 != 0) {
            int count = days / 7;
            days = (count + 1) * 7;
        }
        temp.add(Calendar.DAY_OF_MONTH, -preDays);
        for (int i = 0; i < days; i++) {
            dates.add(temp.getTime());
            temp.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    // 判断首行是不是同一个月
    public boolean isPreviewSameMonth() {
        Date first = dates.get(0);
        Date last = dates.get(6);
        Calendar firstCalendar = Calendar.getInstance();
        firstCalendar.setTime(first);
        Calendar lastCalendar = Calendar.getInstance();
        lastCalendar.setTime(last);
        return firstCalendar.get(Calendar.YEAR) == lastCalendar.get(Calendar.YEAR) && firstCalendar.get(Calendar.MONTH) == lastCalendar.get(Calendar.MONTH);
    }

    //最后一行是不是同一个月
    public boolean isNextSameMonth() {

        Date first = dates.get(dates.size() - 7);
        Date last = dates.get(dates.size() - 1);
        Calendar firstCalendar = Calendar.getInstance();
        firstCalendar.setTime(first);
        Calendar lastCalendar = Calendar.getInstance();
        lastCalendar.setTime(last);
        return firstCalendar.get(Calendar.YEAR) == lastCalendar.get(Calendar.YEAR) && firstCalendar.get(Calendar.MONTH) == lastCalendar.get(Calendar.MONTH);
    }


    @IntDef({STATE_OPEN, STATE_COLLAPSE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface STATE {
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView textView;

        public Holder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_calendar_day);
        }
    }
}
