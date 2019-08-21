package ru.ulko.sandbox;

/**
 * Created by yulia on 21.08.2019.
 */
public class Equation {
    private double a;
    private double b;
    private double c;

    private int n; //количество корней уравнения

    public Equation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;

        double d = b * b - 4 * a * c;

        // если в уравнении a не равно нулю, то имеет смысл вычислять дискриминант
        if (a != 0) {
            if (d > 0) {
                n = 2;
            } else if (d == 0) {
                n = 1;
            } else {
                n = 0;
            }
        } else if (b != 0) { // если же a равно нулю, то уравнение уже превращается в линейное и меет одно решение
            n = 1;
        } else if (c != 0){ // если и b равно нулю, то уравнение имеет 0 решений
            n = 0;
        } else {
            n = -1; // если и c равно нулю, то решений бесконечное множество
        }
    }


    public int rootNumber() {
        return n;
    }
}
