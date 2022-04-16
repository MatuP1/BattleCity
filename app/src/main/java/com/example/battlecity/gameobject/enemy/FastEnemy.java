package com.example.battlecity.gameobject.enemy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.battlecity.gameobject.Base;

public class FastEnemy extends Enemy {
    public FastEnemy(Context context,Base base, double positionX, double positionY, double radius) {
        super(context,base,positionX, positionY, radius);
        setValue(200); //Valor del juego original
    }

    @Override
    public void draw(Canvas canvas) {
        getPaint().setColor(Color.GREEN);
        canvas.drawCircle((float) getPositionX(), (float) getPositionY(), (float)getRadius(), getPaint());
    }
}
