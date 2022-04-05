package com.example.battlecity;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private Game gameView;
    private boolean running;
    public static Canvas canvas;
    public MainThread(SurfaceHolder surfaceHolder, Game gameView) {

        super();
        this.surfaceHolder = surfaceHolder;
        this.gameView = gameView;

    }
    @Override
    public void run() {
        while (running) {
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas(); //bloquea el canvas para que solo un hilo dibuje
                synchronized(surfaceHolder) {
                    this.gameView.update();
                    this.gameView.draw(canvas);
                }
            } catch (Exception e) {} finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void setRunning(boolean isRunning) {
        running = isRunning;
    }
}