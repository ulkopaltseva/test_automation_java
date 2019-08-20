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

    public double distance (Point p1, Point p2){
        double xa = p1.getX();
        double xb = p2.getX();
        double ya = p1.getY();
        double yb = p2.getY();

        return Math.sqrt((Math.pow((xb-xa), 2) + Math.pow((yb-ya), 2)));
    }

}
