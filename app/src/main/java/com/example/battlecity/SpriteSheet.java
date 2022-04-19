package com.example.battlecity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class SpriteSheet {
    private Bitmap bitmap;

    public SpriteSheet(Context context){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.sprite_sheet,bitmapOptions);
    }
    public Sprite getPlayerSprite(){
        return new Sprite(this,new Rect(0,0,16,16));
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
