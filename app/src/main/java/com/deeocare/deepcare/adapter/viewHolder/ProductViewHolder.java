package com.deeocare.deepcare.adapter.viewHolder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.deeocare.deepcare.R;
import com.deeocare.deepcare.bean.ProductBean;

public class ProductViewHolder extends RecyclerView.ViewHolder {
    public TextView tv_product = null;
    private ProductBean bean;

    public ProductViewHolder(View view1) {
        super(view1);
        tv_product = (TextView) view1.findViewById(R.id.tv_product);
    }

    public void bindData(ProductBean bean){
        this.bean = bean;
    }
}
