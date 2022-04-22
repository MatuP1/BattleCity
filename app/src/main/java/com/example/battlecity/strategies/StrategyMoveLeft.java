package com.example.battlecity.strategies;

import android.graphics.Rect;

public class StrategyMoveLeft extends Strategy {
    public StrategyMoveLeft(int row, int column) {
        super(row, column);
    }

    @Override
    public Rect getRect() {
        return internalRect(2);
    }
}
