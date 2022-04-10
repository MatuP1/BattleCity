package com.example.battlecity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Player extends Tank{
    private static final double SPEED_PIXELS_PER_SECOND = 400.0;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/GameLoop.MAX_UPS;


    private final Joystick joystick;

    public Player(Context context,Joystick joystick,double positionX, double positionY, double radius){
        super(positionX,positionY,radius);
        this.joystick=joystick;

        paint = new Paint();
        int color = ContextCompat.getColor(context,R.color.magenta);
        paint.setColor(color);

    }
    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle((float)positionX,(float) positionY,(float) radius,paint );
    }

    @Override
    public void update() {
        velocityX= joystick.getActuatorX()*MAX_SPEED;
        velocityY= joystick.getActuatorY()*MAX_SPEED;
        positionX+=velocityX;
        positionY+=velocityY;
    }

    @Override
    public void die() {

    }

    public void setPosition(double x,double y) {
        positionX = x;
        positionY = y;

    }
}
