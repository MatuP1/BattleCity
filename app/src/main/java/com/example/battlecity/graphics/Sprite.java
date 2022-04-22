package com.example.battlecity.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

import com.example.battlecity.strategies.Strategy;
import com.example.battlecity.gameobject.GameObject;

public class Sprite {
    private final SpriteSheet spriteSheet;
    private Rect rect;
    private GameObject gameObject;
    private Strategy strategy;

    public Sprite(Context context,Strategy strategy, GameObject gameObject) {
        this.spriteSheet = SpriteSheet.getInstance(context);
        this.strategy = strategy;
        this.gameObject = gameObject;
        this.rect = strategy.getRect();
    }
    public Sprite(Context context,Rect rect, GameObject gameObject) {
        this.spriteSheet = SpriteSheet.getInstance(context);
        this.gameObject = gameObject;
        this.rect = rect;
    }

    public Rect getRect() {
        return rect;
    }

    public SpriteSheet getSpriteSheet() {
        return spriteSheet;
    }

    public void draw(Canvas canvas){
        draw(canvas,(int) gameObject.getPositionX(),(int)gameObject.getPositionY());
    }
    public void drawResized(Canvas canvas){

        draw(canvas,(int) gameObject.getPositionX(),(int)gameObject.getPositionY(),32,32);
    }

    public void draw(Canvas canvas, int positionX, int positionY){
        canvas.drawBitmap(
            spriteSheet.getBitmap(),
            rect,
            new Rect(positionX,positionY,positionX+getWidth(),positionY+getHeight()),
            null
        );
    }
    public void draw(Canvas canvas, int positionX, int positionY, int newWidth, int newHeight){
        canvas.drawBitmap(
                spriteSheet.getBitmap(),
                rect,
                new Rect(positionX-newWidth,positionY-newHeight,positionX+newWidth,positionY+newHeight),
                null
        );
    }
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP

        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        //bm.recycle();
        return resizedBitmap;
    }

    public int getWidth() {
        return rect.width();
    }

    public int getHeight() {
        return rect.height();
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;

        rect = strategy.getRect();
    }
}
