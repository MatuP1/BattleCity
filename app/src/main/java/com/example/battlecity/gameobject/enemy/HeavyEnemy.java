package com.example.battlecity.gameobject.enemy;

import android.graphics.Canvas;

import com.example.battlecity.gameobject.Base;

public class HeavyEnemy extends Enemy{
    public HeavyEnemy(Base base, double positionX, double positionY, double radius) {
        super(base, positionX, positionY, radius);
        setValue(400);
        setLife(4);
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
