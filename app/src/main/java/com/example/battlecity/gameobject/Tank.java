package com.example.battlecity.gameobject;

import com.example.battlecity.gameobject.enemy.Enemy;

public abstract class Tank extends GameObject {

    private int healthPoints;

    public Tank(double positionX, double positionY, double radius) {
        super(positionX, positionY, radius);
    }


    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    @Override
    public void receiveDamage(){
        if(getHealthPoints()>0)
            setHealthPoints(getHealthPoints()-1);
    }

}
