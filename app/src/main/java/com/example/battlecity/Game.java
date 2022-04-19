package com.example.battlecity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;

import com.example.battlecity.gameobject.Base;
import com.example.battlecity.gameobject.GameObject;
import com.example.battlecity.gameobject.Spell;
import com.example.battlecity.gameobject.enemy.Enemy;
import com.example.battlecity.gameobject.enemy.NormalEnemy;
import com.example.battlecity.gameobject.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private final Base base;
    private final Joystick joystick;
    private final Collection<Enemy> enemyCollection;
    private final Collection<Spell> spellCollection;
    private final Pair<Double,Double> [] spawnPoints = new Pair[3];
    private int lastSpawnPoint;
    public PipeSprite pipe1, pipe2, pipe3;
    public static int gapHeight = 500;
    public static int velocity = 10;
    private final int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private final int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    private GameLoop gameLoop;
    private int joystickPointerID = 0;
    private int numberOfSpellsToCast = 0;
    private final GameOver gameOver;

    public Game(Context context) {
        super(context);

        //get surface holder (no se que es el surface holder) and add a callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        SpriteSheet spriteSheet = new SpriteSheet(getContext());
        //Set the spawn points for the enemies
        spawnPoints[0]=new Pair<>(0.0,0.0);
        spawnPoints[1]=new Pair<>(1000.0,0.0);
        spawnPoints[2]=new Pair<>(2000.0,0.0);
        lastSpawnPoint = 0;

        //Initialize objects that interact with others
        joystick = new Joystick(275, 700, 70,40);
        player = new Player(getContext(),joystick,500,500,30, spriteSheet.getPlayerSprite());
        base = new Base(1000,1000,100);
        enemyCollection = new ArrayList<>();
        spellCollection = new ArrayList<>();
        gameLoop = new GameLoop(this,surfaceHolder);

        //initialize game panels?

        gameOver = new GameOver(getContext());
        setFocusable(true);
        //thread = new MainThread(getHolder(), this);

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }
    public void surfaceCreated(SurfaceHolder holder) {
        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            gameLoop = new GameLoop(this, holder);
        }

        gameLoop.startLoop();
        //makeLevel();


       // thread.setRunning(true);
       // thread.start();

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
        bm.recycle();
        return resizedBitmap;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if(joystick.getIsPressed()){
                    //joystick was pressed before this event -> cast spell
                    numberOfSpellsToCast++;
                } else if(joystick.isPressed((double)event.getX(),(double)event.getY())){
                    //Joystick is pressed in this event
                    joystickPointerID = event.getPointerId(event.getActionIndex());
                    joystick.setIsPressed(true);
                } else{
                    // was not previously pressed
                    numberOfSpellsToCast++;
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if(joystick.getIsPressed()){
                    joystick.setActuator((double)event.getX(),(double)event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if(joystickPointerID == event.getPointerId(event.getActionIndex())){
                    joystick.setIsPressed(false);
                    joystick.resetActuator();
                }
                return true;
        }
        return super.onTouchEvent(event);
    }

    /**
     *
    private void makeLevel() {
        characterSprite = new CharacterSprite
                (getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.bird), 300, 240));
        Bitmap bmp;
        Bitmap bmp2;
        int y;
        int x;
        bmp = getResizedBitmap(BitmapFactory.decodeResource
                (getResources(), R.drawable.pipe_down), 500, Resources.getSystem().getDisplayMetrics().heightPixels / 2);
        bmp2 = getResizedBitmap
                (BitmapFactory.decodeResource(getResources(), R.drawable.pipe_up), 500, Resources.getSystem().getDisplayMetrics().heightPixels / 2);

        pipe1 = new PipeSprite(bmp, bmp2, 2000, 100);
        pipe2 = new PipeSprite(bmp, bmp2, 4500, 100);
        pipe3 = new PipeSprite(bmp, bmp2, 3200, 100);

    }*/

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        /**while (retry) {
            try {
                thread.setRunning(false);
                thread.join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }*/
    }

    public void update() {
        if(player.getHealthPoints() <= 0 || base.getHealthPoints() <= 0){return;}

        //Update game state
        joystick.update();
        player.update();
        base.update();

        //spawn an enemy if it ready;
        if(Enemy.readyToSpawn()){
            Pair spawnPoint = spawnPoints[lastSpawnPoint++ % 3];
            enemyCollection.add(new NormalEnemy(getContext(),base, (Double) spawnPoint.getFirstElement(), (Double) spawnPoint.getSecondElement(),30));
        }
        while (numberOfSpellsToCast>0){
            spellCollection.add(new Spell(getContext(),player));
            numberOfSpellsToCast--;
        }
        for (Enemy enemy:enemyCollection ){
            enemy.update();
        }

        for (Spell spell:spellCollection ){
            spell.update();
        }
        Iterator<Enemy> enemyIterator = enemyCollection.iterator();
        //Check collisions for the enemies
        while (enemyIterator.hasNext()){
            //Replace with Visitor patron
            GameObject enemy = enemyIterator.next();
            if(GameObject.isColliding(enemy,player)){
                enemyIterator.remove();
                player.receiveDamage();
                //Collide with an enemy shouldn't kill the player
                continue;
            }
            if(GameObject.isColliding(enemy,base)){
                enemyIterator.remove();
                base.receiveDamage();
                //Collide with an enemy shouldn't kill the player
                continue;
            }
            Iterator<Spell> spellIterator = spellCollection.iterator();
            while (spellIterator.hasNext()){
                //Replace with Visitor patron
                GameObject spell = spellIterator.next();
                if(GameObject.isColliding(spell,enemy)){
                    spellIterator.remove();
                    enemyIterator.remove();
                    break;
                }
                if(GameObject.isColliding(spell,base)){
                    spellIterator.remove();
                    base.receiveDamage();
                    break;
                }
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {

        super.draw(canvas);

        drawUPS(canvas);
        drawFPS(canvas);
        joystick.draw(canvas);
        player.draw(canvas);
        base.draw(canvas);

        for (Enemy enemy:enemyCollection ){
            enemy.draw(canvas);
        }
        for (Spell spell:spellCollection ){
            spell.draw(canvas);
        }
        //Draw Game over
        if(player.getHealthPoints() <= 0 || base.getHealthPoints() <= 0){
            gameOver.draw(canvas);
        }
    }

    public void drawUPS(Canvas canvas){
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS "+averageUPS,100,100,paint);
    }

    public void drawFPS(Canvas canvas){
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(getContext(), R.color.magenta);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS "+averageFPS,100,200,paint);
    }

    public void pause() {
        gameLoop.stopLoop();
    }
}