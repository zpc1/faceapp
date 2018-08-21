package com.deeocare.deepcare.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.deeocare.deepcare.Contast;
import com.deeocare.deepcare.R;
import com.deeocare.deepcare.adapter.RadarAdapter;
import com.deeocare.deepcare.bean.PingCeSingle;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ResultPreviewActivity extends Activity implements View.OnClickListener {
    private RadarChart mchart;
    private Typeface tf;
    private TextView tv_gaishan;
//    private LinearLayout ll_gaishai_content;// ll ,
//    private RelativeLayout rl_gaishan;
    private ImageView bt_camera, bt_share;
    private String[] mActivities = new String[]{"毛孔", "黑头", "纹理", "黑点",};
    private boolean is_ll_gaishan_show = false;
    private GridView mGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_preview);


        init_view();

    }

    private void init_view() {
        mchart = (RadarChart) findViewById(R.id.radarchart_fuzhi);
//        ll = (LinearLayout) findViewById(R.id.ll_item);
//        ll.setOnClickListener(this);
//        rl_gaishan = findViewById(R.id.rl_gaishan);
//        rl_gaishan.setOnClickListener(this);
        tv_gaishan = findViewById(R.id.tv_gaisan);
        tv_gaishan.setOnClickListener(this);
        bt_camera = (ImageView) findViewById(R.id.iv_camera);
        bt_camera.setOnClickListener(this);
        bt_share = (ImageView) findViewById(R.id.iv_share);
        bt_share.setOnClickListener(this);
//        ll_gaishai_content = (LinearLayout) findViewById(R.id.ll_gaishan_conten);
//        ll_gaishai_content.setVisibility(View.GONE);
//        ll_gaishai_content.setOnClickListener(this);
        mGridView = findViewById(R.id.gv_pingce);

//        init_chart();
        List<PingCeSingle> lists = new ArrayList<>();
        Map<String, Float> datamap = new HashMap<>();
        datamap.put("毛孔", 30f);
        datamap.put("黑头", 40f);
        datamap.put("纹理", 50f);
        PingCeSingle data = new PingCeSingle(datamap, "肤质", 70, "合报告说明综合报告说明综合报告说明综合报告说明");
        data.setFillColor(255, 105, 180);
        data.setWebColor(255, 182, 193);
        data.setTitleColor(255, 105, 180);
        lists.add(data);

        datamap = new HashMap<>();
        datamap.put("粉刺", 32f);
        datamap.put("痘痘", 40f);
        datamap.put("痘印", 54f);
        datamap.put("老年斑", 65f);
        datamap.put("黄褐斑", 34f);
//        datamap.put("雀斑",54f);
//        datamap.put("真皮斑",32f);
//        datamap.put("荧光剂",32f);
//        datamap.put("铅",65f);
//        datamap.put("汞",52f);
        data = new PingCeSingle(datamap, "粉刺", 50, "hao");
        data.setFillColor(0, 255, 0);
        data.setWebColor(127, 255, 170);
        data.setTitleColor(0, 255, 0);
        lists.add(data);

        datamap = new HashMap<>();
        datamap.put("雀斑", 54f);
        datamap.put("真皮斑", 32f);
        datamap.put("荧光剂", 32f);
        datamap.put("铅", 65f);
        datamap.put("汞", 52f);
        data = new PingCeSingle(datamap, "粉刺", 50, "hao");
        data.setFillColor(0, 255, 0);
        data.setWebColor(127, 255, 170);
        data.setTitleColor(0, 255, 0);
        lists.add(data);

        mGridView.setAdapter(new RadarAdapter(getApplicationContext(), lists));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("onTouchEvent", "MyGridView onTouchEvent");
        return super.onTouchEvent(event);
    }

    //    private void init_chart(){
//        tf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
//        Description des = mchart.getDescription();
////        des.setText("我是描述");
//        des.setEnabled(false);
//        mchart.setWebLineWidth(2f);
//        mchart.setWebColor(Color.argb(1,255,182,193));
//        mchart.setWebLineWidthInner(1f);
//        mchart.setWebColorInner(Color.argb(1,255,182,193));
//        mchart.setBackgroundColor(Color.WHITE);//背景色
//        mchart.setWebAlpha(100);
//        mchart.setTouchEnabled(false);
//        mchart.setWebLineWidthInner(2f);
//
//
//
//        Map<String, Float> datamap = new HashMap<>();
//        datamap.put("毛孔",30f);
//        datamap.put("黑头",40f);
//        datamap.put("纹理",50f);
//        datamap.put("粉刺",32f);
//        datamap.put("痘痘",40f);
//        datamap.put("痘印",54f);
//        datamap.put("老年斑",65f);
//        datamap.put("黄褐斑",34f);
//        datamap.put("雀斑",54f);
//        datamap.put("真皮斑",32f);
//        datamap.put("荧光剂",32f);
//        datamap.put("铅",65f);
//        datamap.put("汞",52f);
//        PingCeSingle data = new PingCeSingle(datamap,70, "hao");
//        setData(data);
//
//        mchart.animateXY(1400, 1400);
//
//        XAxis xAxis = mchart.getXAxis();
//        xAxis.setTypeface(tf);
//        xAxis.setTextSize(9f);
//        xAxis.setYOffset(0f);
//        xAxis.setXOffset(0f);
//        xAxis.setValueFormatter(new MyValueFormatter());
//        xAxis.setTextColor(Color.rgb(255,182,193));//x轴标签
//
//        YAxis yAxis = mchart.getYAxis();
//        yAxis.setTypeface(tf);
//        yAxis.setLabelCount(5, false);
//        yAxis.setTextSize(9f);
//        yAxis.setAxisMinimum(0f);
//        yAxis.setAxisMaximum(80f);
//        yAxis.setDrawLabels(false);
//        yAxis.setTextColor(Color.BLUE); //y轴数值颜色
//
//
//        //图例
//        Legend l = mchart.getLegend();
//        l.setEnabled(false);
//        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
//        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
//        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
//        l.setDrawInside(false);
//        l.setTypeface(tf);
//        l.setXEntrySpace(7f);
//        l.setYEntrySpace(5f);
//        l.setTextColor(Color.WHITE);
//    }
    private static final String TAG = "ResultPreviewActivity";
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0){
            if (resultCode==RESULT_OK){
                if (data!=null){
                    ArrayList<CharSequence> picturePaths = data.getCharSequenceArrayListExtra(Contast.PICTURE_PATHS);
                    String paths="连拍图片的路径如下：\n";
                    for (int i = 0; i <picturePaths.size() ; i++) {
                        paths+=picturePaths.get(i)+"\n";
                    }
                    Log.e(TAG,"result path "+paths.toString());
//                    String  path = picturePaths.get(0).toString();
//                    Bitmap bitmap = BitmapFactory.decodeFile(path);
//                    Matrix matrix = new Matrix();
//                    matrix.setRotate(90);//默认是横屏的饿，旋转90度
//                    Bitmap bitmap1 = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(),matrix,true);
//                    mIvShow.setImageBitmap(bitmap1);
//                    mShowPaths.setText(paths);
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_gaisan:
//                ll_gaishai_content.setVisibility(View.VISIBLE);
//                showLayout(is_ll_gaishan_show, ll_gaishai_content);

//                Toast.makeText(this, "改善被点击了", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, GaiShanActivity.class));
                break;
//            case R.id.ll_item:
//                Toast.makeText(this,"雷达图布局被点击了",Toast.LENGTH_SHORT).show();
//                break;
            case R.id.iv_share:
                startActivity(new Intent(this, ShareActivity.class));
//                Toast.makeText(this, "分享被点击了", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_camera:
//                startActivityForResult(new Intent(this,AutoTakePicturesActivity.class),0);
                Toast.makeText(this, "相机被点击了", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.ll_gaishan_conten:
//                Toast.makeText(this, "item布局被点击了", Toast.LENGTH_SHORT).show();
//                break;
        }

    }

    public class MyValueFormatter implements IAxisValueFormatter {

        private DecimalFormat mFormat;

        public MyValueFormatter() {
            mFormat = new DecimalFormat("###,###,##0.0"); // use one decimal
        }


        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            Log.d("zhangpengcheng", value + "");

            int v = (int) value;
            if (v < keys.size())
                return keys.get(v);
            return "";
        }
    }

    List<String> keys = new ArrayList<>();

    public void setData(PingCeSingle data) {

        float mult = 80;
        float min = 20;
        Map<String, Float> dataMap = data.getNames();
//        int cnt = dataMap.size();
        Set<String> names = dataMap.keySet();


        List<RadarEntry> entries1 = new ArrayList<>();
//        List<RadarEntry> entries2 = new ArrayList<>();
        keys.clear();
//        ArrayList<RadarEntry> entries2 = new ArrayList<RadarEntry>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        int i = 0;
        for (String key : names) {
//            float val1 = (float) (Math.random() * mult) + min;
//            entries1.add(new RadarEntry(val1));
//            Log.d("zhangpengc","key ="+key+"; "+dataMap.get(key));
            keys.add(key);
            i++;
//            if (i < 3) {
            entries1.add(new RadarEntry(dataMap.get(key)));
//            }else {
//                entries2.add(new RadarEntry(dataMap.get(key)));
//            }
//            float val2 = (float) (Math.random() * mult) + min;
//            entries2.add(new RadarEntry(val2));
        }

        RadarDataSet set1 = new RadarDataSet(entries1, "Last Week");
        set1.setColor(Color.rgb(255, 105, 180));
        set1.setFillColor(Color.rgb(255, 105, 180));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(0f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);
//        set1.setDrawValues(true);

//        RadarDataSet set2 = new RadarDataSet(entries2, "This Week");
//        set2.setColor(Color.rgb(121, 162, 175));
//        set2.setFillColor(Color.rgb(121, 162, 175));
//        set2.setDrawFilled(true);
//        set2.setFillAlpha(180);
//        set2.setLineWidth(2f);
//        set2.setDrawHighlightCircleEnabled(true);
//        set2.setDrawHighlightIndicators(false);
//        set2.setDrawValues(true);

        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(set1);
//        sets.add(set2);

        RadarData radarData = new RadarData(sets);
        radarData.setDrawValues(true);
        radarData.setValueTypeface(tf);
        radarData.setValueTextSize(8f);
        radarData.setValueTextColor(Color.WHITE);

        mchart.setData(radarData);
        mchart.invalidate();
    }

    @SuppressLint("WrongConstant")
    private void showLayout(boolean isShow, LinearLayout layout) {
        int show = isShow ? 8 : 0;
        layout.setVisibility(show);
        is_ll_gaishan_show = !isShow;

    }

}
