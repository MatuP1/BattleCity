package com.example.battlecity.gameobject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.battlecity.GameLoop;
import com.example.battlecity.R;
import com.example.battlecity.gameobject.Player;

public class Spell extends GameObject {
    private Tank spellcaster;

    private static final double SPEED_PIXELS_PER_SECOND = 800.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/ GameLoop.MAX_UPS;

    public Spell(Context context, Tank player) {
        super(
                context,
                player.getPositionX(),
                player.getPositionY(),
                25);
        spellcaster = player;
        setPaint(new Paint());
        int color = ContextCompat.getColor(context, R.color.spell);
        getPaint().setColor(color);
        setVelocityX(spellcaster.getDirectionX() * MAX_SPEED);
        setVelocityY(spellcaster.getDirectionY() * MAX_SPEED);
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
