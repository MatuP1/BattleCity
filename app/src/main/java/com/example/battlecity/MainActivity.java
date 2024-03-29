package com.example.battlecity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
    private Game game;

    /**
     * Main Activity is the entry point of the app
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        game = new Game(this);
        setContentView(game);

    }

    @Override
    protected void onPause() {
        game.pause();
        super.onPause();

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

    }
}