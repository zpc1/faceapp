package com.deeocare.deepcare.adapter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.deeocare.deepcare.R;
import com.deeocare.deepcare.bean.ClassfyBean;

public class ClassFyViewHolder extends RecyclerView.ViewHolder {
    public TextView tv_classfy;
    public ImageView iv_show;
    private ClassfyBean bean;
    public ClassFyViewHolder(View view2) {
        super(view2);
        tv_classfy = (TextView)  view2.findViewById(R.id.tv_classfy);
        iv_show = (ImageView) view2.findViewById(R.id.iv_show);
    }
    public void bindData(ClassfyBean bean){
        this.bean = bean;
    }
}
