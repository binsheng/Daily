package com.dev.bins.datepicker;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bin on 25/09/2017.
 */

public class TimePickerDialog extends AlertDialog implements DialogInterface.OnClickListener {


    private final FragmentManager mFragmentManager;
    private final android.app.TimePickerDialog.OnTimeSetListener mTimeSetListener;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Adapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<>();

    public TimePickerDialog(@NonNull Context context, android.app.TimePickerDialog.OnTimeSetListener listener, FragmentManager fm) {
        this(context, resolveDialogTheme(context, 0), listener, fm);
    }

    public TimePickerDialog(@NonNull Context context, @StyleRes int themeResId, android.app.TimePickerDialog.OnTimeSetListener listener, FragmentManager fm) {
        super(context, themeResId);
        mFragmentManager = fm;
        mTimeSetListener = listener;
        final Context themeContext = getContext();
        final LayoutInflater inflater = LayoutInflater.from(themeContext);
        final View view = inflater.inflate(R.layout.dialog_time_picker, null);
        setView(view);
        initView(view);
        setButton(BUTTON_POSITIVE, getContext().getString(android.R.string.ok), this);
        setButton(BUTTON_NEGATIVE, getContext().getString(android.R.string.cancel), this);
    }

    private void initView(View view) {
        mTabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        mViewPager = (ViewPager) view.findViewById(R.id.vp);
        mFragments.add(TimePickerFragment.newInstance());
        mFragments.add(TimePickerFragment.newInstance());
        mAdapter = new Adapter(mFragmentManager);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    static int resolveDialogTheme(Context context, int resId) {
        if (resId == 0) {
            final TypedValue outValue = new TypedValue();
            context.getTheme().resolveAttribute(android.R.attr.timePickerDialogTheme, outValue, true);
            return outValue.resourceId;
        } else {
            return resId;
        }
    }



    @Override
    public void onClick(DialogInterface dialogInterface, int which) {

        switch (which) {
            case BUTTON_POSITIVE:
//                if (mTimeSetListener != null) {
//                    mTimeSetListener.onTimeSet(mTimePicker, mTimePicker.getCurrentHour(),
//                            mTimePicker.getCurrentMinute());
//                }
                break;
            case BUTTON_NEGATIVE:
                cancel();
                break;
        }


    }


    class Adapter extends FragmentStatePagerAdapter {


        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return String.valueOf(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }


}
