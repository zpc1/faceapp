package com.deeocare.deepcare.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.deeocare.deepcare.R;
import com.deeocare.deepcare.adapter.viewHolder.ClassFyViewHolder;
import com.deeocare.deepcare.adapter.viewHolder.ProductViewHolder;
import com.deeocare.deepcare.adapter.viewHolder.ServiceViewHolder;
import com.deeocare.deepcare.bean.BaseBean;
import com.deeocare.deepcare.bean.ClassfyBean;
import com.deeocare.deepcare.bean.ProductBean;
import com.deeocare.deepcare.bean.ServiceBean;

import java.util.ArrayList;
import java.util.List;

public class Myadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<BaseBean> mDatas;
    private static final String TAG = "Myadapter";
    private ServiceOnClickListerner serviceOnClickListerner;

    public Myadapter(Context context, List<BaseBean> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    public void setServiceAddListener(ServiceOnClickListerner listener){
        this.serviceOnClickListerner = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case BaseBean.TYPE_CLASS:
                View view2 = inflater.inflate(R.layout.item_classfy, viewGroup, false);
                holder = new ClassFyViewHolder(view2) ;
                break;
            case BaseBean.TYPE_SERVICE:
                View view = inflater.inflate(R.layout.item_service, viewGroup, false);
                holder = new ServiceViewHolder(view);
                break;
            case BaseBean.TYPE_CHILD:
                View view1 = inflater.inflate(R.layout.item_product, viewGroup, false);
                holder = new ProductViewHolder(view1);
                break;

        }
        return holder;
    }

    private SparseArray<List<BaseBean>> saveSparse = new SparseArray<>();
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, final int i) {
        if (holder instanceof ServiceViewHolder) {
            final ServiceViewHolder viewHolder = (ServiceViewHolder) holder;
            viewHolder.bindData((ServiceBean) mDatas.get(i));
            viewHolder.tv_type.setText(mDatas.get(i).getName());
            viewHolder.tv_type.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int size = getNum(i);
                    show(i, size);
                    notifyDataSetChanged();
                }
            });
            viewHolder.iv_shop_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                    Toast.makeText(mContext, "点击了添加商品", Toast.LENGTH_SHORT).show();
                    if (serviceOnClickListerner != null){
                        serviceOnClickListerner.onServiceAdd(view,i, mDatas.get(i));
                    }
                }
            });
        }else if (holder instanceof ProductViewHolder){
            ProductViewHolder holder1 = (ProductViewHolder) holder;
            holder1.bindData((ProductBean) mDatas.get(i));
            holder1.tv_product.setText(mDatas.get(i).getName());
        }else if (holder instanceof ClassFyViewHolder){
            final ClassFyViewHolder holder2 = (ClassFyViewHolder) holder;
            holder2.bindData((ClassfyBean) mDatas.get(i));
            holder2.tv_classfy.setText(mDatas.get(i).getName());
            holder2.tv_classfy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int  size = getNum(i);
                    if (show(i, size)){
                        holder2.iv_show.setImageResource(R.drawable.ic_find_previous_holo_light);
                    }else {
                        holder2.iv_show.setImageResource(R.drawable.ic_find_next_holo_light);
                    }
                    notifyDataSetChanged();
                }
            });
        }
    }

    /**
     * 获取被点击列表的孩子数
     * @param i 点击位置
     * @return 需要折叠的数目
     */
    private int getNum(int i){
        BaseBean bean = mDatas.get(i);
        int totalcnt = 0;
        if (bean.getType()== BaseBean.TYPE_CLASS){
            ClassfyBean beans = (ClassfyBean) bean;
            if (beans.getServiceBeans() == null) {
                Toast.makeText(mContext, "产品列表为空", Toast.LENGTH_SHORT).show();
                return totalcnt;
            }
            for (BaseBean item : beans.getServiceBeans()){
                totalcnt++;
                if (item.getType() == BaseBean.TYPE_SERVICE && item.isShow()){
                    ServiceBean sev = (ServiceBean) item;
                    if (sev.getProducts()!=null){
                        totalcnt+=sev.getProducts().size();
                    }
                }
            }
        }else if (bean.getType() == BaseBean.TYPE_SERVICE){
            ServiceBean sev = (ServiceBean) bean;
            if (sev.getProducts()==null){
                Toast.makeText(mContext, "服务列表为空", Toast.LENGTH_SHORT).show();
                return totalcnt;
            }
            totalcnt = sev.getProducts().size();
        }
        return totalcnt;
    }

    /**
     * 折叠或者展开分组
     * @param i 点击位置
     * @param size 需要折叠的子view数
     */
    private boolean show(int i , int size){
        boolean isShow ;
        int itemId = mDatas.get(i).getItemId();
        if (mDatas.get(i).isShow()) {
            mDatas.get(i).setShow(false);
            List<BaseBean> tmp = new ArrayList<>();
            int rPosition;
            for (int j = 0; j < size; j++) {
                rPosition = i + 1;
                tmp.add(mDatas.get(rPosition));
                mDatas.remove(rPosition);
            }
            saveSparse.put(itemId, tmp);
            isShow = false;
        }else {
            mDatas.get(i).setShow(true);
            List<BaseBean> beans = saveSparse.get(itemId);
            int tmp = 0;
            for (int pos = i+1; pos < (beans.size()+i+1); pos++){
                mDatas.add(pos,beans.get(tmp));
                tmp++;
            }
            isShow = true;
        }
        return isShow;
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).getType();
    }
}
