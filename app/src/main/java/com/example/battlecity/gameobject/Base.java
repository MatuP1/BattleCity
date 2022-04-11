package com.example.battlecity.gameobject;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.battlecity.gameobject.GameObject;

public class Base extends GameObject {

    public Base(double positionX, double positionY, double radius) {
        super(positionX, positionY, radius);
        setPaint(new Paint());
        getPaint().setColor(Color.WHITE);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle((float)getPositionX(),(float) getPositionY(),(float) getRadius(),getPaint() );
    }

    @Override
    public void update() {

    }

    @Override
    public void die() {

    }

    @Override
    public void receiveDamage() {
        die();
    }
}
