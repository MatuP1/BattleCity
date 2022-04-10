package com.example.battlecity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Base extends GameObject{

    public Base(double positionX, double positionY, double radius) {
        super(positionX, positionY, radius);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        life = 1;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle((float)positionX,(float) positionY,(float) radius,paint);
    }

    @Override
    public void update() {

    }

    @Override
    public void die() {

    }
}
