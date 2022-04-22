package com.example.battlecity.gameobject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.battlecity.GameLoop;
import com.example.battlecity.Joystick;
import com.example.battlecity.R;
import com.example.battlecity.states.powerup.HelmetState;
import com.example.battlecity.strategies.StrategyMoveDown;
import com.example.battlecity.graphics.Sprite;
import com.example.battlecity.Utils;
import com.example.battlecity.strategies.StrategyMoveLeft;
import com.example.battlecity.strategies.StrategyMoveRight;
import com.example.battlecity.strategies.StrategyMoveUp;

public class Player extends Tank{
    public static final int MAX_HEALTH_POINTS = 3;
    private static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/ GameLoop.MAX_UPS;


    private final Joystick joystick;
    private final HealthBar healthBar;

    public Player(Context context,Joystick joystick,double positionX, double positionY, double radius){
        super(context,positionX,positionY,radius);
        this.joystick=joystick;

        setPaint(new Paint());
        int color = ContextCompat.getColor(context, R.color.magenta);
        getPaint().setColor(color);

        setHealthPoints(MAX_HEALTH_POINTS);
        this.healthBar = new HealthBar(context,this);

        setSprite(new Sprite(context,new StrategyMoveUp(0,0),this));
        setState(new HelmetState(this));
        setState(0);
    }
    @Override
    public void draw(Canvas canvas) {
       super.draw(canvas);
       healthBar.draw(canvas);
    }

    @Override
    public void updateStrategy(double dirX, double dirY) {
        if(dirX != 0)
            getSprite().setStrategy(dirX < 0 ? new StrategyMoveLeft(0, 0) : new StrategyMoveRight(0, 0));
        else
            getSprite().setStrategy(dirY > 0 ? new StrategyMoveDown(0, 0) : new StrategyMoveUp(0, 0));
    }

    @Override
    public void update() {
        setVelocityX(getVelocityX()*MAX_SPEED);
        setVelocityY(getVelocityY()*MAX_SPEED);
        //positionX+=velocityX;
        //positionY+=velocityY;

        if(getVelocityX() != 0 || getVelocityY()!=0){
            if(Math.abs(getVelocityX()) >= Math.abs(getVelocityY())){
                //Normalize velocity to get direction
                double distance = Utils.getDistanceBetweenTwoPoints(0,0,getVelocityX(),getVelocityY());
                setDirectionX(getVelocityX()/distance);
                setPositionX(getPositionX() + getVelocityX());
                setVelocityY(0);
                setDirectionY(0);

            }else{
                double distance = Utils.getDistanceBetweenTwoPoints(0,0,getVelocityX(),getVelocityY());
                setDirectionY(getVelocityY()/distance);
                setPositionY(getPositionY() + getVelocityY());
                setVelocityX(0);
                setDirectionX(0);

            }
            updateStrategy(getDirectionX(),getDirectionY());

        }
        state.update();
    }

    @Override
    public void die() {
        if(getLife()>0)
            respawn();
    }

    private void respawn() {
        setPosition(810,1000);
    }

    public void setPosition(double x,double y) {
        setPositionX(x);
        setPositionY(y);

    }
}
