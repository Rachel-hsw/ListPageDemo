package com.example.recyclerpagedemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyclerpagedemo.R;

import java.util.List;

/**
 * Created by zhenghangxia on 17-5-3.
 */

public class FooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;
    private final List<String> mList;
    //模拟数据
//    public String [] texts={"java","python","C++","Php",".NET","js","Ruby","Swift","OC","python","C++","Php",".NET","js","Ruby","Swift","OC"};
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private int mBottomCount=1;//底部View个数

    public FooterAdapter(Context mContext, List<String> mList) {
        this.mContext = mContext;
        this.mList = mList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    //内容长度
    public int getContentItemCount(){
        return mList.size();
    }

    //判断当前item类型
    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getContentItemCount();
        if (mBottomCount != 0 && position >=  dataItemCount) {
            //底部View
            return ITEM_TYPE_BOTTOM;
        } else {
            //内容View
            return ITEM_TYPE_CONTENT;
        }
    }

    //判断当前item是否是FooterView
    public boolean isBottomView(int position) {
        return mBottomCount != 0 && position >= getContentItemCount();
    }

    //内容 ViewHolder
    public static class ContentViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ContentViewHolder(View itemView) {
            super(itemView);
            textView=(TextView)itemView.findViewById(R.id.tv_item_text);
        }
    }

    //底部 ViewHolder
    public static class BottomViewHolder extends RecyclerView.ViewHolder {
        public BottomViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE_CONTENT) {
            return new ContentViewHolder(mLayoutInflater.inflate(R.layout.recyclerview_item, parent, false));
        } else if (viewType == ITEM_TYPE_BOTTOM) {
            return new BottomViewHolder(mLayoutInflater.inflate(R.layout.footer, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ContentViewHolder) {
            ((ContentViewHolder) holder).textView.setText(mList.get(position));
        } else if (holder instanceof BottomViewHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return getContentItemCount() + mBottomCount;
    }
}
