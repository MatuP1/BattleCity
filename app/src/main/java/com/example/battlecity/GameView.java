package com.example.battlecity;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private CharacterSprite characterSprite;
    public GameView(Context context) {
        super(context);
        thread = new MainThread(getHolder(),this);
        setFocusable(true);
        getHolder().addCallback(this);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        characterSprite = new CharacterSprite(BitmapFactory.decodeResource(getResources(),R.drawable.avdgreen));

        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) { //this is a loop because it can take multiple attempts to stop a thread
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }
    public void update() {
        characterSprite.update();

    }

    @Override
    public void draw(Canvas canvas)
    {

        super.draw(canvas);
        if(canvas!=null) {
            characterSprite.draw(canvas);

        }
    }
}
