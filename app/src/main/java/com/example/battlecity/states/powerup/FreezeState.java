package com.example.battlecity.states.powerup;

import android.graphics.Canvas;

import com.example.battlecity.GameLoop;
import com.example.battlecity.gameobject.Tank;

public class FreezeState extends State{
    private static final int SPAWNS_PER_MINUTE = 6;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE / 60.0;
    private static final double UPDATES_PER_SPAWN = GameLoop.MAX_UPS/SPAWNS_PER_SECOND;
    private static double updatesUntilChangeState = UPDATES_PER_SPAWN;
    protected FreezeState(Tank tank) {
        super(tank);
    }

    @Override
    public void draw(Canvas canvas) {
        getTank().getSprite().drawResized(canvas);
    }

    @Override
    public void update() {
        if(updatesUntilChangeState <= 0){
            changeState(new NormalState(getTank()));
        }else{
            updatesUntilChangeState--;
        }
    }

    @Override
    public void move() {

    }
}
