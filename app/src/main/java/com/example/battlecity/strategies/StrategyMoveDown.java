package com.example.battlecity.strategies;

import android.graphics.Rect;

public class StrategyMoveDown extends Strategy {
    public StrategyMoveDown(int row, int column) {
        super(row, column);
    }

    @Override
    public Rect getRect() {
        return internalRect(4);
    }
}
