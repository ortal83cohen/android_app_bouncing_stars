package com.ortal.bouncing.stars.customviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.ortal.bouncing.stars.models.StarItem;

import java.util.ArrayList;

public class Bounce extends SurfaceView {

    private static final int NO_TOUCHED_STAR = -1;
    private final int frameTime;
    private SurfaceHolder holder;
    private Handler handler;
    private Runnable run;
    private PointF viewSize;
    private ArrayList<StarItem> starItems;
    private int touchedStar = NO_TOUCHED_STAR;


    public Bounce(Context context, final int speed, final int amount) {
        super(context);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                setWillNotDraw(false);
                Canvas c = holder.lockCanvas(null);
                draw(c);
                holder.unlockCanvasAndPost(c);
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                viewSize = new PointF(width, height);
                starItems = new ArrayList<>();
                // create the amount of elements
                for (int i = 0; i < amount; i++) {
                    starItems.add(new StarItem(viewSize));
                }
            }

        });
        //getting windows size
        Display display = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        // calculating length of frame - Second / RefreshRate(number of refresh to Second) = length of frame
        frameTime = Math.round(1000 / display.getRefreshRate());

        handler = new Handler();

        starItems = new ArrayList<>();

        run = new Runnable() {
            @Override
            public void run() {
                int i = 0;
                for (StarItem starItem : starItems) {
                    if (i++ != touchedStar) {
                        starItem.move(((float) speed) / 10);
                    }
                }
                invalidate();
            } //run()
        }; //run
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                //pick the correct star from the stars list and save its index
                int i = 0;
                for (StarItem starItem : starItems) {
                    if (starItem.touch(event.getX(), event.getY())) {
                        touchedStar = i;
                        break;
                    }
                    i++;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                touchedStar = NO_TOUCHED_STAR;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (touchedStar != NO_TOUCHED_STAR) {
                    starItems.get(touchedStar).moveManual(event.getX(), event.getY());
                }
                break;
            }
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // draw all stars
        for (StarItem starItem : starItems) {
            canvas.drawPath(starItem.getPath(), starItem.getPaint());
        }

        handler.postDelayed(run, frameTime);
    }


}
