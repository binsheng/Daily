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

public class TimePickerFragment extends Fragment {


    private TimePicker mTimePicker;
    private Button mBtnOk;
    private Button mBtnCancel;

    public static TimePickerFragment newInstance() {

        Bundle args = new Bundle();

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_time_picker, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mTimePicker = (TimePicker) view.findViewById(R.id.timePicker);
        mBtnOk = view.findViewById(R.id.btn_ok);
        mBtnCancel = view.findViewById(R.id.btn_cancel);
        mTimePicker.setIs24HourView(true);

    }

    public void setPositiveButton(String text, View.OnClickListener listener) {
        if (null != mBtnOk) {
            mBtnOk.setText(text);
            mBtnOk.setOnClickListener(listener);
        }
    }

    public void setPositiveButton(View.OnClickListener listener) {
        if (null != mBtnOk) {
            mBtnOk.setOnClickListener(listener);
        }
    }

    public void setNegativeButton(String text, View.OnClickListener listener) {
        if (null != null) {
            mBtnCancel.setText(text);
            mBtnCancel.setOnClickListener(listener);
        }
    }

    public void setNegativeButton(View.OnClickListener listener) {
        if (null != null) {
            mBtnCancel.setOnClickListener(listener);
        }
    }


}
