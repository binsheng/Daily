package com.dev.bins.datepicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

/**
 * Created by bin on 25/09/2017.
 */

public class TimePickerFragment extends Fragment implements TimePicker.OnTimeChangedListener {


    private boolean mIs24HourView;
    private int mInitialMinute;
    private int mInitialHourOfDay;
    private TimePicker mTimePicker;

    private OnTimeSetListener mTimeSetListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_time_picker, container, false);
        return  new Button(getContext());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mTimePicker = view.findViewById(R.id.timePicker);
//        mTimePicker.setOnTimeChangedListener(this);

    }

    public void setmTimeSetListener(OnTimeSetListener mTimeSetListener) {
        this.mTimeSetListener = mTimeSetListener;
    }

//    public TimePickerFragment(@NonNull Context context, OnTimeSetListener listener, int hourOfDay, int minute,
//                              boolean is24HourView) {
//        super(context);
//        mTimeSetListener = listener;
//        mInitialHourOfDay = hourOfDay;
//        mInitialMinute = minute;
//        mIs24HourView = is24HourView;
//        View view = LayoutInflater.from(context).inflate(R.layout.fragment_time_picker, this, true);
//        mTimePicker = view.findViewById(R.id.timePicker);
//        mTimePicker.setOnTimeChangedListener(this);
//    }

    @Override
    public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
        if (null != mTimeSetListener) {
            mTimeSetListener.onTimeSet(timePicker, hourOfDay, minute);
        }
    }

    public interface OnTimeSetListener {
        /**
         * Called when the user is done setting a new time and the dialog has
         * closed.
         *
         * @param view      the view associated with this listener
         * @param hourOfDay the hour that was set
         * @param minute    the minute that was set
         */
        void onTimeSet(TimePicker view, int hourOfDay, int minute);
    }


}
