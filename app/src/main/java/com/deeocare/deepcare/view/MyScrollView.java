package com.deeocare.deepcare.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    private float xStart;
    private float yStart;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                xStart = ev.getX();
//                yStart = ev.getY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                float xEnd = ev.getX();
//                float yEnd = ev.getY();
//                float xMove = Math.abs(xStart - xEnd);
//                float yMove = Math.abs(yStart - yEnd);
//                if (xMove < yMove && yMove > 3) {
//                    return fa;
//                }
//                break;
//        }
        return false;
    }
}
