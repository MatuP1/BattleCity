package com.example.battlecity.gameobject.enemy;

import android.content.Context;
import android.graphics.Canvas;

import com.example.battlecity.strategies.StrategyMoveDown;
import com.example.battlecity.gameobject.Base;
import com.example.battlecity.graphics.Sprite;
import com.example.battlecity.strategies.StrategyMoveLeft;
import com.example.battlecity.strategies.StrategyMoveRight;
import com.example.battlecity.strategies.StrategyMoveUp;


public class NormalEnemy extends Enemy {
    public NormalEnemy(Context context, Base base, double positionX, double positionY, double radius) {
        super(context,base,positionX, positionY, radius);
        setValue(100); //Valor del juego original

        setSprite(new Sprite(context,new StrategyMoveDown(4,8),this));
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void updateStrategy(double dirX, double dirY) {
        if(dirX != 0)
            getSprite().setStrategy(dirX < 0 ? new StrategyMoveLeft(4, 8) : new StrategyMoveRight(4, 8));
        else
            getSprite().setStrategy(dirY > 0 ? new StrategyMoveDown(4, 8) : new StrategyMoveUp(4, 8));
    }
}
