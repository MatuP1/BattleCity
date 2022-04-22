package com.example.battlecity.gameobject.enemy;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.battlecity.GameLoop;
import com.example.battlecity.gameobject.Base;
import com.example.battlecity.gameobject.GameObject;
import com.example.battlecity.gameobject.Tank;
import com.example.battlecity.gameobject.bullet.EnemyBullet;
import com.example.battlecity.strategies.StrategyMoveLeft;
import com.example.battlecity.strategies.StrategyMoveRight;

import java.util.Random;

public abstract class Enemy extends Tank {

    private static double speedModifier = 0.6;
    private static final double SPEED_PIXELS_PER_SECOND = 400.0 * speedModifier;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/ GameLoop.MAX_UPS;
    private static final int SPAWNS_PER_MINUTE = 20;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE / 60.0;
    private static final double UPDATES_PER_SPAWN = GameLoop.MAX_UPS/SPAWNS_PER_SECOND;
    private static double updatesUntilNextSpawn = UPDATES_PER_SPAWN;
    private int value;

    private int nextUpdateToShoot;

    public Enemy(Context context, Base base, double positionX, double positionY, double radius) {
        super(context,positionX, positionY, radius);
        this.base = base;
        setPaint(new Paint());
        getPaint().setColor(Color.RED);
        value = 0;
        nextUpdateToShoot = new Random().nextInt(60)+30;
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
       state.move();
       state.update();
    }

    public boolean readyToShoot() { return --nextUpdateToShoot <= 0; }

    public EnemyBullet shoot()
    {
        nextUpdateToShoot = new Random().nextInt(60)+30;
        return new EnemyBullet(getContext(), this);
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
