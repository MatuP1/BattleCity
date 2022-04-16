package com.example.battlecity.gameobject.enemy;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Canvas;

import com.example.battlecity.gameobject.Base;

public class StrongEnemy extends Enemy{
    public StrongEnemy(Context context,Base base, double positionX, double positionY, double radius) {
        super(context,base, positionX, positionY, radius);
        setValue(300);
    }

    @Override
    public void draw(Canvas canvas) {

    }
}
