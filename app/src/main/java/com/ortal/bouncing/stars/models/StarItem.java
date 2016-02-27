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
    private float radius;
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
        this.radius = DEFAULT_RADIUS;
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
        if (position.y + radius >= viewSize.y - speed - 2) {
            directionY = false;
        }
        if (position.y - radius <= speed + 2) {
            directionY = true;
        }
        if (position.x + radius >= viewSize.x - speed - 2) {
            directionX = false;
        }
        if (position.x - radius <= speed + 2) {
            directionX = true;
        }
        updateStar();
    } //move

    public Path getPath() {
        return path;
    }

    private void updateStar() {
        int numOfPt = 5;
        float innerRadius = this.radius / 2;
        double section = 2.0 * Math.PI / numOfPt;

        path.reset();
        path.moveTo(
                (float) (position.x + radius * Math.cos(0)),
                (float) (position.y + radius * Math.sin(0)));
        path.lineTo(
                (float) (position.x + innerRadius * Math.cos(0 + section / 2.0)),
                (float) (position.y + innerRadius * Math.sin(0 + section / 2.0)));

        for (int i = 1; i < numOfPt; i++) {
            path.lineTo(
                    (float) (position.x + radius * Math.cos(section * i)),
                    (float) (position.y + radius * Math.sin(section * i)));
            path.lineTo(
                    (float) (position.x + innerRadius * Math.cos(section * i + section / 2.0)),
                    (float) (position.y + innerRadius * Math.sin(section * i + section / 2.0)));
        }
        path.close();
    }//updateStar

    public boolean touch(float x, float y) {
        return Math.abs(position.x - x) < radius && Math.abs(position.y - y) < radius;
    }

    public void moveManual(float x, float y) {
        if (x != 0 || y != 0) {
            directionX = (position.x - x < 0);
            directionY = (position.y - y < 0);
        }
        position.x = x;
        position.y = y;

        updateStar();
    }

} //class
