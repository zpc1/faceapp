package com.deeocare.deepcare.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deeocare.deepcare.R;
import com.deeocare.deepcare.adapter.viewHolder.ChartViewHolder;
import com.deeocare.deepcare.bean.ServiceBean;

import java.util.List;

public class ChartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "ChartAdapter";
    private final Context mContext;
    private final List<ServiceBean> mDatas;
    private MyClickListener listener;
    private int itemType ;

    public ChartAdapter(Context context, List<ServiceBean> data, MyClickListener listener) {
        this.mContext = context;
        this.mDatas = data;
//        this.listener = new Listener();
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        RecyclerView.ViewHolder holder = null;
        View view = inflater.inflate(R.layout.item_chart, viewGroup, false);
        itemType = type;
        holder = new ChartViewHolder(view, type);
        return holder;
    }

    private class myholder extends RecyclerView.ViewHolder{


        public myholder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int position) {
        ChartViewHolder viewHolder = (ChartViewHolder) holder;
        ServiceBean bean = mDatas.get(position);
        viewHolder.tv_class.setText(bean.getClassname());
        viewHolder.tv_num.setText(bean.getShop_num()+"");
        viewHolder.tv_price.setText(bean.getPrice()+"");
        viewHolder.tv_name.setText(bean.getClassname()+":"+bean.getName());
        viewHolder.tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!= null){
                    listener.OnChartAddClicked(mDatas.get(position),position);
                }
            }
        });
        viewHolder.tv_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!= null){
                    listener.OnChartDescClicked(mDatas.get(position),position);
                }
            }
        });
        viewHolder.iv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!= null){
                    listener.OnChartDelClicked(holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return 111;//显示头
        }
        if (mDatas.get(position).getClassname().equalsIgnoreCase(mDatas.get(position-1).getClassname())){
            return 222;//不显示头
        }
        return 111;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    public void updateData(int num, int position){
        mDatas.get(position).setShop_num(num);
        notifyItemChanged(position);
    }

    public void deleteData(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    public double getTotalPrice(List<ServiceBean> data){
        List<ServiceBean> datas = null;
        if (data == null) {
            datas = mDatas;
        }else {
            datas = data;
        }
        double price = 0;
        for (ServiceBean bean: datas){
            int num = bean.getShop_num();
            double p = bean.getPrice();
            price+= p*num;
        }
        return price;
    }

//    private class Listener implements MyClickListener{
//
//        @Override
//        public void OnChartAddClicked(ServiceBean bean,int posion) {
//            int num = bean.getShop_num();
//            num++;
//            mDatas.get(posion).setShop_num(num);
//            notifyItemChanged(posion);
//        }
//
//        @Override
//        public void OnChartDescClicked(ServiceBean bean,int posion) {
//            int num = bean.getShop_num();
//            if (num > 1) {
//                num--;
//                mDatas.get(posion).setShop_num(num);
//                notifyItemChanged(posion);
//            }else {
//                Toast.makeText(mContext, "不能再减了", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        @Override
//        public void OnChartDelClicked(int posion) {
////            Toast.makeText(mContext, "删除", Toast.LENGTH_SHORT).show();
//            mDatas.remove(posion);
//            notifyItemRemoved(posion);
//        }
//    }



//    public interface MyClickListener extends com.example.deepcare.okhttptest.adapter.MyClickListener {
//    }
}
