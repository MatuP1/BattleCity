package com.example.battlecity.strategies;

import android.graphics.Rect;

public abstract class Strategy {
    protected int row,column;
    public Strategy(int row,int column){
        this.row = row;
        this.column = column;
    }

    public abstract Rect getRect();

    protected Rect internalRect(int offset) {
        int x = 16*(column+offset);
        int y = 16*row;
        return new Rect(x,y,x+16,y+16);
    }
}
