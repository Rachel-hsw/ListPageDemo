package com.example.zhenghangxia.listpagedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zhenghangxia.listpagedemo.R;
import com.example.zhenghangxia.listpagedemo.bean.Student;

import java.util.List;

/**
 * Created by zhenghangxia on 17-5-3.
 */

public class ListViewAdapter extends BaseAdapter {

    private final List<Student> mList;
    private final Context mContext;

    public ListViewAdapter(Context mContext, List<Student> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Student stu = mList.get(position);

        ViewHolder viewHolder = null;
        if (convertView == null) {

            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
            viewHolder.mNameText = (TextView) convertView.findViewById(R.id.mTv1);
            viewHolder.mSexText = (TextView) convertView.findViewById(R.id.mTv2);

            convertView.setTag(viewHolder);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();

        }

        if (stu != null) {

            viewHolder.mNameText.setText(stu.getName());
            viewHolder.mSexText.setText(stu.getSex());

        }

        return convertView;
    }

    public class ViewHolder {

        TextView mNameText;
        TextView mSexText;

    }

}
