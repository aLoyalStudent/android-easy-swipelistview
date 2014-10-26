package com.swipelistview.example.dismiss;

import android.app.Activity;
import android.os.Bundle;

import com.vincentbrison.openlibraries.android.swipelistview.BaseSwipeListViewListener;
import com.vincentbrison.openlibraries.android.swipelistview.SwipeListView;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity implements MyAdapter.MyAdapterCallbacks{

    private SwipeListView mListView;
    private MyAdapter mAdapter;
    private List<String> mStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_activity);

        mListView = (SwipeListView) findViewById(R.id.my_activity_my_listview);
        mListView.setSwipeOpenOnLongPress(true);
        mListView.setSwipeCloseAllItemsWhenMoveList(true);
        mListView.setOffsetRight(300);

        mStrings = new ArrayList<String>();

        for (int i = 0; i < 50; i++) {
            mStrings.add("Entry number " + i);
        }

        mAdapter = new MyAdapter(mStrings, this, this, mListView);
        mListView.setAdapter(mAdapter);

        mListView.setSwipeListViewListener(new BaseSwipeListViewListener() {
            @Override
            public void onDismiss(int[] reverseSortedPositions) {
                super.onDismiss(reverseSortedPositions);
                for (int i = 0; i < reverseSortedPositions.length; i++) {
                    mStrings.remove(reverseSortedPositions[0]);
                }

                mAdapter.notifyDataSetChanged();
            }
        });



    }

    @Override
    public void onClickDelete(int i) {
        mListView.dismiss(i);
    }
}