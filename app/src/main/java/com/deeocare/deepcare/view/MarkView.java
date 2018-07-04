package com.deeocare.deepcare.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.deeocare.deepcare.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MarkView extends View {
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mPaint;
    private Context mContext;
    Path path;

    public MarkView(Context context) {
        super(context);
        mContext = context;
    }

    public MarkView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(this.getClass().getSimpleName(), "onMeasure: width = "+MeasureSpec.getSize(widthMeasureSpec)+" hight = "+MeasureSpec.getSize(heightMeasureSpec));
    }

    //    private void init(){
//        mPaint = new Paint();
//        mPaint.setAntiAlias(true);          //抗锯齿
//        mPaint.setColor(Color.BLUE);//画笔颜色
//        mPaint.setStyle(Paint.Style.FILL);  //画笔风格
//        mPaint.setTextSize(36);             //绘制文字大小，单位px
//        mPaint.setStrokeWidth(5);           //画笔粗细
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawCircle(200,200,100,mPaint);
        Path path = new Path();
        path.moveTo(200,200);
        path.lineTo(300, 300);
        path.lineTo(400,400);
        path.close();
//        path.lineTo(10, 10);
//        path.close();
//        canvas.drawPath(path,mPaint);
//        canvas.drawBitmap(mBitmap,0,0,null);
        canvas.drawPath(this.path,mPaint);
//        canvas.drawLines(new float[]{50,50,100,100,300,500,800,1200},mPaint);

    }

    private void  init(){
        List<Map<String, Integer>> line = new ArrayList();
        Map<String, Integer> point ;
//        try {
//            InputStream is = mContext.getAssets().open("test.txt");
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String aline = "";
//            aline = bufferedReader.readLine();
            aline = "897,880 897,881 897,882 897,883 897,884 897,885 897,886 897,887 897,888 897,889 897,890 897,891 897,892 897,893 897,894 897,895 898,880 898,881 898,882 898,883 898,884 898,885 898,886 898,887 898,888 898,889 898,890 898,891 898,892 898,893 898,894 898,895" ;
            String[] items = aline.split(" ");
            for (String item : items){
                String[] x_y = item.split(",");
                int x = Integer.parseInt(x_y[0]);
                int y = Integer.parseInt(x_y[1]);
                point = new HashMap();
                point.put("x",x);
                point.put("y",y);
                line.add(point);

            }

//            while ((line = bufferedReader.readLine()) != null){
            System.out.println(aline);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        if (mBitmap==null){
            mBitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.test)).getBitmap();

//            mCanvas = new Canvas(BitmapFactory.decodeResource(getResources(), R.drawable.test));
//            mCanvas.drawColor(Color.WHITE);

            mPaint = new Paint();
            mPaint.setColor(Color.BLUE);
            mPaint.setStrokeWidth(10);
        }
        path = new Path();
        for (int i = 0; i < line.size(); i++) {
            if (i == 0) {
                path.moveTo(line.get(i).get("x"),line.get(i).get("y"));
            }else {
                path.lineTo(line.get(i).get("x"),line.get(i).get("y"));
            }
        }
        path.close();
        invalidate();

    }
}
