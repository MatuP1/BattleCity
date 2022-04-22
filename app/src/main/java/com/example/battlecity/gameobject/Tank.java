package com.example.battlecity.gameobject;

import android.content.Context;
import android.graphics.Canvas;

import com.example.battlecity.states.powerup.NormalState;
import com.example.battlecity.states.powerup.State;

public abstract class Tank extends GameObject {

    private int healthPoints;
    protected State state;

    private boolean canMove;

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    protected Base base;

    public Tank(Context context, double positionX, double positionY, double radius) {
        super(context, positionX, positionY, radius);
        state = new NormalState(this);
    }


    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    @Override
    public void receiveDamage() {
        state.receivedDamage();
    }

    @Override
    public void draw(Canvas canvas) {
        state.draw(canvas);//getSprite().drawResized(canvas);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setState(int row) {
        state.setRow(row);
    }

    public abstract void updateStrategy(double dirX, double dirY);

    public boolean canMove() {
        return canMove;
    }

    public void canMove(boolean canMove){
        this.canMove = canMove;
    }

}
