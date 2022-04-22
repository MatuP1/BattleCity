package com.example.battlecity.states.powerup;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.battlecity.GameLoop;
import com.example.battlecity.gameobject.Tank;
import com.example.battlecity.graphics.Sprite;
import com.example.battlecity.states.powerup.State;

public class HelmetState extends State {
    private int column_offset;

    private static final int SPAWNS_PER_MINUTE = 6;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE / 60.0;
    private static final double UPDATES_PER_SPAWN = GameLoop.MAX_UPS/SPAWNS_PER_SECOND;
    private static double updatesUntilChangeState = UPDATES_PER_SPAWN;
    public HelmetState(Tank tank) {
        super(tank);
    }

    @Override
    public void draw(Canvas canvas) {
        getTank().getSprite().drawResized(canvas);
        new Sprite(tank.getContext(),new Rect((16+column_offset)*16,9*16,16+(column_offset+16)*16,9*16+16),getTank()).drawResized(canvas);

        column_offset = (column_offset+1) % 2;
    }

    @Override
    public void receivedDamage() {

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
