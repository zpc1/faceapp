package com.deeocare.deepcare.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.deeocare.deepcare.Castant;
import com.deeocare.deepcare.MainActivity;
import com.deeocare.deepcare.R;
import com.deeocare.deepcare.adapter.ChartAdapter;
import com.deeocare.deepcare.adapter.MyClickListener;
import com.deeocare.deepcare.bean.ServiceBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends Activity implements MyClickListener{
    private static final String TAG = "ChartActivity";
    Gson gson = new Gson();
    private RecyclerView recyclerView;
    private TextView totalPrice;
    private List<ServiceBean> mDatas = new ArrayList<>();
    private ChartAdapter adapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        initData();

        initView();
    }

    private void initView() {
        totalPrice = (TextView)findViewById(R.id.total_price);
        recyclerView = (RecyclerView) findViewById(R.id.chart_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChartAdapter(this, mDatas, this);
        recyclerView.setAdapter(adapter);
        totalPrice.setText(adapter.getTotalPrice(mDatas)+"");
    }

    private void initData() {
        Intent intent = getIntent();
        String json = intent.getStringExtra(Castant.CHART_SHOP);
        List<ServiceBean> services = gson.fromJson(json, new TypeToken<List<ServiceBean>>(){}.getType());
        for (ServiceBean bean: services){
            Log.e(TAG, gson.toJson(bean));
            mDatas.add(bean);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(Castant.CHART_SHOP,gson.toJson(mDatas));
        startActivity(intent);
        finish();
    }

    @Override
    public void OnChartAddClicked(ServiceBean bean,int posion) {
        int num = bean.getShop_num();
        num++;
        adapter.updateData(num, posion);
        showTotalProce(adapter.getTotalPrice(null)+"");
    }

    @Override
    public void OnChartDescClicked(ServiceBean bean,int posion) {
        int num = bean.getShop_num();
        if (num > 1) {
            num--;
            adapter.updateData(num,posion);
        }else {
            Toast.makeText(this, "不能再减了", Toast.LENGTH_SHORT).show();
        }
        showTotalProce(adapter.getTotalPrice(null)+"");
    }

    @Override
    public void OnChartDelClicked(int posion) {
        adapter.deleteData(posion);
        showTotalProce(adapter.getTotalPrice(null)+"");
    }

    private void showTotalProce(String price){
        totalPrice.setText(price);
    }
}
