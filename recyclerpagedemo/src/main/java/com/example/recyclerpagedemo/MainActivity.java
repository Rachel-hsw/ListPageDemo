package com.example.recyclerpagedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.recyclerpagedemo.adapter.FooterAdapter;
import com.example.recyclerpagedemo.listener.MyOnScrollListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyOnScrollListener.OnLoadDataListener {

    private RecyclerView mRecyclerView;
    private FooterAdapter mAdapter;
    private List<String> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();
        showData(mData);

        setListener();

    }

    private void setListener() {

        MyOnScrollListener onScrollListener = new MyOnScrollListener(mData);

        //设置接口回调
        onScrollListener.setOnLoadDataListener(this);

        mRecyclerView.addOnScrollListener(onScrollListener);

    }

    private void showData(List<String> data) {

        if (mAdapter == null) {

            mRecyclerView=(RecyclerView)findViewById(R.id.mRecyclerView);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(layoutManager);

            mAdapter = new FooterAdapter(this, mData);

            mRecyclerView.setAdapter(mAdapter);

        } else {

            mData = data;

            mAdapter.notifyDataSetChanged();

        }

    }

    private void getData() {

        mData = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String string = "姓名 " + i;
            mData.add(string);
        }

    }

    @Override
    public void onloadData(List<String> data) {
        showData(mData);
    }
}
