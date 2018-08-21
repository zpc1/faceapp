package com.deeocare.deepcare.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.deeocare.deepcare.Castant;
import com.deeocare.deepcare.R;
import com.deeocare.deepcare.adapter.Myadapter;
import com.deeocare.deepcare.adapter.ServiceOnClickListerner;
import com.deeocare.deepcare.bean.BaseBean;
import com.deeocare.deepcare.bean.ClassfyBean;
import com.deeocare.deepcare.bean.ProductBean;
import com.deeocare.deepcare.bean.ServiceBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GaiShanActivity extends Activity {
    private static final String TAG = "MainActivity";

    private RecyclerView recyclerView;
    private TextView tv_total_num ;
    private RelativeLayout rl,mainlayout;
    private ImageView shopChartView;

    private int chart_num = 0;
    private List<BaseBean> mDatas = new ArrayList<>();
    private SparseArray<ServiceBean> chart_data = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gai_shan);

        initdata();
        initView();


    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String json = intent.getStringExtra(Castant.CHART_SHOP);
        if(!TextUtils.isEmpty(json)) {
            chart_data.clear();
            List<ServiceBean> beans = new Gson().fromJson(json, new TypeToken<List<ServiceBean>>(){}.getType());
            for (ServiceBean bean: beans){
                chart_num+=bean.getShop_num();
                chart_data.put(bean.getClassId()+bean.getItemId(), bean);
            }
            if (chart_num!=0 && (tv_total_num.getVisibility()!= View.VISIBLE)){
                tv_total_num.setVisibility(View.VISIBLE);
            }
            tv_total_num.setText(chart_num+"");
        }
    }

    private List<ServiceBean> SparseArrayToList(SparseArray<ServiceBean> sparseArray){
        List<ServiceBean> beans = new ArrayList<>();
        for (int i = 0; i < sparseArray.size(); i ++){
            ServiceBean bean = sparseArray.valueAt(i);
            if (bean == null)
                continue;
            beans.add(bean);
        }
        return beans;
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        shopChartView = (ImageView) findViewById(R.id.shopping_cart) ;
        tv_total_num = (TextView) findViewById(R.id.shopping_cart_total_num);
        rl = (RelativeLayout) findViewById(R.id.shopping_cart_layout);
        mainlayout = (RelativeLayout) findViewById(R.id.mainLayout);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<ServiceBean> beans = SparseArrayToList(chart_data);
                if (beans.size() == 0){
                    Toast.makeText(GaiShanActivity.this, "购物车为空", Toast.LENGTH_SHORT).show();
                    return;
                }
//                Toast.makeText(MainActivity.this, "购物车被点击了", Toast.LENGTH_SHORT).show();
                String jstr = new Gson().toJson(beans);
//                Log.e(TAG, jstr);
                Intent intent = new Intent(GaiShanActivity.this, ChartActivity.class);
                intent.putExtra(Castant.CHART_SHOP, jstr);
                startActivity(intent);
                finish();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Myadapter adapter = new Myadapter(this, mDatas);
        adapter.setServiceAddListener(new ServiceOnClickListerner() {
            @Override
            public void onServiceAdd(View view,int posion, BaseBean itemdata) {


                chart_num++;
                //判断是否需要显示购物车数量布局
                if (tv_total_num.getVisibility() == View.GONE){//隐藏状态,判断num是否需要显示
                    if (chart_num != 0){
                        tv_total_num.setVisibility(View.VISIBLE);
                    }
                }else if (chart_num == 0){//已经显示，判断num是否需要隐藏
                    tv_total_num.setVisibility(View.GONE);
                }
                int num = itemdata.getShop_num()+1;
                int id = itemdata.getClassId()+itemdata.getItemId();
                ServiceBean data = chart_data.get(id);
                //判断购物车是否已经有该商品
                if (data == null){
                    //购物车中没有该服务
                    itemdata.setShop_num(num);
                    data = (ServiceBean) itemdata;
                    chart_data.put(id, data);

                }else {
                    data.setShop_num(num);
                    chart_data.put(id,data);
                }
                tv_total_num.setText(chart_num+"");
            }
        });
        recyclerView.setAdapter(adapter);

    }



    private void initdata() {
//        for (ServiceBean beans : getServiceData()){
//            services.add(beans);
//
//            for (ProductBean bean : beans.getProducts()){
//                services.add(bean);
//            }
//        }

        for (ClassfyBean cb : getDatas()){
            mDatas.add(cb);
            if (cb == null|| cb.getServiceBeans()==null)
                continue;
            for (ServiceBean sb : cb.getServiceBeans()){
                sb.setClassname(cb.getName());
                sb.setClassId(cb.getClassId());
                mDatas.add(sb);

                for (ProductBean bean : sb.getProducts()){
                    mDatas.add(bean);
                }
            }
        }
    }

    private List<ClassfyBean> getDatas(){
        List<ClassfyBean> classfyBeans = new ArrayList<>();
        for (int k = 0; k < 2; k++){
            ClassfyBean bean = new ClassfyBean();
            bean.setName("类型"+k);
            bean.setClassId(111*(k+1));
            bean.setShow(true);
            bean.setType(BaseBean.TYPE_CLASS);

            if (k < 2){
                bean.setServiceBeans(getServiceData());
            }
            classfyBeans.add(bean);
        }
        return classfyBeans;
    }

    private List<ServiceBean> getServiceData(){
        List<ServiceBean> serviceBeans = new ArrayList<>();
        Random random = new Random();
        int min = 1;
        int max = 100;
        for (int j = 0; j < 3; j++){
            List<ProductBean> products = new ArrayList<>();

            ServiceBean bean = new ServiceBean();
            bean.setItemId(j);
            bean.setName("服务"+j);
            bean.setShow(true);
            bean.setPrice(random.nextInt(max)%(max-min+1)+min);
            bean.setType(BaseBean.TYPE_SERVICE);

            for (int i = 0; i < 5; i++) {
                ProductBean product = new ProductBean();
                product.setName(bean.getName()+":产品"+i);
                product.setServiceId(i);
                product.setType(BaseBean.TYPE_CHILD);
                products.add(product);
            }

            bean.setProducts(products);
            serviceBeans.add(bean);
        }
        return serviceBeans;
    }
}
