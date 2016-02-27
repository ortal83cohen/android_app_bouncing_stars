package com.ortal.bouncing.stars.models;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

import java.util.Random;

public class StarItem {
    private static final float DEFAULT_RADIUS = 150;
    Path path;


    private PointF position;
    private PointF viewSize;
    private Paint paint;
    private boolean directionX;
    private boolean directionY;

    public StarItem(PointF viewSize) {
        Random random = new Random();
        this.viewSize = viewSize;
        this.position = new PointF(random.nextFloat() * (viewSize.x - DEFAULT_RADIUS * 2) + DEFAULT_RADIUS,
                random.nextFloat() * (viewSize.y - DEFAULT_RADIUS * 2) + DEFAULT_RADIUS);
        this.paint = new Paint();
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setARGB(255, random.nextInt(200), random.nextInt(200), random.nextInt(200));
        this.directionX = random.nextBoolean();
        this.directionY = random.nextBoolean();
        path = new Path();
    } //constructor

    public Paint getPaint() {
        return paint;
    }

    public void move(float speed) {


        if (directionX) {
            position.x = position.x + speed;
        } else {
            position.x = position.x - speed;
        }
        if (directionY) {
            position.y = position.y + speed;
        } else {
            position.y = position.y - speed;
        }
        if (position.y + DEFAULT_RADIUS >= viewSize.y - speed - 2) {
            directionY = false;
        }
        if (position.y - DEFAULT_RADIUS <= speed + 2) {
            directionY = true;
        }
        if (position.x + DEFAULT_RADIUS >= viewSize.x - speed - 2) {
            directionX = false;
        }
        if (position.x - DEFAULT_RADIUS <= speed + 2) {
            directionX = true;
        }
        updateStar();
    } //move

    public Path getPath() {
        return path;
    }

    private void updateStar() {
        int numOfPt = 5;
        float innerDEFAULT_RADIUS = this.DEFAULT_RADIUS / 2;
        double section = 2.0 * Math.PI / numOfPt;

        path.reset();
        path.moveTo(
                (float) (position.x + DEFAULT_RADIUS * Math.cos(0)),
                (float) (position.y + DEFAULT_RADIUS * Math.sin(0)));
        path.lineTo(
                (float) (position.x + innerDEFAULT_RADIUS * Math.cos(0 + section / 2.0)),
                (float) (position.y + innerDEFAULT_RADIUS * Math.sin(0 + section / 2.0)));

        for (int i = 1; i < numOfPt; i++) {
            path.lineTo(
                    (float) (position.x + DEFAULT_RADIUS * Math.cos(section * i)),
                    (float) (position.y + DEFAULT_RADIUS * Math.sin(section * i)));
            path.lineTo(
                    (float) (position.x + innerDEFAULT_RADIUS * Math.cos(section * i + section / 2.0)),
                    (float) (position.y + innerDEFAULT_RADIUS * Math.sin(section * i + section / 2.0)));
        }
        path.close();
    }//updateStar

    public boolean touch(float x, float y) {
        //return true if the point is inside the star aria (actually aria= circle)
        return Math.abs(position.x - x) < DEFAULT_RADIUS && Math.abs(position.y - y) < DEFAULT_RADIUS;
    }

    public void moveManual(float x, float y) {
        // determinate the direction (after moveManual will done)
        if (x != 0 || y != 0) {
            directionX = (position.x - x < 0);
            directionY = (position.y - y < 0);
        }
        // determinate star position
        position.x = x;
        position.y = y;

        updateStar();
    }

} //class
