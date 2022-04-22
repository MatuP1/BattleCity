package com.example.battlecity.gameobject.terrain;

import android.content.Context;
import android.graphics.Canvas;

import com.example.battlecity.gameobject.GameObject;

public abstract class World extends GameObject {

    public World(Context context,double positionX, double positionY, double radius) {
        super(context,positionX, positionY, radius);
    }

}
