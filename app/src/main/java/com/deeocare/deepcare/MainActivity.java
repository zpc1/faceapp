package com.deeocare.deepcare;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.deeocare.deepcare.ui.AddHuiYuanActivity;
import com.deeocare.deepcare.ui.HuiYuanActivity;
import com.deeocare.deepcare.ui.ResultPreviewActivity;

public class MainActivity extends Activity implements View.OnClickListener{
    private Button btn_all,btn_add,btn_fangan,btn_my;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        Toast.makeText(this, getDeviceId()+"", Toast.LENGTH_SHORT).show();


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
                startActivity(new Intent(this, ResultPreviewActivity.class));
                break;
            case R.id.bt_my:
                break;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public  String getDeviceId() {
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "no permission";
        }
        return android.os.Build.getSerial();
    }
}
