package com.example.recyclerpage2demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhenghangxia on 17-5-4.
 */

public abstract class BaseAdapter extends RecyclerView.Adapter {

    abstract void setOnMoreDataLoadListener(LoadMoreDataListener onMoreDataLoadListener);

    abstract void setOnItemClickListener(RecyclerOnItemClickListener onItemClickListener);

    public interface LoadMoreDataListener {
        void loadMoreData();
    }

    public interface RecyclerOnItemClickListener {
        void onClick(View view);
    }

}
