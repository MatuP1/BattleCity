package com.example.battlecity.gameobject.enemy;

import android.graphics.Canvas;

import com.example.battlecity.gameobject.Base;

public class StrongEnemy extends Enemy{
    public StrongEnemy(Base base, double positionX, double positionY, double radius) {
        super(base, positionX, positionY, radius);
        setValue(300);
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
