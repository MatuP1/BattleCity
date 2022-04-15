package com.example.battlecity;

import android.graphics.Canvas;

public class Spell extends GameObject{
    private final double velocityX;
    private final double velocityY;
    private Player spellcaster;

    private static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/GameLoop.MAX_UPS;

    public Spell(Player player) {
        super(
                player.getPositionX(),
                player.getPositionY(),
                25);
        spellcaster = player;

        velocityX = spellcaster.getDirectionX() * MAX_SPEED;
        velocityY = spellcaster.getDirectionY() * MAX_SPEED;
    }

    @Override
    public void draw(Canvas canvas) {

    }
    @Override
    public void update() {

    }

    @Override
    public void die() {

    }
}
