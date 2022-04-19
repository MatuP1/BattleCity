package com.example.battlecity;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {
    private final SpriteSheet spriteSheet;
    private final Rect rect;

    public Sprite(SpriteSheet spriteSheet, Rect rect) {
        this.spriteSheet = spriteSheet;
        this.rect = rect;
    }

    public void draw(Canvas canvas, int positionX, int positionY){
        canvas.drawBitmap(
            spriteSheet.getBitmap(),
            rect,
            new Rect(positionX,positionY,positionX+getWidth(),positionY+getHeight()),
            null
        );
    }

    public int getWidth() {
        return rect.width();
    }

    public int getHeight() {
        return rect.height();
    }
}
