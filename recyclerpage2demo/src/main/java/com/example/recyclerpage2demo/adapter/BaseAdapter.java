package com.example.recyclerpage2demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by zhenghangxia on 17-5-4.
 */

public abstract class BaseAdapter extends RecyclerView.Adapter {

    public List<String> mData;
    public boolean isLoading;

    abstract void setOnMoreDataLoadListener(LoadMoreDataListener onMoreDataLoadListener);

    abstract void setOnItemClickListener(RecyclerOnItemClickListener onItemClickListener);

    public interface LoadMoreDataListener {
        void loadMoreData();
    }

    public interface RecyclerOnItemClickListener {
        void onClick(View view);
    }

    //设置数据的方法
    public void setData(List<String> data) {
        mData = data;
    }

    public void setLoaded() {
        isLoading = false;
    }

}
