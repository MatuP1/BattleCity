package com.example.battlecity.strategies;

import android.graphics.Rect;

public class StrategyMoveRight extends Strategy {
    public StrategyMoveRight(int row, int column) {
        super(row, column);
    }

    @Override
    public Rect getRect() {
        return internalRect(6);
    }
}
