package ru.ulko.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by yulia on 24.08.2019.
 */
public class PrimeTests {

    @Test
    public void testPrimeFor(){
        Assert.assertTrue(Primes.isPrimeFor(Integer.MAX_VALUE));
    }

    @Test
    public void testNonPrimeFor(){
        Assert.assertFalse(Primes.isPrimeFor(Integer.MAX_VALUE - 1));
    }

    @Test(enabled = false)
    public void testPrimeWhile(){
        Assert.assertTrue(Primes.isPrimeWhile(Integer.MAX_VALUE));
    }

    @Test(enabled = false)
    public void testNonPrimeWhile(){
        Assert.assertFalse(Primes.isPrimeWhile(Integer.MAX_VALUE - 1));
    }

    @Test(enabled = false)
    public void testPrimeForLong(){
        long n = Integer.MAX_VALUE;
        Assert.assertTrue(Primes.isPrimeFor(n));
    }
}
