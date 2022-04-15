package com.example.battlecity.gameobject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.battlecity.GameLoop;
import com.example.battlecity.Joystick;
import com.example.battlecity.R;

public class Player extends Tank{
    private static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/ GameLoop.MAX_UPS;


    private final Joystick joystick;

    public Player(Context context,Joystick joystick,double positionX, double positionY, double radius){
        super(positionX,positionY,radius);
        this.joystick=joystick;

        setPaint(new Paint());
        int color = ContextCompat.getColor(context, R.color.magenta);
        getPaint().setColor(color);

    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle((float)getPositionX(),(float) getPositionY(),(float) getRadius(),getPaint() );
    }

    @Override
    public void update() {
        velocityX= joystick.getActuatorX()*MAX_SPEED;
        velocityY= joystick.getActuatorY()*MAX_SPEED;
<<<<<<< Updated upstream:app/src/main/java/com/example/battlecity/gameobject/Player.java
        setPositionX(getPositionX() + velocityX);
        setPositionY(getPositionY() + velocityY);
=======
        positionX+=velocityX;
        positionY+=velocityY;
        if(velocityX != 0 || velocityY !=0){
            //Normalize velocity to get direction
            double distance = getDistanceBetweenTwoPoints(0,0,velocityX,velocityY);
        }
>>>>>>> Stashed changes:app/src/main/java/com/example/battlecity/Player.java
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

    @Override
    public void receiveDamage() {
        die();
    }
}
