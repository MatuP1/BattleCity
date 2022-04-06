package com.example.battlecity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Player {
    private Paint paint;
    private double positionX,positionY,radius;

    public Player(Context context,double positionX, double positionY, double radius){
        this.positionX=positionX;
        this.positionY=positionY;
        this.radius = radius;

        paint = new Paint();
        int color = ContextCompat.getColor(context,R.color.magenta);
        paint.setColor(color);

    }
    public void draw(Canvas canvas) {
        canvas.drawCircle((float)positionX,(float) positionY,(float) radius,paint );
    }

    public void update() {

    }

    public void setPosition(double x,double y) {
        positionX = x;
        positionY = y;

    }
}
