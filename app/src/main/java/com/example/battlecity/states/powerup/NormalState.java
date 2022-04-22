package com.example.battlecity.states.powerup;

import android.graphics.Canvas;

import com.example.battlecity.GameLoop;
import com.example.battlecity.Pair;
import com.example.battlecity.gameobject.GameObject;
import com.example.battlecity.gameobject.Tank;
import com.example.battlecity.states.powerup.State;

import java.util.Random;

public class NormalState extends State {

    private static final double SPEED_PIXELS_PER_SECOND = 400.0 * 0.6;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/ GameLoop.MAX_UPS;
    private Pair<Integer,Integer> dir = new Pair<>(0,1);
    public NormalState(Tank tank) {
        super(tank);
    }

    @Override
    public void draw(Canvas canvas) {
        getTank().getSprite().drawResized(canvas);
    }

    @Override
    public void update() {

    }

    @Override
    public void move() {
       /** double distanceToBaseX = getTank().getBase().getPositionX() - getTank().getPositionX();
        double distanceToBaseY = getTank().getBase().getPositionY() - getTank().getPositionY();

        double distanceToBase = GameObject.distanceBetweenTwoObjects(getTank(),getTank().getBase());

        double directionX = distanceToBaseX/distanceToBase;
        double directionY = distanceToBaseY/distanceToBase;*/


        if(!getTank().canMove()){
            move(new Pair<>(dir.getFirstElement()*-1, dir.getSecondElement()*-1 ));
            int newDir = new Random().nextInt(4);
            switch (newDir){
                case 0:
                    dir = new Pair<>(0,1);
                    break;
                case 1:
                    dir = new Pair<>(1,0);
                    break;
                case 2:
                    dir = new Pair<>(0,-1);
                    break;
                case 3:
                    dir = new Pair<>(-1,0);
                    break;
            }
            getTank().canMove(true);
        }
        else
            move(dir);

        //Update position
        getTank().setPositionX(getTank().getPositionX() + getTank().getVelocityX());
        getTank().setPositionY(getTank().getPositionY() + getTank().getVelocityY());
    }
    private void move(Pair<Integer,Integer> dir){
        getTank().setVelocityX(dir.getFirstElement()*MAX_SPEED);
        getTank().setVelocityY(dir.getSecondElement()*MAX_SPEED);

        getTank().setDirectionX(dir.getFirstElement());
        getTank().setDirectionY(dir.getSecondElement());
        getTank().updateStrategy(dir.getFirstElement(), dir.getSecondElement());
    }

}
