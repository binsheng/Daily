package com.dev.bins.datepicker;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

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

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//        View view = inflater.inflate(R.layout.dialog_time_picker, null);
//        mTabLayout = (TabLayout) view.findViewById(R.id.tablayout);
//        mViewPager = (ViewPager) view.findViewById(R.id.vp);
//        mFragments.add(TimePickerFragment.newInstance());
//        mFragments.add(TimePickerFragment.newInstance());
//        mAdapter = new Adapter(getChildFragmentManager());
//        mViewPager.setAdapter(mAdapter);
//        mTabLayout.setupWithViewPager(mViewPager);
//        builder.setView(view);
//        return builder.create();
//    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
//        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        float height = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 450f, getResources().getDisplayMetrics());
//        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        getDialog().getWindow().setAttributes(params);
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Window window = getDialog().getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.setGravity(Gravity.CENTER);
        return inflater.inflate(R.layout.dialog_time_picker, null);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mTabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        mViewPager = (ViewPager) view.findViewById(R.id.vp);
        mFragments.add(TimePickerFragment.newInstance());
        mFragments.add(TimePickerFragment.newInstance());
        mAdapter = new Adapter(getChildFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    class Adapter extends FragmentStatePagerAdapter {


        int titles[] = {R.string.from,R.string.to};

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
