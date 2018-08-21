package com.deeocare.deepcare.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.deeocare.deepcare.R;
import com.deeocare.deepcare.bean.PingCeSingle;
import com.deeocare.deepcare.ui.ResultPreviewActivity;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
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

public class RadarAdapter extends BaseAdapter {
    private Context mContext;
    private List<PingCeSingle> result;

   public RadarAdapter(Context mContext, List<PingCeSingle> data){
        this.result = data;
       this.mContext = mContext;
   }

    @Override
    public int getCount() {
        return result.size()==0?0:result.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       ViewHolder holder;
       if (view == null){
           view = View.inflate(mContext, R.layout.grid_item,null);
           holder = new ViewHolder();
           holder.mRadarChart = view.findViewById(R.id.radarchart_fuzhi);
           holder.tv_desc = view.findViewById(R.id.tv_desc);
           holder.tv_title = view.findViewById(R.id.tv_titile);
           holder.tv_score = view.findViewById(R.id.tv_score);
           view.setTag(holder);
       }else {
           holder = (ViewHolder) view.getTag();
       }

       init_chart(holder.mRadarChart, result.get(i));
       holder.tv_desc.setText(result.get(i).getDes());
        Log.d("hahaha", "getView: "+result.get(i).getScore());
       holder.tv_score.setText(result.get(i).getScore()+"");
       holder.tv_title.setText(result.get(i).getTitle());

        return view;
    }

    class ViewHolder{
       RadarChart mRadarChart;
       TextView tv_score, tv_desc, tv_title;

    }


    private Typeface tf;
    private void init_chart(RadarChart mchart, PingCeSingle data){
        tf = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Regular.ttf");
        Description des = mchart.getDescription();
//        des.setText("我是描述");
        des.setEnabled(false);
        mchart.setWebLineWidth(2f);
        mchart.setWebColor(data.getWebColor());
        mchart.setWebLineWidthInner(1f);
        mchart.setWebColorInner(data.getWebColor());
        mchart.setBackgroundColor(Color.WHITE);//背景色
        mchart.setWebAlpha(100);
        mchart.setTouchEnabled(false);
        mchart.setWebLineWidthInner(2f);



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
        setData(data, mchart);

        mchart.animateXY(1400, 1400);

        XAxis xAxis = mchart.getXAxis();
        xAxis.setTypeface(tf);
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new MyValueFormatter());
        xAxis.setTextColor(data.getWebColor());//x轴标签

        YAxis yAxis = mchart.getYAxis();
        yAxis.setTypeface(tf);
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);
        yAxis.setTextColor(Color.RED); //y轴数值颜色


        //图例
        Legend l = mchart.getLegend();
        l.setEnabled(false);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setTypeface(tf);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.BLUE);
    }
    public class MyValueFormatter implements IAxisValueFormatter {

        private DecimalFormat mFormat;

        public MyValueFormatter() {
            mFormat = new DecimalFormat("###,###,##0.0"); // use one decimal
        }


        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            Log.d("zhangpengcheng",keys.toString()+"");

            int v = (int) value;
            if (v<keys.size())
                return keys.get(v);
            return "";
        }
    }

    List<String> keys ;
    public void setData(PingCeSingle data, RadarChart mchart) {
        keys = new ArrayList<>();
        float mult = 80;
        float min = 20;
        Map<String, Float> dataMap = data.getNames();
//        int cnt = dataMap.size();
        Set<String> names = dataMap.keySet();
//        Log.e("zhangpengcheng", names.toString());


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
            entries1.add(new RadarEntry(dataMap.get(key),key));
//            }else {
//                entries2.add(new RadarEntry(dataMap.get(key)));
//            }
//            float val2 = (float) (Math.random() * mult) + min;
//            entries2.add(new RadarEntry(val2));
        }

        RadarDataSet set1 = new RadarDataSet(entries1, "Last Week");
        set1.setColor(data.getFillColor());
        set1.setFillColor(data.getWebColor());
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
        radarData.setValueTextColor(data.getFillColor());

        mchart.setData(radarData);
        mchart.invalidate();
        notifyDataSetChanged();
    }

}
