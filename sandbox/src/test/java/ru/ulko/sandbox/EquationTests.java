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

    @Test
    // для ситуации, когда уравнение является линейным и имеет только одно решение
    public void testLinear(){
        Equation e = new Equation(0, 1, 1);
        Assert.assertEquals(e.rootNumber(), 1);
    }

    @Test
    //для ситуаций, когда уравнение является вырожденным и имеет 0 решений
    public void testConstant(){
        Equation e = new Equation(0, 0, 1);
        Assert.assertEquals(e.rootNumber(), 0);
    }

    @Test
    //для ситуаций, когда уравнение является 0, то есть имеет бесконечное множество решений
    public void testZero(){
        Equation e = new Equation(0, 0, 0);
        Assert.assertEquals(e.rootNumber(), -1);
    }
}
