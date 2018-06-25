package com.deeocare.deepcare.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.deeocare.deepcare.KeHu;
import com.deeocare.deepcare.KeHuDBHelper;
import com.deeocare.deepcare.KeHuDao;
import com.deeocare.deepcare.R;
import com.deeocare.deepcare.TableContanst;
import com.deeocare.deepcare.bean.HuiYuan;
import com.deeocare.deepcare.db.HuiYuanDBHelper;
import com.deeocare.deepcare.db.HuiYuanDao;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddHuiYuanActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "AddHuiYuanActivity";
    private EditText nameText,ageText,phoneText,dataText;
    private TextView idText;
    private RadioButton button1,button2;
    private RadioGroup group;
    private Button save_btn,reset_btn;
    private HuiYuanDao dao;
    private boolean isAdd = true;
    private Long huiyuan_id;
    private int year,month,day;
    private Calendar cal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hui_yuan);

        initView();
    }

    private void initView(){
        idText = (TextView) findViewById(R.id.tv_stu_id);
        nameText = (EditText) findViewById(R.id.et_name);
        ageText = (EditText) findViewById(R.id.et_age);
        button1 = (RadioButton) findViewById(R.id.rb_sex_female);
        button2 = (RadioButton) findViewById(R.id.rb_sex_male);
        phoneText = (EditText) findViewById(R.id.et_phone);
        dataText = (EditText) findViewById(R.id.et_traindate);
        group = (RadioGroup) findViewById(R.id.rg_sex);
        save_btn = (Button) findViewById(R.id.btn_save);
        reset_btn = (Button) findViewById(R.id.btn_clear);
        dao = new HuiYuanDao(new HuiYuanDBHelper(this)); // 设置监听 78
        save_btn.setOnClickListener(this);
        reset_btn.setOnClickListener(this);
        dataText.setOnClickListener(this);
        checkIsAddStudent();
    }

    // 检查此时Activity用于添加学员信息还是编辑信息
    private void checkIsAddStudent() {
        Intent intent = getIntent();
        Serializable serial = intent.getSerializableExtra(TableContanst.STUDENT_TABLE);
        if (serial == null) {
            isAdd = true;
            dataText.setText(getCurrentDate());
        } else {
            isAdd = false;
            HuiYuan s = (HuiYuan) serial;
            showEditUI(s);
        }
    }

    @Override
    public void onClick(View v) {
        // 收集数据
        if (v== save_btn) {
            if (!checkUIInput()) {// 界面输入验证
                return;
            }
            HuiYuan huiyuan = getHuiYuanFromUI();
            if (isAdd) {
                long id = dao.addHuiYUan(huiyuan);
                dao.closeDB();
                if (id > 0) {
                    Toast.makeText(this, "保存成功， ID=" + id,Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "保存失败，请重新输入！", Toast.LENGTH_SHORT).show();
                }
            } else if (!isAdd) {
                long id = dao.addHuiYUan(huiyuan);
                dao.closeDB();
                if (id > 0) {
                    Toast.makeText(this, "更新成功",Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Log.d(TAG, "onClick: updata fails "+id);
                    Toast.makeText(this, "更新失败，请重新输入！",Toast.LENGTH_SHORT).show();
                }
            }
        } else if (v == reset_btn) {
            clearUIData();
        } else if (v == dataText) {
            getDate();
            DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    dataText.setText(i+"-"+(++i1)+"-"+i2);      //将选择的日期显示到TextView中,因为之前获取month直接使用，所以不需要+1，这个地方需要显示，所以+1
                }
            };
            DatePickerDialog dialog=new DatePickerDialog(this, 0,listener,year,month,day);//后边三个参数为显示dialog时默认的日期，月份从0开始，0-11对应1-12个月
            dialog.show();
        }
    }


    //验证用户是否按要求输入了数据
    private boolean checkUIInput() { // name, age, sex
        String name = nameText.getText().toString();
        String age = ageText.getText().toString();
        int id = group.getCheckedRadioButtonId();
        String message = null;
        View invadView = null;
        if (name.trim().length() == 0) {
            message = "请输入姓名！";
            invadView = nameText;
        } else if (age.trim().length() == 0) {
            message = "请输入年龄！";
            invadView = ageText;
        } else if (id == -1) {
            message = "请选择性别！";
        }
        if (message != null) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            if (invadView != null)
                invadView.requestFocus();
            return false;
        }
        return true;
    }


    //获取当前日期
    private void getDate() {
        cal= Calendar.getInstance();
        year=cal.get(Calendar.YEAR);       //获取年月日时分秒
//        Log.i("wxy","year"+year);
        month=cal.get(Calendar.MONTH);   //获取到的月份是从0开始计数
        day=cal.get(Calendar.DAY_OF_MONTH);
    }
    //      * 得到当前的日期
    private String getCurrentDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }

    //      * 得到当前的日期时间
    private String getCurrentDateTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(new Date());
    }


    //       清空界面的数据176
    private void clearUIData() {
        nameText.setText("");
        ageText.setText("");
        phoneText.setText("");
        dataText.setText("");
        group.clearCheck();
    }

    //      收集界面输入的数据，并将封装成huiyuan对象
    private HuiYuan getHuiYuanFromUI() {
        String name = nameText.getText().toString();
        int age = Integer.parseInt(ageText.getText().toString());
        String sex = ((RadioButton) findViewById(group
                .getCheckedRadioButtonId())).getText().toString();
        String likes = "";
        String trainDate = dataText.getText().toString();
        String phoneNumber = phoneText.getText().toString();
        String modifyDateTime = getCurrentDateTime();
        HuiYuan s=new HuiYuan(name, age, sex, phoneNumber, trainDate,
                modifyDateTime);
        if (!isAdd) {
            s.setId(Integer.parseInt(idText.getText().toString()));
            dao.deleteStudentById(huiyuan_id);
        }
        return s;
    }

    //显示更新的UI
    private void showEditUI(HuiYuan huiYuan) {
        // 先将Student携带的数据还原到student的每一个属性中去
        huiyuan_id = huiYuan.getId();
        String name = huiYuan.getName();
        int age = huiYuan.getAge();
        String phone = huiYuan.getPhoneNumber();
        String data = huiYuan.getTrainDate();
        String sex = huiYuan.getSex();
        if (sex.toString().equals("男")) {
            button2.setChecked(true);
        } else if (sex.toString().equals("女")) {
            button1.setChecked(true);
        }

        // 还原数据
        idText.setText(huiyuan_id + "");
        nameText.setText(name + "");
        ageText.setText(age + "");
        phoneText.setText(phone + "");
        dataText.setText(data + "");
        setTitle("学员信息更新");
        save_btn.setText("更新");
    }
}
