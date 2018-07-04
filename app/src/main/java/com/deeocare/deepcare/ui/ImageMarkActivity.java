package com.deeocare.deepcare.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.deeocare.deepcare.R;
import com.deeocare.deepcare.view.MarkView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TooManyListenersException;

public class ImageMarkActivity extends Activity {
    private ImageView iv_back;
    private FrameLayout layout;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_mark);
//        iv_back = (ImageView) findViewById(R.id.iv_back);
//        Glide.with(this).load(R.drawable.test).into(iv_back);
//        layout = (FrameLayout)findViewById(R.id.frame);
//        layout.addView(new MarkView(this));
//        init();
    }


    private void  init(){
        List<Map<String, Integer>> line = new ArrayList();
        Map<String, Integer> point ;
        try {
            InputStream is = getAssets().open("test.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String aline = null;
            aline = bufferedReader.readLine();
//            aline =  "897,880 897,881 897,882 897,883 897,884 897,885 897,886 897,887 897,888 897,889 897,890 897,891 897,892 897,893 897,894 897,895 898,880 898,881 898,882 898,883 898,884 898,885 898,886 898,887 898,888 898,889 898,890 898,891 898,892 898,893 898,894 898,895" ;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mBitmap==null){
            mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.test);
            Bitmap bitmap = Bitmap.createScaledBitmap(mBitmap,mBitmap.getWidth(), mBitmap.getHeight(),true);
            mCanvas = new Canvas();
            mCanvas.setBitmap(bitmap);
            mCanvas.drawColor(Color.WHITE);

            mPaint = new Paint();
            mPaint.setColor(Color.RED);
            mPaint.setStrokeWidth(10);
        }
        Path path = new Path();
        for (int i = 0; i < line.size(); i++) {
            if (i == 0) {
                path.moveTo(line.get(i).get("x"),line.get(i).get("y"));
            }else {
                path.lineTo(line.get(i).get("x"),line.get(i).get("y"));
            }
        }
        path.close();
        mCanvas.drawPath(path,mPaint);

    }
}
