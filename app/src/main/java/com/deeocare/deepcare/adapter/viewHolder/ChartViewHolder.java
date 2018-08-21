package com.deeocare.deepcare.adapter.viewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.deeocare.deepcare.R;

public class ChartViewHolder extends RecyclerView.ViewHolder {
    public TextView tv_class, tv_name, tv_add, tv_desc, tv_num, tv_price;
    public ImageView iv_del;
    public View divide;

    public ChartViewHolder(@NonNull View itemView, int type) {
        super(itemView);
        tv_class = (TextView)itemView.findViewById(R.id.tv_chart_class);
        tv_name = (TextView)itemView.findViewById(R.id.tv_chart_service_name);
        tv_add = (TextView)itemView.findViewById(R.id.chart_service_num_add);
        tv_desc = (TextView)itemView.findViewById(R.id.chart_service_num_desc);
        tv_num = (TextView)itemView.findViewById(R.id.chart_service_num);
        tv_price = (TextView)itemView.findViewById(R.id.tv_chart_service_price);
        iv_del = (ImageView) itemView.findViewById(R.id.chart_service_del);
        divide = itemView.findViewById(R.id.chart_dvide);
        if (type == 222){
            tv_class.setVisibility(View.GONE);
            divide.setVisibility(View.GONE);
        }
    }
}
