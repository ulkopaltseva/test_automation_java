package ru.ulko.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by yulia on 24.08.2019.
 */
public class PrimeTests {

    @Test
    public void testPrime(){
        Assert.assertTrue(Primes.isPrime(Integer.MAX_VALUE));
    }

    @Test
    public void testNonPrime(){
        Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE - 1));
    }
}
