package com.example.battlecity.gameobject.enemy;

import android.content.Context;
import android.graphics.Canvas;

import com.example.battlecity.gameobject.Base;

public class HeavyEnemy extends Enemy{
    public HeavyEnemy(Context context,Base base, double positionX, double positionY, double radius) {
        super(context,base, positionX, positionY, radius);
        setValue(400);
        setLife(4);
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
