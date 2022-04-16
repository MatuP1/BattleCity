package com.example.battlecity.gameobject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.battlecity.GameLoop;
import com.example.battlecity.Joystick;
import com.example.battlecity.R;
import com.example.battlecity.Utils;

public class Player extends Tank{
    public static final int MAX_HEALTH_POINTS = 3;
    private static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/ GameLoop.MAX_UPS;


    private final Joystick joystick;
    private final HealthBar healthBar;

    public Player(Context context,Joystick joystick,double positionX, double positionY, double radius){
        super(positionX,positionY,radius);
        this.joystick=joystick;

        setPaint(new Paint());
        int color = ContextCompat.getColor(context, R.color.magenta);
        getPaint().setColor(color);

        setHealthPoints(MAX_HEALTH_POINTS);
        this.healthBar = new HealthBar(context,this);
    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle((float)getPositionX(),(float) getPositionY(),(float) getRadius(),getPaint() );
        healthBar.draw(canvas);
    }

    @Override
    public void update() {
        setVelocityX(joystick.getActuatorX()*MAX_SPEED);
        setVelocityY(joystick.getActuatorY()*MAX_SPEED);

        setPositionX(getPositionX() + getVelocityX());
        setPositionY(getPositionY() + getVelocityY());

        //positionX+=velocityX;
        //positionY+=velocityY;
        if(getVelocityX() != 0 || getVelocityY()!=0){
            //Normalize velocity to get direction
            double distance = Utils.getDistanceBetweenTwoPoints(0,0,getVelocityX(),getVelocityY());
            setDirectionX(getVelocityX()/distance);
            setDirectionY(getVelocityY()/distance);
        }

    }

    @Override
    public void die() {
        if(getLife()>0)
            respawn();
    }

    private void respawn() {
    }

    public void setPosition(double x,double y) {
        setPositionX(x);
        setPositionY(y);

    }
}
