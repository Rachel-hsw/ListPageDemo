package com.example.recyclerpage2demo.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.recyclerpage2demo.R;

/**
 * Created by zhenghangxia on 17-5-3.
 *
 *  设置滚动监听事件
 *      1 获取当前显示总条数，最后一条数据的位置
 *      2 判断当前是否是刷新数据加载数据状态，
 *          是：当监听器不为空 调用监听方法加载数据  并设置加载状态
 *
 *   页面加载与显示
 *      根据主程序传递的data值进行判断加载 content 部分还是 footer 部分
 *
 */

public class MyAdapter extends BaseAdapter {

    private static final int VIEW_ITEM = 0;
    private static final int VIEW_PROG = 1;
    private final Context mContext;
    private final RecyclerView mRecyclerView;

    private final LayoutInflater inflater;
    private int totalItemCount;
    private int lastVisibleItemPosition;
    //当前滚动的position下面最小的items的临界值
    private int visibleThreshold = 5;


    public MyAdapter(Context context, RecyclerView recyclerView) {
        mContext = context;
        inflater = LayoutInflater.from(context);
        mRecyclerView = recyclerView;

        if (mRecyclerView.getLayoutManager() instanceof LinearLayoutManager) {
            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    totalItemCount = linearLayoutManager.getItemCount();
                    lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();

                    Log.d("test", "totalItemCount =" + totalItemCount + "-----" + "lastVisibleItemPosition =" + lastVisibleItemPosition);

                    if (!isLoading && totalItemCount <= (lastVisibleItemPosition + visibleThreshold)) {
                        //此时是刷新状态
                        if (mMoreDataListener != null)
                            mMoreDataListener.loadMoreData();
                        isLoading = true;
                    }
                }
            });
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == VIEW_ITEM) {
            holder = new MyViewHolder(inflater.inflate(R.layout.item_view, parent, false));
        } else {
            holder = new MyProgressViewHolder(inflater.inflate(R.layout.item_footer, parent, false));
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            if (((MyViewHolder) holder).tv_name != null)
                ((MyViewHolder) holder).tv_name.setText(mData.get(position));
        } else if (holder instanceof MyProgressViewHolder) {
            if (((MyProgressViewHolder) holder).pb != null)
                ((MyProgressViewHolder) holder).pb.setIndeterminate(true);
        }
    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }


    //根据不同的数据返回不同的viewType
    @Override
    public int getItemViewType(int position) {
        return mData.get(position) != null ? VIEW_ITEM : VIEW_PROG;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnitemClickListener != null)
                        mOnitemClickListener.onClick(v);
                }
            });
        }

    }

    public class MyProgressViewHolder extends RecyclerView.ViewHolder {

        private final ProgressBar pb;

        public MyProgressViewHolder(View itemView) {
            super(itemView);
            pb = (ProgressBar) itemView.findViewById(R.id.pb);
        }

    }

    private RecyclerOnItemClickListener mOnitemClickListener;
    private LoadMoreDataListener mMoreDataListener;

    //加载更多监听方法
    @Override
    public void setOnMoreDataLoadListener(LoadMoreDataListener onMoreDataLoadListener) {
        mMoreDataListener = onMoreDataLoadListener;
    }

    //点击事件监听方法
    @Override
    public void setOnItemClickListener(RecyclerOnItemClickListener onItemClickListener) {
        mOnitemClickListener = onItemClickListener;
    }

}
