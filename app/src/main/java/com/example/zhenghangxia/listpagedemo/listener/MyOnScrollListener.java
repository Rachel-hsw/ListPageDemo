package com.example.zhenghangxia.listpagedemo.listener;

import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;

import com.example.zhenghangxia.listpagedemo.bean.Student;

import java.util.List;

/**
 * Created by zhenghangxia on 17-5-3.
 */
public class MyOnScrollListener implements AbsListView.OnScrollListener {

    private final List<Student> mData;
    private final View mFooterView;
    private OnLoadDataListener listener;
    private int lastItem;
    private int totalItemCount;
    private boolean isLoading;

    public MyOnScrollListener(View mFooterView, List<Student> mData) {
        this.mFooterView = mFooterView;
        this.mData = mData;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (!isLoading && lastItem == totalItemCount && scrollState == SCROLL_STATE_IDLE) {
            mFooterView.setVisibility(View.VISIBLE);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (listener != null) {
                        //开始加载更多数据
                        loadMoreData();
                        //回调设置ListView的数据
                        listener.onloadData(mData);
                        //加载完成后操作什么
                        loadComplete();
                    }
                }
            },2000);
        }
    }

    private void loadComplete() {
        isLoading = false;
        mFooterView.setVisibility(View.GONE);
    }

    private void loadMoreData() {

        isLoading = true;
        Student stu = null;
        for (int i = 0; i < 3; i++) {
            stu = new Student();
            stu.setName("新名字" + i);
            stu.setSex("新性别" + i);
            mData.add(stu);
        }

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        lastItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }

    public void setOnLoadDataListener(OnLoadDataListener listener) {
        this.listener = listener;
    }

    public interface OnLoadDataListener {
        void onloadData(List<Student> data);
    }
}
