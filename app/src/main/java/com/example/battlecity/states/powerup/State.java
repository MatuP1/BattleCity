package com.example.battlecity.states.powerup;

import android.graphics.Canvas;

import com.example.battlecity.gameobject.Tank;

public abstract class State {
    protected Tank tank;

    protected int row;
    protected State(Tank tank) {
        this.tank = tank;
    }

    public abstract void draw(Canvas canvas);

    public void receivedDamage(){
            if (getTank().getHealthPoints() > 0) {
                getTank().setHealthPoints(getTank().getHealthPoints() - 1);
                getTank().die();
            }
    }

    public abstract void update();

    public abstract void move();

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public void setRow(int row){this.row = row;  }

    public int getRow() {return row;}

    public void changeState(State state){
        tank.setState(state);
    }
}
