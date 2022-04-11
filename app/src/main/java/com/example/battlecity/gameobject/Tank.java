package com.example.battlecity.gameobject;

import com.example.battlecity.gameobject.enemy.Enemy;

public abstract class Tank extends GameObject {
    protected double velocityY;
    protected double velocityX;
    public Tank(double positionX, double positionY, double radius) {
        super(positionX, positionY, radius);
    }

}
