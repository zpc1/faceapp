package com.deeocare.deepcare.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.deeocare.deepcare.AddKeHuActivity;
import com.deeocare.deepcare.KeHu;
import com.deeocare.deepcare.KeHuDBHelper;
import com.deeocare.deepcare.KeHuDao;
import com.deeocare.deepcare.KeHuListActivity;
import com.deeocare.deepcare.R;
import com.deeocare.deepcare.ShowKeHuActivity;
import com.deeocare.deepcare.TableContanst;
import com.deeocare.deepcare.bean.HuiYuan;
import com.deeocare.deepcare.db.HuiYuanDBHelper;
import com.deeocare.deepcare.db.HuiYuanDao;

public class HuiYuanActivity extends Activity implements View.OnClickListener,AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private static final String TAG = "HuiYuanActivity";
    private ListView listView;
    private HuiYuan huiYuan;
    private HuiYuanDao dao;
    private Cursor cursor;
    private Button btn_search;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hui_yuan);

        btn_search = (Button) findViewById(R.id.btn_search);
        btn_search.setOnClickListener(this);
        listView = findViewById(R.id.huiyuan_list);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        listView.setOnCreateContextMenuListener(this);

        huiYuan = new HuiYuan();
        dao = new HuiYuanDao(new HuiYuanDBHelper(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
        load();
    }

    // 创建菜单
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this); //getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    // 对菜单中的按钮添加响应时间
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int item_id = item.getItemId();
        huiYuan = (HuiYuan) listView.getTag();
        Log.v(TAG, "TestSQLite++++student+" + listView.getTag() + "");
        final long student_id = huiYuan.getId();
        Intent intent = new Intent();
        Log.v(TAG, "TestSQLite+++++++id"+student_id);
        switch (item_id) {
            /* 添加
            case R.id.add:
                startActivity(new Intent(this, AddStudentActivity.class));
                break;*/
            // 删除
            case R.id.delete:
                deleteHuiYuanInformation(student_id);
                break;
            case R.id.look:
                // 查看学生信息
//                Log.v(TAG, "TestSQLite+++++++look"+huiYuan+"");
                intent.putExtra("student", huiYuan);
                intent.setClass(this, ShowHuiYuanActivity.class);
                this.startActivity(intent);
                break;
            case R.id.write:
                 //修改会员信息
                intent.putExtra("student", huiYuan);
                intent.setClass(this, AddHuiYuanActivity.class);
                this.startActivity(intent);
                break;
            default:
                break;
        }
        return super.onContextItemSelected(item);
    }

    public void load() {
        HuiYuanDBHelper huiyuanDBHelper = new HuiYuanDBHelper(
                this);
        SQLiteDatabase database = huiyuanDBHelper.getWritableDatabase();
        cursor = database.query(TableContanst.STUDENT_TABLE, null, null, null,
                null, null, TableContanst.StudentColumns.MODIFY_TIME + " desc");
        startManagingCursor(cursor);
        adapter = new SimpleCursorAdapter(this, R.layout.kehu_list_item,
                cursor, new String[] { TableContanst.StudentColumns.ID,
                TableContanst.StudentColumns.NAME,
                TableContanst.StudentColumns.AGE,
                TableContanst.StudentColumns.SEX,
                TableContanst.StudentColumns.LIKES,
                TableContanst.StudentColumns.PHONE_NUMBER,
                TableContanst.StudentColumns.TRAIN_DATE }, new int[] {
                R.id.tv_stu_id, R.id.tv_stu_name, R.id.tv_stu_age,
                R.id.tv_stu_sex, R.id.tv_stu_likes, R.id.tv_stu_phone,
                R.id.tv_stu_traindate },0);
        listView.setAdapter(adapter);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
        huiYuan = dao.getHuiYuanFromView(view, id);
        Log.e(TAG, "student*****" + dao.getHuiYuanFromView(view, id));
        Intent intent = new Intent();
        intent.putExtra("student", huiYuan);
        intent.setClass(this, ShowHuiYuanActivity.class);
        this.startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long id) {
        Log.e(TAG, "is show " + view.findViewById(R.id.cb_box).getVisibility());
        HuiYuan huiyuan = (HuiYuan) dao.getHuiYuanFromView(view, id);
        listView.setTag(huiyuan);
        registerForContextMenu(listView);
        return false;
    }


    // 自定义一个利用对话框形式进行数据的删除

    private void deleteHuiYuanInformation(final long delete_id) {
        // 利用对话框的形式删除数据
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("学员信息删除")
                .setMessage("确定删除所选记录?")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int raws = dao.deleteStudentById(delete_id);

//                        isDeleteList = !isDeleteList;
                        load();
                        if (raws > 0) {
                            Toast.makeText(HuiYuanActivity.this, "删除成功!",
                                    Toast.LENGTH_LONG).show();
                        } else
                            Toast.makeText(HuiYuanActivity.this, "删除失败!",
                                    Toast.LENGTH_LONG).show();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void onClick(View view) {
        if (view == btn_search){

            startActivity(new Intent(this, HuiYuanSearchActivity.class));
        }
    }
}
