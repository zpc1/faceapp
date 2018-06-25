package com.deeocare.deepcare.ui;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.deeocare.deepcare.KeHuDBHelper;
import com.deeocare.deepcare.KeHuDao;
import com.deeocare.deepcare.R;
import com.deeocare.deepcare.TableContanst;
import com.deeocare.deepcare.db.HuiYuanDBHelper;
import com.deeocare.deepcare.db.HuiYuanDao;

public class HuiYuanSearchActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nameText;
    private Button button;
    private Button reButton;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private ListView listView;
    private HuiYuanDao dao;
    private Button returnButton;
    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hui_yuan_search);

        nameText = (EditText) findViewById(R.id.et_srarch);
        layout=(LinearLayout) findViewById(R.id.linersearch);
        button = (Button) findViewById(R.id.bn_sure_search);
        reButton = (Button) findViewById(R.id.bn_return);
        listView = (ListView) findViewById(R.id.searchListView);
        returnButton = (Button) findViewById(R.id.return_id);
        dao = new HuiYuanDao(new HuiYuanDBHelper(this));


        reButton.setOnClickListener(this);
        returnButton.setOnClickListener(this);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == button) {
            reButton.setVisibility(View.GONE);
            button.setVisibility(View.GONE);
            nameText.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
            String name = nameText.getText().toString();
            cursor = dao.findHuiYuan(name);
            if (!cursor.moveToFirst()) {
                Toast.makeText(this, "没有所查学员信息！", Toast.LENGTH_SHORT).show();
            } else
                //如果有所查询的信息，则将查询结果显示出来
                adapter = new SimpleCursorAdapter(this, R.layout.find_kehu_list_item,
                        cursor, new String[] { TableContanst.StudentColumns.ID,
                        TableContanst.StudentColumns.NAME,
                        TableContanst.StudentColumns.AGE,
                        TableContanst.StudentColumns.SEX,
                        TableContanst.StudentColumns.LIKES,
                        TableContanst.StudentColumns.PHONE_NUMBER,
                        TableContanst.StudentColumns.TRAIN_DATE },
                        new int[] {
                                R.id.tv_stu_id,
                                R.id.tv_stu_name,
                                R.id.tv_stu_age,
                                R.id.tv_stu_sex,
                                R.id.tv_stu_likes,
                                R.id.tv_stu_phone,
                                R.id.tv_stu_traindate });
            listView.setAdapter(adapter);
        }else if(v==reButton|v==returnButton){
            finish();
        }
    }
}
