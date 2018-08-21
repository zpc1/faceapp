package com.deeocare.deepcare.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.GridView;

import java.security.spec.ECField;

public class MyGridView extends GridView {
    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
//        super.onMeasure(widthMeasureSpec, expandSpec);
//    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.d("onTouchEvent", "MyGridView onTouchEvent");
        return super.onTouchEvent(ev);
    }

    private float xStart;
    private float yStart;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                xStart = ev.getX();
                yStart = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float xEnd = ev.getX();
                float yEnd = ev.getY();
                float xMove = Math.abs(xStart - xEnd);
                float yMove = Math.abs(yStart - yEnd);
                if ( xMove < yMove && yMove > 3) {
                    return true;
                }
                break;
        }
        return true;
    }
}
