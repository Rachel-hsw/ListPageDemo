package com.example.zhenghangxia.listpagedemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.zhenghangxia.listpagedemo.adapter.ListViewAdapter;
import com.example.zhenghangxia.listpagedemo.listener.MyOnScrollListener;
import com.example.zhenghangxia.listpagedemo.bean.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhenghangxia on 17-5-3.
 */

public class LoadMoreActivity extends AppCompatActivity implements MyOnScrollListener.OnLoadDataListener {

    private List<Student> mData = new ArrayList<>();
    private ListViewAdapter mAdapter;
    private ListView mListView;
    private View mFooterView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();
        showData(mData);

        setListener();

    }

    private void setListener() {

        MyOnScrollListener onScrollListener = new MyOnScrollListener(mFooterView, mData);

        //设置接口回调
        onScrollListener.setOnLoadDataListener(this);

        mListView.setOnScrollListener(onScrollListener);

    }

    private void showData(List<Student> data) {

        if (mAdapter == null) {

            mListView = (ListView) findViewById(R.id.mListView);

            mFooterView = LayoutInflater.from(this).inflate(R.layout.footer_item,null);
            mFooterView.setVisibility(View.GONE);

            mListView.addFooterView(mFooterView);

            mAdapter = new ListViewAdapter(this, data);

            mListView.setAdapter(mAdapter);

        } else {

            mData = data;

            mAdapter.notifyDataSetChanged();

        }

    }

    private void getData() {

        mData = new ArrayList<>();

        Student stu = null;
        for (int i = 0; i < 10; i++) {
            stu = new Student();
            stu.setName("姓名" + i);
            stu.setSex(i % 2 == 0 ? "男" : "女");
            mData.add(stu);
        }

    }

    @Override
    public void onloadData(List<Student> data) {
        showData(data);
    }
}
