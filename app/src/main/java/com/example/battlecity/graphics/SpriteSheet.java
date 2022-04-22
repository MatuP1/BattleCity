package com.example.battlecity.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.battlecity.R;
import com.example.battlecity.graphics.Sprite;

public class SpriteSheet  {
    private Bitmap bitmap;
    private Context context;
    private static SpriteSheet instance;
    private SpriteSheet(Context context){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        this.context = context;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_sheet_2,bitmapOptions);
    }
    public static SpriteSheet getInstance(Context context) {
        if (instance == null) {
            instance = new SpriteSheet(context);
        }
        return instance;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Bitmap getPlayerIdleUp(){return BitmapFactory.decodeResource(context.getResources(),R.drawable.sprite_player_idle_up);}

    public Bitmap getPlayerIdleLeft(){return bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.sprite_player_idle_left);}

    public Bitmap getPlayerIdleDown(){return bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.sprite_player_idle_down);}

    public Bitmap getPlayerIdleRight(){return bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.sprite_player_idle_right);}

    //Normal Enemy
    public Bitmap getNormalEnemyIdleUp(){return BitmapFactory.decodeResource(context.getResources(),R.drawable.sprite_normal_enemy_idle_up);}

    public Bitmap getNormalEnemyIdleLeft(){return bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.sprite_normal_enemy_idle_left);}

    public Bitmap getNormalEnemyIdleDown(){return bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.sprite_normal_enemy_idle_down);}

    public Bitmap getNormalEnemyIdleRight(){return bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.sprite_normal_enemy_idle_right);}

    //Fast Enemy
    public Bitmap getFastEnemyIdleUp(){return bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.sprite_fast_enemy_idle_up);}

    public Bitmap getFastEnemyIdleLeft(){return bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.sprite_fast_enemy_idle_left);}

    public Bitmap getFastEnemyIdleDown(){return bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.sprite_fast_enemy_idle_down);}

    public Bitmap getFastEnemyIdleRight(){return bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.sprite_fast_enemy_idle_right);}
}
