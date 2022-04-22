package com.example.battlecity.strategies;

import android.graphics.Rect;

public class StrategyMoveUp extends Strategy {
    public StrategyMoveUp(int row, int column) {
        super(row, column);
    }

    @Override
    public Rect getRect() {
        return internalRect(0);
    }
}
