package com.example.battlecity.gameobject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.battlecity.gameobject.GameObject;

public class Base extends Tank {

    public Base(Context context,double positionX, double positionY, double radius) {
        super(context,positionX, positionY, radius);
        setPaint(new Paint());
        getPaint().setColor(Color.WHITE);
        setHealthPoints(1);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle((float)getPositionX(),(float) getPositionY(),(float) getRadius(),getPaint() );
    }

    @Override
    public void updateStrategy(double dirX, double dirY) {

    }

    @Override
    public void update() {

    }

    @Override
    public void die() {

    }

    @Override
    public void receiveDamage() {
        setHealthPoints(0);
    }
}
