package com.example.battlecity.gameobject.enemy;

import android.graphics.Canvas;

import com.example.battlecity.gameobject.Base;

public class NormalEnemy extends Enemy{
    public NormalEnemy(Base base, double positionX, double positionY, double radius) {
        super(base,positionX, positionY, radius);
        setValue(100); //Valor del juego original
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle((float) getPositionX(), (float) getPositionY(), (float)getRadius(), getPaint());

    }
}
