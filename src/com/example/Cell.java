package com.example;

/**
 * Created by L T on 2016/6/8.
 */
public class Cell {
    private int x;
    private int y;

    public Cell(){}
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
