package org.example;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        int a = 5;
        int b = 5;
        int expectedResult = 25;
        int actualResult = 5 * 5;
        Assert.assertEquals(expectedResult, actualResult);
    }
}
