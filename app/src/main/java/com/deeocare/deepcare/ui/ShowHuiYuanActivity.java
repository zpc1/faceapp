package com.deeocare.deepcare.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.deeocare.deepcare.KeHu;
import com.deeocare.deepcare.R;
import com.deeocare.deepcare.TableContanst;
import com.deeocare.deepcare.bean.HuiYuan;

import static com.deeocare.deepcare.R.id.huiyuan_list;
import static com.deeocare.deepcare.R.id.tv_name;

public class ShowHuiYuanActivity extends AppCompatActivity {
    private TextView tv_name, tv_birthday, tv_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_hui_yuan);

        Intent intent = getIntent();
        HuiYuan huiYuan = (HuiYuan) intent.getSerializableExtra(TableContanst.STUDENT_TABLE);

        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_birthday = (TextView) findViewById(R.id.tv_birthday);
        tv_phone = (TextView) findViewById(R.id.tv_phone);

        tv_name.setText(huiYuan.getName());
        tv_birthday.setText(huiYuan.getTrainDate());
        tv_phone.setText(huiYuan.getPhoneNumber());
    }
}
