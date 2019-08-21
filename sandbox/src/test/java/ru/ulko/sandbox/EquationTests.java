package ru.ulko.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by yulia on 21.08.2019.
 */
public class EquationTests {
    @Test
    // Для ситуаций, когда решений у уравнений нет
    public void test0() {
        Equation e = new Equation(1, 1, 1);
        Assert.assertEquals(e.rootNumber(), 0);
    }

    @Test
    // Для ситуаций, когда у уравнения есть одно решение
    public void test1() {
        Equation e = new Equation(1,2,1);
        Assert.assertEquals(e.rootNumber(), 1);
    }

    @Test
    // Для ситуаций, когда у уравнения есть несколько решний
    public void test2(){
        Equation e = new Equation(1,4,1);
        Assert.assertEquals(e.rootNumber(), 2);
    }
}
