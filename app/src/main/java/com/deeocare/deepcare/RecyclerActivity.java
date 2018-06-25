package com.deeocare.deepcare;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;

import java.util.List;

public class RecyclerActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView mRecyclerview;
    private static final String TAG = "TestSQLite";
    private Button addStudent;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;
    private List<Long> list;
    private RelativeLayout relativeLayout;
    private Button searchButton;
    private Button selectButton;
    private Button deleteButton;
    private Button selectAllButton;
    private Button canleButton;
    private LinearLayout layout;
    private KeHuDao dao;
    private KeHu student;
    private Boolean isDeleteList = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

//        initView();
    }

//    private void initView(){
//        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerview);
//
//        student = new KeHu();
//        dao = new KeHuDao(new KeHuDBHelper(this));
//        addStudent = (Button) findViewById(R.id.btn_add_student);
//        searchButton = (Button) findViewById(R.id.bn_search_id);
//        selectButton = (Button) findViewById(R.id.bn_select);
//        deleteButton = (Button) findViewById(R.id.bn_delete);
//        selectAllButton = (Button) findViewById(R.id.bn_selectall);
//        canleButton = (Button) findViewById(R.id.bn_canel);
//        layout = (LinearLayout) findViewById(R.id.showLiner);
//        relativeLayout=(RelativeLayout) findViewById(R.id.RelativeLayout);
//
//        addStudent.setOnClickListener(this);
//        searchButton.setOnClickListener(this);
//        selectButton.setOnClickListener(this);
//        deleteButton.setOnClickListener(this);
//        canleButton.setOnClickListener(this);
//        selectAllButton.setOnClickListener(this);
//
//        listView.setOnItemClickListener(this);
//        listView.setOnItemLongClickListener(this);
//        listView.setOnCreateContextMenuListener(this);
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//    }
//
//    // 自定义一个加载数据库中的全部记录到当前页面的无参方法
//    public void load() {
//        KeHuDBHelper studentDBHelper = new KeHuDBHelper(this);
//        SQLiteDatabase database = studentDBHelper.getWritableDatabase();
//        cursor = database.query(TableContanst.STUDENT_TABLE, null, null, null,
//                null, null, TableContanst.StudentColumns.MODIFY_TIME + " desc");
//        startManagingCursor(cursor);
//        adapter = new SimpleCursorAdapter(this, R.layout.kehu_list_item,
//                cursor, new String[] { TableContanst.StudentColumns.ID,
//                TableContanst.StudentColumns.NAME,
//                TableContanst.StudentColumns.AGE,
//                TableContanst.StudentColumns.SEX,
//                TableContanst.StudentColumns.LIKES,
//                TableContanst.StudentColumns.PHONE_NUMBER,
//                TableContanst.StudentColumns.TRAIN_DATE }, new int[] {
//                R.id.tv_stu_id, R.id.tv_stu_name, R.id.tv_stu_age,
//                R.id.tv_stu_sex, R.id.tv_stu_likes, R.id.tv_stu_phone,
//                R.id.tv_stu_traindate },0);
//        list.setAdapter(adapter);
//
//
//    }
    @Override
    public void onClick(View view) {

    }
}
