package com.example;

import javax.swing.*;
import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by L T on 2016/6/8.
 */
public class Snake {
    public List<Cell> cells=new LinkedList<Cell>();
    public int width;
    public int height;
    public int score;
    public int dir=DIR_RIGTH;
    public static final int CELL_SIZE=20;
    public Food food;
    public  int DELAY=100;


    public static final int DIR_LEFT=0;
    public static final int DIR_UP=1;
    public static final int DIR_RIGTH=2;
    public static final int DIR_DOWN=3;
    public Snake(int width,int height,Cell cell,int dir) {
        this.width=width;
        this.height=height;
        init(cell,dir);
    }
    public void init(Cell cell,int dir){
        food=new Food(width,height);
        cells.clear();
        score=0;
        this.dir=dir;
        cells.add(cell);
    }
    public void move(){
        Cell cell;
        int x=cells.get(cells.size()-1).getX();
        int y=cells.get(cells.size()-1).getY();
        switch (dir){
            case DIR_LEFT:
                cell=new Cell(x-CELL_SIZE,y);
                cells.add(cell);
                break;
            case DIR_UP:
                cell=new Cell(x,y-CELL_SIZE);
                cells.add(cell);
                break;
            case DIR_RIGTH:
                cell=new Cell(x+CELL_SIZE,y);
                cells.add(cell);
                break;
            case DIR_DOWN:
                cell=new Cell(x,y+CELL_SIZE);
                cells.add(cell);
                break;
            default:
                break;
        }
        if(ateFood()){
            food.setNextPos();
            score+=10;
        }else{
            cells.remove(0);
        }
    }
    public void changeDir(int dir){
        switch (this.dir){
            case DIR_UP:
                if(dir!=DIR_DOWN){
                    this.dir=dir;
                }
                break;
            case DIR_DOWN:
                if(dir!=DIR_UP){
                    this.dir=dir;
                }
                break;
            case DIR_LEFT:
                if(dir!=DIR_RIGTH){
                    this.dir=dir;
                }
                break;
            case DIR_RIGTH:
                if(dir!=DIR_LEFT){
                    this.dir=dir;
                }
                break;
            default:
                break;
        }
    }
    public boolean ateFood(){
        int x=cells.get(cells.size()-1).getX();
        int y=cells.get(cells.size()-1).getY();
        if(x==food.pos.getX()
                &&y==food.pos.getY()){
            return true;
        }
        return false;
    }
    public boolean hitBoundary(){
        int x=cells.get(cells.size()-1).getX();
        int y=cells.get(cells.size()-1).getY();
        if(x<0 || y<0 || x>width || y>height){
            //JOptionPane.showMessageDialog(null,"hit");
            return true;
        }
        return false;
    }
    public boolean biteItself(){
        int x=cells.get(cells.size()-1).getX();
        int y=cells.get(cells.size()-1).getY();
        for(int i=0;i<cells.size()-1;i++){
            if(cells.get(i).getX()==x
                    &&cells.get(i).getY()==y){
                //JOptionPane.showMessageDialog(null,"bite");
                return true;
            }
        }
        return false;
    }
    public boolean isGameOver(){
        if(hitBoundary()
                ||biteItself()){
            return true;
        }
        return false;
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
