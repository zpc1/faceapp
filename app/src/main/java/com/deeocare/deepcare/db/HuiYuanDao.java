package com.deeocare.deepcare.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.deeocare.deepcare.KeHu;
import com.deeocare.deepcare.KeHuDBHelper;
import com.deeocare.deepcare.R;
import com.deeocare.deepcare.TableContanst;
import com.deeocare.deepcare.bean.HuiYuan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2018/6/20.
 */

public class HuiYuanDao {

    private HuiYuanDBHelper dbHelper;
    private Cursor cursor;
    private static final String TAG = "HuiYuanDao";

    public HuiYuanDao(HuiYuanDBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    // 添加一个Student对象数据到数据库表
    public long addHuiYUan(HuiYuan s) {
        ContentValues values = new ContentValues();
        values.put(TableContanst.StudentColumns.NAME, s.getName());
        values.put(TableContanst.StudentColumns.AGE, s.getAge());
        values.put(TableContanst.StudentColumns.SEX, s.getSex());
        values.put(TableContanst.StudentColumns.PHONE_NUMBER, s.getPhoneNumber());
        values.put(TableContanst.StudentColumns.TRAIN_DATE, s.getTrainDate());
        values.put(TableContanst.StudentColumns.MODIFY_TIME, s.getModifyDateTime());
        return dbHelper.getWritableDatabase().insert(TableContanst.STUDENT_TABLE, null, values);
    }

    // 删除一个id所对应的数据库表student的记录
    public int deleteStudentById(long id) {
        return dbHelper.getWritableDatabase().delete(TableContanst.STUDENT_TABLE,
                TableContanst.StudentColumns.ID + "=?", new String[]{id + ""});
    }

    // 更新一个id所对应数据库表student的记录
    public int updateHuiYuan(HuiYuan s) {
        ContentValues values = new ContentValues();
        values.put(TableContanst.StudentColumns.NAME, s.getName());
        values.put(TableContanst.StudentColumns.AGE, s.getAge());
        values.put(TableContanst.StudentColumns.SEX, s.getSex());
        values.put(TableContanst.StudentColumns.PHONE_NUMBER, s.getPhoneNumber());
        values.put(TableContanst.StudentColumns.TRAIN_DATE, s.getTrainDate());
        values.put(TableContanst.StudentColumns.MODIFY_TIME, s.getModifyDateTime());

        Log.d(TAG, "updateHuiYuan: "+s.getId());
//        String updateStr = "update student set age=1 where _id=1";
//        dbHelper.getWritableDatabase().execSQL(updateStr);
//        return 1;
        return dbHelper.getWritableDatabase().update(TableContanst.STUDENT_TABLE, values,
                TableContanst.StudentColumns.ID + "=?", new String[]{s.getId() + ""});
    }

    // 查询所有的记录
    public List<Map<String, Object>> getAllHuiYuan() {
        //modify_time desc
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        Cursor cursor = dbHelper.getWritableDatabase().query(TableContanst.STUDENT_TABLE, null, null, null,
                null, null, TableContanst.StudentColumns.MODIFY_TIME + " desc");
        while (cursor.moveToNext()) {
            Map<String, Object> map = new HashMap<String, Object>(8);
            long id = cursor.getInt(cursor.getColumnIndex(TableContanst.StudentColumns.ID));
            map.put(TableContanst.StudentColumns.ID, id);
            String name = cursor.getString(cursor.getColumnIndex(TableContanst.StudentColumns.NAME));
            map.put(TableContanst.StudentColumns.NAME, name);
            int age = cursor.getInt(cursor.getColumnIndex(TableContanst.StudentColumns.AGE));
            map.put(TableContanst.StudentColumns.AGE, age);
            String sex = cursor.getString(cursor.getColumnIndex(TableContanst.StudentColumns.SEX));
            map.put(TableContanst.StudentColumns.SEX, sex);
            String likes = cursor.getString(cursor.getColumnIndex(TableContanst.StudentColumns.LIKES));
            map.put(TableContanst.StudentColumns.LIKES, likes);
            String phone_number = cursor.getString(cursor.getColumnIndex(TableContanst.StudentColumns.PHONE_NUMBER));
            map.put(TableContanst.StudentColumns.PHONE_NUMBER, phone_number);
            String train_date = cursor.getString(cursor.getColumnIndex(TableContanst.StudentColumns.TRAIN_DATE));
            map.put(TableContanst.StudentColumns.TRAIN_DATE, train_date);
            String modify_time = cursor.getString(cursor.getColumnIndex(TableContanst.StudentColumns.MODIFY_TIME));
            map.put(TableContanst.StudentColumns.MODIFY_TIME, modify_time);
            data.add(map);
        }
        return data;
    }

    //模糊查询一条记录
    public Cursor findHuiYuan(String name) {
        Cursor cursor = dbHelper.getWritableDatabase().query(TableContanst.STUDENT_TABLE, null, "name like ?",
                new String[]{"%" + name + "%"}, null, null, null, null);
        return cursor;
    }

    //按姓名进行排序
    public Cursor sortByName() {
        Cursor cursor = dbHelper.getWritableDatabase().query(TableContanst.STUDENT_TABLE, null, null,
                null, null, null, TableContanst.StudentColumns.NAME);
        return cursor;
    }

    //按入学日期进行排序
    public Cursor sortByTrainDate() {
        Cursor cursor = dbHelper.getWritableDatabase().query(TableContanst.STUDENT_TABLE, null, null,
                null, null, null, TableContanst.StudentColumns.TRAIN_DATE);
        return cursor;
    }

    //按学号进行排序
    public Cursor sortByID() {
        Cursor cursor = dbHelper.getWritableDatabase().query(TableContanst.STUDENT_TABLE, null, null,
                null, null, null, TableContanst.StudentColumns.ID);
        return cursor;
    }

    public void closeDB() {
        dbHelper.close();
    }   //自定义的方法通过View和Id得到一个student对象

    public HuiYuan getHuiYuanFromView(View view, long id) {
        TextView nameView = (TextView) view.findViewById(R.id.tv_stu_name);
        TextView ageView = (TextView) view.findViewById(R.id.tv_stu_age);
        TextView sexView = (TextView) view.findViewById(R.id.tv_stu_sex);
        TextView phoneView = (TextView) view.findViewById(R.id.tv_stu_phone);
        TextView dataView = (TextView) view.findViewById(R.id.tv_stu_traindate);
        String name = nameView.getText().toString();
        int age = Integer.parseInt(ageView.getText().toString());
        String sex = sexView.getText().toString();
        String phone = phoneView.getText().toString();
        String data = dataView.getText().toString();
        HuiYuan huiYuan = new HuiYuan(id, name, age, sex,  phone, data, null);
        return huiYuan;
    }

}
