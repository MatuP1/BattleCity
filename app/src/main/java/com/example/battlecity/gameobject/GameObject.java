package com.example.battlecity.gameobject;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.battlecity.gameobject.enemy.Enemy;

public abstract class GameObject {
    private Paint paint;
    private double radius;
    private double positionX,positionY;
    private int life;

    public GameObject(double positionX,double positionY, double radius){
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        life = 1;
    }

    protected static double distanceBetweenTwoObjects(GameObject go1, GameObject go2) {
        return Math.sqrt(
                Math.pow(go2.getPositionX()- go1.getPositionX(),2) +
                Math.pow(go2.getPositionY() - go1.getPositionY(),2)

        );
    }

    public static boolean isCollinding(GameObject go1, GameObject go2) {
        double distance = distanceBetweenTwoObjects(go1,go2);
        double distanceToCollision = go1.getRadius() + go2.getRadius();
        if (distance < distanceToCollision)
            return true;
        else
            return false;
    }

    public double getPositionY() {
        return positionY;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public abstract void draw(Canvas canvas);
    public abstract void update();
    public abstract void die();
    public abstract void receiveDamage();

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }


}
