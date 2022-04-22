package com.example.battlecity.gameobject.terrain;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.battlecity.gameobject.GameObject;
import com.example.battlecity.graphics.Sprite;
import com.example.battlecity.strategies.StrategyMoveDown;

public class Iron extends World {


    public Iron(Context context, double positionX, double positionY, double radius) {
        super(context, positionX, positionY, radius);
        setSprite(new Sprite(context,new Rect(16*16,16,16*16+8,16+8),this));
    }

    @Override
    public void draw(Canvas canvas) {
        getSprite().draw(canvas,(int)getPositionX(),(int)getPositionY(),24,24);
    }

    @Override
    public void update() {

    }

    @Override
    public void die() {

    }

    @Override
    public void receiveDamage() {

    }
}
