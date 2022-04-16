package com.example.battlecity.gameobject.enemy;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.battlecity.GameLoop;
import com.example.battlecity.gameobject.Base;
import com.example.battlecity.gameobject.GameObject;
import com.example.battlecity.gameobject.Tank;

public abstract class Enemy extends Tank {

    private static double speedModifier = 0.6;
    private static final double SPEED_PIXELS_PER_SECOND = 400.0 * speedModifier;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/ GameLoop.MAX_UPS;
    private static final int SPAWNS_PER_MINUTE = 20;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE / 60.0;
    private static final double UPDATES_PER_SPAWN = GameLoop.MAX_UPS/SPAWNS_PER_SECOND;
    private static double updatesUntilNextSpawn = UPDATES_PER_SPAWN;
    private int value;

    private final Base base;
    public Enemy(Context context, Base base, double positionX, double positionY, double radius) {
        super(positionX, positionY, radius);
        this.base = base;
        setPaint(new Paint());
        getPaint().setColor(Color.RED);
        value = 0;
    }

    public static boolean readyToSpawn() {
        if(updatesUntilNextSpawn <= 0){
            updatesUntilNextSpawn += UPDATES_PER_SPAWN;
            return true;
        }else{
            updatesUntilNextSpawn--;
            return false;
        }
    }

    @Override
    public void update() {
        double distanceToBaseX = base.getPositionX() - getPositionX();
        double distanceToBaseY = base.getPositionY() - getPositionY();

        double distanceToBase = GameObject.distanceBetweenTwoObjects(this,base);

        double directionX = distanceToBaseX/distanceToBase;
        double directionY = distanceToBaseY/distanceToBase;

        if(distanceToBase>0){
            setVelocityX(directionX*MAX_SPEED);
            setVelocityY(directionY*MAX_SPEED);
        }else{
            setVelocityX(0);
            setVelocityY(0);
        }

        //Update position
        setPositionX(getPositionX() + getVelocityX());
        setPositionY(getPositionY() + getVelocityY());
    }

    @Override
    public void die() {

    }
    @Override
    public void receiveDamage(){
        setLife(getLife()-1);
        if(getLife() == 0){
            die();
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
