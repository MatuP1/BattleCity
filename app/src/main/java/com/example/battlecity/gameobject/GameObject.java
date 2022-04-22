package com.example.battlecity.gameobject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.battlecity.gameobject.enemy.Enemy;
import com.example.battlecity.graphics.Sprite;

public abstract class GameObject {
    private Paint paint;
    private double radius;
    private double positionX,positionY;
    private int life;
    private double velocityY;
    private double velocityX;
    //always start facing X
    private double directionX;
    private double directionY = -1;
    private Sprite sprite;


    private Context context;

    public GameObject(Context context, double positionX, double positionY, double radius){
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        life = 1;
        this.context = context;
    }

    public static double distanceBetweenTwoObjects(GameObject go1, GameObject go2) {
        return Math.sqrt(
                Math.pow(go2.getPositionX()- go1.getPositionX(),2) +
                Math.pow(go2.getPositionY() - go1.getPositionY(),2)

        );
    }

    public static boolean isColliding(GameObject go1, GameObject go2) {
        double distance = distanceBetweenTwoObjects(go1,go2);
        double distanceToCollision = go1.getRadius()+go2.getRadius();
        if(distance<distanceToCollision)
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

    public double getVelocityY() { return velocityY;  }

    public void setVelocityY(double velocityY) { this.velocityY = velocityY; }

    public double getVelocityX() {        return velocityX; }

    public void setVelocityX(double velocityX) {        this.velocityX = velocityX; }

    public double getDirectionX() { return directionX; }

    public double getDirectionY() {        return directionY; }

    public void setDirectionX(double directionX) {       this.directionX = directionX; }

    public void setDirectionY(double directionY) { this.directionY = directionY; }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public boolean checkOutOfBounds(int sh,int sw){
        boolean outOfBounds = false;
        if(getPositionX() > 1850|| getPositionX() < 96*6){
            setVelocityX(0);
            setDirectionX(0);
            if(getPositionX() > 1850)
                setPositionX(1820);
            else
                setPositionX(32+96*6);
            outOfBounds = true;
        }
        if(getPositionY() > sh|| getPositionY() < 24){
            setVelocityY(0);
            setDirectionY(0);
            if(getPositionY() > sh)
                setPositionY(sh-32);
            else
                setPositionY(24);
            outOfBounds = true;
        }
        return outOfBounds;
    }

    public void destroy(){
        paint = null;
        sprite = null;

    }
}
