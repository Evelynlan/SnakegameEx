package com.example;

/**
 * Created by L T on 2016/6/8.
 */
public class Food {
    public  Cell pos=new Cell();
    private int width;
    private int height;
    public final int CELL_SIZE=20;
    public Food(int width,int height) {
        this.width=width;
        this.height=height;
        setNextPos();
    }

    public void setNextPos(){
        int randX=((int)(Math.random()*width))/CELL_SIZE*CELL_SIZE;
        int randY=((int)(Math.random()*height))/CELL_SIZE*CELL_SIZE;
        pos.setX(randX);
        pos.setY(randY);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
