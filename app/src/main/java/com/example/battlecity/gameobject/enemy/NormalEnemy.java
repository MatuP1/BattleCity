package com.example.battlecity.gameobject.enemy;

import android.content.Context;
import android.graphics.Canvas;

import com.example.battlecity.gameobject.Base;

public class NormalEnemy extends Enemy{
    public NormalEnemy(Context context, Base base, double positionX, double positionY, double radius) {
        super(context,base,positionX, positionY, radius);
        setValue(100); //Valor del juego original
    }

    @Override
    public void draw(Canvas canvas) {
        //dibujar sprite correcto
        canvas.drawCircle((float) getPositionX(), (float) getPositionY(), (float)getRadius(), getPaint());

    }
}
