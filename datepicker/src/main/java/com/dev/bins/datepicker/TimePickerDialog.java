package com.dev.bins.datepicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bin on 25/09/2017.
 */

public class TimePickerDialog extends DialogFragment{


    TabLayout mTabLayout;
    ViewPager mViewPager;
    Adapter mAdapter;

    List<Fragment> mFragments = new ArrayList<>();

    TimePickerFragment.OnTimeSetListener mFromTimeSetListener;
    TimePickerFragment.OnTimeSetListener mToTimeSetListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_time_picker,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mTabLayout =  view.findViewById(R.id.tablayout);
        mViewPager = view.findViewById(R.id.viewPager);
        TimePickerFragment fromFragment = new TimePickerFragment();
        fromFragment.setmTimeSetListener(mFromTimeSetListener);
        TimePickerFragment toFragment = new TimePickerFragment();
        toFragment.setmTimeSetListener(mToTimeSetListener);
        mFragments.add(fromFragment);
        mFragments.add(toFragment);
        mAdapter = new Adapter(getFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    public void setmFromTimeSetListener(TimePickerFragment.OnTimeSetListener mFromTimeSetListener) {
        this.mFromTimeSetListener = mFromTimeSetListener;
    }

    public void setmToTimeSetListener(TimePickerFragment.OnTimeSetListener mToTimeSetListener) {
        this.mToTimeSetListener = mToTimeSetListener;
    }

    class Adapter extends FragmentPagerAdapter {



        public Adapter(FragmentManager fm) {
            super(fm);
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

    public class Builder{
        TimePickerFragment.OnTimeSetListener fromTimeSetListener;
        TimePickerFragment.OnTimeSetListener toTimeSetListener;

        public Builder() {
        }

        public Builder setFromTiemSetListener(TimePickerFragment.OnTimeSetListener listener){
            fromTimeSetListener = listener;
            return  this;
        }

        public Builder setToTimeSetListenr(TimePickerFragment.OnTimeSetListener listenr){
            toTimeSetListener = listenr;
            return this;
        }


        public TimePickerDialog show(){
            TimePickerDialog dialog = new TimePickerDialog();
            dialog.setmFromTimeSetListener(fromTimeSetListener);
            dialog.setmToTimeSetListener(toTimeSetListener);
            dialog.show(getFragmentManager(),"");
            return dialog;
        }
    }

}
