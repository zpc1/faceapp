package com.deeocare.deepcare.adapter.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.deeocare.deepcare.R;
import com.deeocare.deepcare.bean.ServiceBean;


public class ServiceViewHolder extends RecyclerView.ViewHolder {
    public TextView tv_type = null;
    public ImageView iv_shop_add ;
    private ServiceBean bean;

    public ServiceViewHolder(View view) {
        super(view);
        tv_type = (TextView) view.findViewById(R.id.tv_type);
        iv_shop_add = (ImageView) view.findViewById(R.id.iv_shop_add);
    }
    public void bindData(ServiceBean bean){
        this.bean = bean;
    }
}
