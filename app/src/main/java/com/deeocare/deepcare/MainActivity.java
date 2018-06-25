package com.deeocare.deepcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.deeocare.deepcare.ui.AddHuiYuanActivity;
import com.deeocare.deepcare.ui.HuiYuanActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_all,btn_add,btn_fangan,btn_my;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView(){
        btn_all = (Button) findViewById(R.id.bt_all_huiyuan);
        btn_add = (Button) findViewById(R.id.bt_add_huiyuan);
        btn_fangan  = (Button) findViewById(R.id.bt_fangan);
        btn_my = (Button) findViewById(R.id.bt_my);

        btn_all.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_fangan.setOnClickListener(this);
        btn_my.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_all_huiyuan:
                startActivity(new Intent(this, HuiYuanActivity.class));
                break;
            case R.id.bt_add_huiyuan:
                startActivity(new Intent(this, AddHuiYuanActivity.class));
                break;
            case R.id.bt_fangan:
                break;
            case R.id.bt_my:
                break;
        }

    }
}
