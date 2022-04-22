package com.example.battlecity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.core.content.ContextCompat;

import com.example.battlecity.gameobject.Base;
import com.example.battlecity.gameobject.GameObject;
import com.example.battlecity.gameobject.Spell;
import com.example.battlecity.gameobject.bullet.EnemyBullet;
import com.example.battlecity.gameobject.enemy.Enemy;
import com.example.battlecity.gameobject.enemy.NormalEnemy;
import com.example.battlecity.gameobject.Player;
import com.example.battlecity.gameobject.terrain.World;
import com.example.battlecity.graphics.SpriteSheet;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private final Base base;
    private final Joystick joystick;
    private final List<Enemy> enemyCollection;
    private final List<Spell> spellCollection;
    private final List<EnemyBullet> enemyBulletCollection;
    private final List<World> terrainCollection;
    private final Pair<Double,Double> [] spawnPoints = new Pair[3];
    private int lastSpawnPoint;
    private SpriteSheet spriteSheet;
    public static int gapHeight = 500;
    public static int velocity = 10;
    private final int screenHeight ;
    private final int screenWidth ;

    private GameLoop gameLoop;
    private int joystickPointerID = 0;
    private int numberOfSpellsToCast = 0;
    private final GameOver gameOver;

    public Game(Context context) {
        super(context);

        //get surface holder (no se que es el surface holder) and add a callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        //Set the spawn points for the enemies
        spawnPoints[0]=new Pair<>(600.0,48.0);
        spawnPoints[1]=new Pair<>(1206.0,48.0);
        spawnPoints[2]=new Pair<>(1820.0,48.0);
        lastSpawnPoint = 0;

        //Initialize objects that interact with others
        joystick = new Joystick(275, 700, 200,70);

        player = new Player(getContext(),joystick,810,1000,20);
        //PlayerSprite ps = new PlayerSprite(player,spriteSheet);
        //player.setSprite(ps);

        base = new Base(getContext(),1206,1000,32);
        enemyCollection = new ArrayList<>();
        spellCollection = new ArrayList<>();
        terrainCollection = new ArrayList<>();
        enemyBulletCollection = new ArrayList<>();
        gameLoop = new GameLoop(this,surfaceHolder);

        //initialize game panels?

        gameOver = new GameOver(getContext());
        setFocusable(true);
        //thread = new MainThread(getHolder(), this);
        MapBuilder.createWorld(getContext(),this);

        screenWidth = Math.max(Resources.getSystem().getDisplayMetrics().heightPixels,Resources.getSystem().getDisplayMetrics().widthPixels);
        screenHeight = Math.min(Resources.getSystem().getDisplayMetrics().heightPixels,Resources.getSystem().getDisplayMetrics().widthPixels);
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
        if(player.getHealthPoints() <= 0 || base.getHealthPoints() <= 0){return;} //Game Over


        //Update game state
        joystick.update();
        player.setVelocityX(joystick.getActuatorX());
        player.setVelocityY(joystick.getActuatorY());

        player.checkOutOfBounds(screenHeight,screenWidth);
        base.update();

        //spawn an enemy if it ready;
        if(Enemy.readyToSpawn() && enemyCollection.size()<4){
            Pair spawnPoint = spawnPoints[lastSpawnPoint++ % 3];
            Enemy actualEnemy = new NormalEnemy(getContext(),base, (Double) spawnPoint.getFirstElement(), (Double) spawnPoint.getSecondElement(),30);
            enemyCollection.add(actualEnemy);
            //EnemySprite ps = new NormalEnemySprite(actualEnemy,spriteSheet);
            //actualEnemy.setSprite(ps);
        }
        //Shoot multiple spells
        while (numberOfSpellsToCast>0){
            spellCollection.add(new Spell(getContext(),player));
            numberOfSpellsToCast--;
        }
        //update all enemys
        for (Enemy enemy:enemyCollection ){
            enemy.update();
            if(enemy.readyToShoot())
                enemyBulletCollection.add(enemy.shoot());
        }
        for(EnemyBullet bullet : enemyBulletCollection)
            bullet.update();
        //update all spells
        for (Spell spell:spellCollection ){
            spell.update();
        }
        //update all spells
        for (World terrain:terrainCollection ){
            terrain.update();
        }
        Iterator<Enemy> enemyIterator = enemyCollection.iterator();
        //Check collisions for the enemies
        while (enemyIterator.hasNext()){
            //Replace with Visitor patron
            Enemy enemy = enemyIterator.next();
            if(enemy.checkOutOfBounds(screenHeight,screenWidth)){
                enemy.canMove(false);
                continue;
            }
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
            for(World terrain: terrainCollection){
                if(GameObject.isColliding(enemy,terrain)){
                    enemy.canMove(false);
                }
            }
            for(Enemy e: enemyCollection){
                if(enemy != e && GameObject.isColliding(enemy,e)){
                    enemy.canMove(false);
                }
            }

        }
        Iterator<Spell> spellIterator = spellCollection.iterator();

        while (spellIterator.hasNext()){
            //Replace with Visitor patron
            GameObject spell = spellIterator.next();
            if(spell.checkOutOfBounds(screenHeight,screenWidth)){
                spellIterator.remove();
                break;
            }
            for(Enemy enemy:enemyCollection){
                if(GameObject.isColliding(spell,enemy)){
                    spellIterator.remove();
                    enemyCollection.remove(enemy);
                    break;
                }
            }
            if(GameObject.isColliding(spell,base)){
                spellIterator.remove();
                base.receiveDamage();
                break;
            }
            for(World terrain: terrainCollection){
                if(GameObject.isColliding(spell,terrain)){
                    terrain.receiveDamage();
                    if(terrain.getLife()<=0){
                        terrain.destroy();
                        terrainCollection.remove(terrain);
                    }
                    spell.destroy();
                    spellIterator.remove();
                    break;
                }

            }
        }

        for(World terrain: terrainCollection){
            if(GameObject.isColliding(terrain,player)) {
                player.setPosition(
                        player.getPositionX()- 16*player.getDirectionX(),
                        player.getPositionY()- 16*player.getDirectionY()
                );
            }
        }

        Iterator<EnemyBullet> enemyBulletIterator = enemyBulletCollection.iterator();

        while(enemyBulletIterator.hasNext()) {
            EnemyBullet bullet = enemyBulletIterator.next();
            if(bullet.checkOutOfBounds(screenHeight,screenWidth)){
                enemyBulletIterator.remove();
                break;
            }
            Iterator<World> terrainIterator = terrainCollection.iterator();
            while(terrainIterator.hasNext()){
                World terrain = terrainIterator.next();
                if (GameObject.isColliding(terrain, bullet)) {
                    terrain.receiveDamage();
                    if (terrain.getLife() <= 0) {
                        terrainIterator.remove();
                        terrain.destroy();
                        terrainCollection.remove(terrain);
                    }
                    enemyBulletIterator.remove();
                    bullet.destroy();
                    enemyBulletCollection.remove(bullet);
                    break;
                }
            }
            if (GameObject.isColliding(bullet, player)) {
                player.receiveDamage();
                enemyBulletIterator.remove();
                bullet.destroy();
                enemyBulletCollection.remove(bullet);
                break;
            }
        }

        player.update();

    }

    @Override
    public void draw(Canvas canvas) {

        super.draw(canvas);
        //canvas.drawBitmap();
        //drawUPS(canvas);
        //drawFPS(canvas);
        joystick.draw(canvas);
        player.draw(canvas);
        base.draw(canvas);

        for (Enemy enemy:enemyCollection ){
            enemy.draw(canvas);
        }
        for (Spell spell:spellCollection ){
            spell.draw(canvas);
        }
        for (World terrain:terrainCollection ){
            terrain.draw(canvas);
        }
        for(EnemyBullet bullet : enemyBulletCollection)
            bullet.draw(canvas);
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

    public void addTerrain(World world){
        terrainCollection.add(world);
    }
}