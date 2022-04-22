package com.example.battlecity.gameobject.bullet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.battlecity.GameLoop;
import com.example.battlecity.gameobject.GameObject;
import com.example.battlecity.gameobject.Spell;
import com.example.battlecity.gameobject.enemy.Enemy;

public class EnemyBullet extends Spell {

    private static final double SPEED_PIXELS_PER_SECOND = 800.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/ GameLoop.MAX_UPS;
    private Enemy enemy;
    public EnemyBullet(Context context, Enemy enemy)
    {
        super(context, enemy);
        setVelocityX(enemy.getDirectionX() * MAX_SPEED);
        setVelocityY(enemy.getDirectionY() * MAX_SPEED);
        setPaint(new Paint());
        getPaint().setColor(Color.RED);
    }


    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle((float)getPositionX(),(float) getPositionY(),(float) getRadius(),getPaint() );
    }

    @Override
    public void update() {
        setPositionX(getPositionX() + getVelocityX());
        setPositionY(getPositionY() + getVelocityY());
    }

    @Override
    public void die() {

    }

    @Override
    public void receiveDamage() {

    }
}
