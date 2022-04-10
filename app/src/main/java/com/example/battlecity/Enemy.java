package com.example.battlecity;

import android.graphics.Color;
import android.graphics.Paint;

public abstract class Enemy extends Tank{

    private static double speedModifier = 0.6;
    private static final double SPEED_PIXELS_PER_SECOND = 400.0 * speedModifier;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/GameLoop.MAX_UPS;
    private static final int SPAWNS_PER_MINUTE = 20;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE / 60.0;
    private static final double UPDATES_PER_SPAWN = GameLoop.MAX_UPS/SPAWNS_PER_SECOND;
    private static double updatesUntilNextSpawn = UPDATES_PER_SPAWN;

    protected final Base base;
    public Enemy(Base base,double positionX, double positionY, double radius) {
        super(positionX, positionY, radius);
        this.base = base;
        paint = new Paint();
        paint.setColor(Color.RED);
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
        double distanceToBaseX = base.getPositionX() - positionX;
        double distanceToBaseY = base.getPositionY() - positionY;

        double distanceToBase = GameObject.distanceBetweenTwoObjects(this,base);

        double directionX = distanceToBaseX/distanceToBase;
        double directionY = distanceToBaseY/distanceToBase;

        if(distanceToBase>0){
            velocityX = directionX*MAX_SPEED;
            velocityY = directionY*MAX_SPEED;
        }else{
            velocityX = 0;
            velocityY = 0;
        }

        //Update position
        positionX += velocityX;
        positionY += velocityY;
    }

    @Override
    public void die() {

    }
}
