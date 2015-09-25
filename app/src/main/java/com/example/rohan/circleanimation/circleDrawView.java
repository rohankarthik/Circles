package com.example.rohan.circleanimation;

/**
 * Created by rohan on 4/14/2015.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.View.OnTouchListener;

import java.util.ArrayList;
import java.util.List;

public class circleDrawView extends View implements OnTouchListener {

    static int r = 0, g = 255, b = 0;
    int radius = 10;
    Paint circlePaint;
    long startTime, endTime ;

    VelocityTracker circleVelocity;
    List<Point> circlePoints = new ArrayList<Point>();
    int[] paintArray = new int[100];
    int[] radiusArray = new int[100];
    int radiusArrayIndex = 0;
    int index = 0;

    public circleDrawView(Context context) {
        super(context);
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setARGB(255, r, g, b);
        setFocusable(true);
        this.setOnTouchListener(this);
    }


    @Override
    public void onDraw(Canvas canvas) {

        index = 0;
        for (Point item : circlePoints) {

            circlePaint.setARGB(255, r, g, b);
            canvas.drawCircle(item.x, item.y, radiusArray[index], circlePaint);
            index = index + 1;
        }
        invalidate();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case (MotionEvent.ACTION_DOWN): {
                circleVelocity = VelocityTracker.obtain();
                circleVelocity.addMovement(event);
                startTime = System.currentTimeMillis();

                Point pointobj = new Point();
                pointobj.x = (int) event.getX();
                pointobj.y = (int) event.getY();

                if (circlePoints.size() < 15)
                    circlePoints.add(pointobj);

                break;
            }
            case MotionEvent.ACTION_MOVE: {
                circleVelocity.addMovement(event);
            }
            break;
            case MotionEvent.ACTION_UP: {
                radius = 10;

                circleVelocity.computeCurrentVelocity(1000);
                Log.i("rew", "X vel " + circleVelocity.getXVelocity() + " Y vel " + circleVelocity.getYVelocity());
                float x_velocity = circleVelocity.getXVelocity();
                float y_velocity = circleVelocity.getYVelocity();
                circleVelocity.recycle();
                circleVelocity = null;

                endTime = System.currentTimeMillis();
                int incrementRadius = (int) (((endTime - startTime) / 1000F) * 100);
                radius = radius + incrementRadius;
                radiusArray[radiusArrayIndex] = radius;
                radiusArrayIndex = radiusArrayIndex + 1;

                invalidate();

                break;
            }
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }
}
