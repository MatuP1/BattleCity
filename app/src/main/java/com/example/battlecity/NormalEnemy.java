package com.example.battlecity;

import android.graphics.Canvas;

public class NormalEnemy extends Enemy{
    public NormalEnemy(Base base,double positionX, double positionY, double radius) {
        super(base,positionX, positionY, radius);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle((float)positionX,(float) positionY,(float) radius,paint );

    }
}
