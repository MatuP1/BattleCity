package com.example.battlecity.gameobject.enemy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.battlecity.gameobject.Base;
import com.example.battlecity.strategies.StrategyMoveDown;
import com.example.battlecity.strategies.StrategyMoveLeft;
import com.example.battlecity.strategies.StrategyMoveRight;
import com.example.battlecity.strategies.StrategyMoveUp;

public class FastEnemy extends Enemy {
    public FastEnemy(Context context,Base base, double positionX, double positionY, double radius) {
        super(context,base,positionX, positionY, radius);
        setValue(200); //Valor del juego original
    }

    @Override
    public void updateStrategy(double dirX, double dirY) {
        if(dirX != 0)
            getSprite().setStrategy(dirX < 0 ? new StrategyMoveLeft(6, 8) : new StrategyMoveRight(5, 8));
        else
            getSprite().setStrategy(dirY > 0 ? new StrategyMoveDown(6, 8) : new StrategyMoveUp(5, 8));
    }

    @Override
    public void draw(Canvas canvas) {
        getPaint().setColor(Color.GREEN);
        canvas.drawCircle((float) getPositionX(), (float) getPositionY(), (float)getRadius(), getPaint());
    }
}
