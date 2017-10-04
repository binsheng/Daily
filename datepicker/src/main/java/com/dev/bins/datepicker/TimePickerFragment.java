package com.dev.bins.datepicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

/**
 * Created by bin on 25/09/2017.
 */

public class TimePickerFragment extends Fragment {


    private static String OK = "ok";
    private static String CANCEL = "cancel";
    private TimePicker mTimePicker;
    private Button mBtnOk;
    private Button mBtnCancel;
    private TimePicker.OnTimeChangedListener listener;
    private View.OnClickListener mBtnOkListener;
    private View.OnClickListener mBtnCancelListener;

    public static TimePickerFragment newInstance(String ok, String cancel) {

        Bundle args = new Bundle();
        args.putString(OK, ok);
        args.putString(CANCEL, cancel);
        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }


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
        mBtnOk = (Button) view.findViewById(R.id.btn_ok);
        mBtnCancel = (Button) view.findViewById(R.id.btn_cancel);
        Bundle args = getArguments();
        if (!TextUtils.isEmpty(args.getString(OK))){
            mBtnOk.setText(args.getString(OK));
        }
        if (!TextUtils.isEmpty(args.getString(CANCEL))){
            mBtnCancel.setText(args.getString(CANCEL));
        }
        mTimePicker.setIs24HourView(true);
        mBtnOk.setOnClickListener(mBtnOkListener);
        mBtnCancel.setOnClickListener(mBtnCancelListener);
        mTimePicker.setOnTimeChangedListener(listener);
    }


    public void setListener(TimePicker.OnTimeChangedListener listener) {
        this.listener = listener;
    }


    public void setPositiveButton(View.OnClickListener listener) {
        mBtnOkListener = listener;
    }

    public void setNegativeButton(View.OnClickListener listener) {
        mBtnCancelListener = listener;
    }


}
