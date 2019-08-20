package ru.ulko.sandbox;

/**
 * Created by yulia on 20.08.2019.
 */
public class Point {
    private int x;
    private int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getXY(){
        return String.valueOf(getX()) + "," + String.valueOf(getY());
    }

}
