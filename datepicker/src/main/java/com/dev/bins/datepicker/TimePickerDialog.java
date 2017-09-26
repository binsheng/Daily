package com.dev.bins.datepicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bin on 25/09/2017.
 */

public class TimePickerDialog extends AppCompatActivity{


    TabLayout mTabLayout;
    ViewPager mViewPager;
    Adapter mAdapter;

    List<Fragment> mFragments = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_time_picker);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        TimePickerFragment fromFragment = new TimePickerFragment();
        TimePickerFragment toFragment = new TimePickerFragment();
        mFragments.add(fromFragment);
        mFragments.add(toFragment);
        mAdapter = new Adapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_time_picker,container,false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mTabLayout =  view.findViewById(R.id.tablayout);
        mViewPager = view.findViewById(R.id.viewPager);
        TimePickerFragment fromFragment = new TimePickerFragment();
        TimePickerFragment toFragment = new TimePickerFragment();
        mFragments.add(fromFragment);
        mFragments.add(toFragment);
        mAdapter = new Adapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
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



}
