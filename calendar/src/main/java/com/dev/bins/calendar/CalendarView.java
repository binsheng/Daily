package com.dev.bins.calendar;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by bin on 08/10/2017.
 */

public class CalendarView extends LinearLayout {

    private ImageView mIvPrev;
    private ImageView mIvNext;
    private TextView mTvDate;

    private GridView mGridView;


    private Calendar mCalendar = Calendar.getInstance();

    public CalendarView(Context context) {
        this(context, null);
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initWidget(context);
        initEvent();
        renderGridView();
    }

    private void initEvent() {


        mIvPrev.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalendar.add(Calendar.MONTH, -1);
                renderGridView();
            }
        });

        mIvNext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalendar.add(Calendar.MONTH, 1);
                renderGridView();
            }
        });


    }

    private void renderGridView() {

        SimpleDateFormat sdf = new SimpleDateFormat("MM yyyy");
        mTvDate.setText(sdf.format(mCalendar.getTime()));


        ArrayList<Date> cells = new ArrayList<>();

        Calendar calendar = (Calendar) mCalendar.clone();

        calendar.set(Calendar.DAY_OF_MONTH, 1);

        int preDays = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        calendar.set(Calendar.DAY_OF_MONTH, -preDays);

        int maxCellCount = 6 * 7;

        while (cells.size() < maxCellCount) {
            cells.add(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        mGridView.setAdapter(new CalendarAdapter(getContext(), cells));

    }

    private void initWidget(Context context) {
        LayoutInflater.from(context).inflate(R.layout.calendar_view, this);

        mIvPrev = findViewById(R.id.iv_prev);
        mIvNext = findViewById(R.id.iv_next);
        mTvDate = findViewById(R.id.tv_date);

        mGridView = findViewById(R.id.grid_calendar);
    }


    private class CalendarAdapter extends ArrayAdapter<Date> {


        private final LayoutInflater inflater;

        public CalendarAdapter(@NonNull Context context, @NonNull List<Date> days) {
            super(context, R.layout.calendar_text_day,days);

            inflater = LayoutInflater.from(context);
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            Date date = getItem(position);


            if (null == convertView) {
                convertView = inflater.inflate(R.layout.calendar_text_day, parent, false);
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            ((TextView) convertView).setText(String.valueOf(day));

            Calendar now = Calendar.getInstance();

            boolean isTheSameMonth = false;
            if (calendar.get(Calendar.MONTH) == now.get(Calendar.MONTH)){
                isTheSameMonth = true;
            }

            if (isTheSameMonth) {
                ((TextView) convertView).setTextColor(Color.BLACK);
            } else {
                ((TextView) convertView).setTextColor(Color.GRAY);
            }

            //当天
            if (now.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)&& calendar.get(Calendar.YEAR) == now.get(Calendar.YEAR)
                    && calendar.get(Calendar.MONTH) == now.get(Calendar.MONTH)) {
                ((TextView) convertView).setTextColor(Color.RED);

            }

            return convertView;
        }
    }

}
