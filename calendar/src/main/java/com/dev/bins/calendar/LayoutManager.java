package com.dev.bins.calendar;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import static com.dev.bins.calendar.CalendarAdapter.STATE_OPEN;

/**
 * Created by bin on 25/10/2017.
 */

public class LayoutManager extends RecyclerView.LayoutManager {

    private RecyclerView mRecyclerView;
    private CalendarAdapter mAdapter;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }


    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() == 0) {
            removeAndRecycleAllViews(recycler);
            return;
        }

        final int childCount = getChildCount();
        if (childCount == 0 && state.isPreLayout()) {
            return;
        }
        int width = getWidth();
        int cellHeight = width/7;
        if (mAdapter.getCurrentState() == STATE_OPEN){

        }else {

        }


    }


    @Override
    public boolean canScrollHorizontally() {
        return true;
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }


    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        mRecyclerView = view;
        mAdapter = (CalendarAdapter) mRecyclerView.getAdapter();
    }


    @Override
    public void onDetachedFromWindow(RecyclerView view, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(view, recycler);
        mRecyclerView = null;
        mAdapter = null;
    }




}
