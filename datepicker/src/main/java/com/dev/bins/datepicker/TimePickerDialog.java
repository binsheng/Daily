package com.dev.bins.datepicker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bin on 25/09/2017.
 */

public class TimePickerDialog extends DialogFragment {


    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private Adapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<>();


    public TimePickerDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Window window = getDialog().getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.setGravity(Gravity.CENTER);
        return inflater.inflate(R.layout.dialog_time_picker, null);
    }


    public void test() {
        Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mTabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        mViewPager = (ViewPager) view.findViewById(R.id.vp);
        TimePickerFragment fromFragment = TimePickerFragment.newInstance();
        fromFragment.setListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {

            }
        });
        fromFragment.setPositiveButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "click", Toast.LENGTH_SHORT).show();
                mViewPager.setCurrentItem(1);
            }
        });
        fromFragment.setNegativeButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        mFragments.add(fromFragment);
        TimePickerFragment toFragment = TimePickerFragment.newInstance();
        toFragment.setPositiveButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        toFragment.setNegativeButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mFragments.add(toFragment);
        mAdapter = new Adapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    class Adapter extends FragmentStatePagerAdapter {


        int titles[] = {R.string.from, R.string.to};

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return getString(titles[position]);
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
