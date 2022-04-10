package com.example.battlecity;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class GameObject {
    protected Paint paint;
    protected double radius;
    protected double positionX,positionY;
    protected int life;

    public GameObject(double positionX,double positionY, double radius){
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
    }

    protected static double distanceBetweenTwoObjects(GameObject go1, GameObject go2) {
        return Math.sqrt(
                Math.pow(go2.getPositionX()- go1.getPositionX(),2) +
                Math.pow(go2.getPositionY() - go1.getPositionY(),2)

        );
    }

    public double getPositionY() {
        return positionY;
    }

    public double getPositionX() {
        return positionX;
    }

    public abstract void draw(Canvas canvas);
    public abstract void update();
    public abstract void die();
}
