package com.mycomany.fizzbuzz;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FizzBuzzTest {

    private FizzBuzz obj = new FizzBuzz();

    @Test
    public void emptyFizzBuzz() {
        List<String> list = obj.fizzBuzz(0);
        assertEquals(0, list.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeFizzBuzz() {
        obj.fizzBuzz(-1);
    }


    @Test
    public void fizzBuzz() {

        List<String> list = obj.fizzBuzz(100);

        assertEquals(100, list.size());
        assertEquals(6, list.stream().filter(ele -> ele.equals("FizzBuzz")).count());
        assertEquals(14, list.stream().filter(ele -> ele.equals("Buzz")).count());
        assertEquals(27, list.stream().filter(ele -> ele.equals("Fizz")).count());
        assertEquals(53, list.stream().filter(this::isNumber).count());

    }

    @Test
    public void fizzBuzzEnhanced() {

        List<String> list = obj.fizzBuzzEnhanced(100);

        assertEquals(100, list.size());
        assertEquals(11, list.stream().filter(ele -> ele.equals("FizzBuzz")).count());
        assertEquals(17, list.stream().filter(ele -> ele.equals("Buzz")).count());
        assertEquals(34, list.stream().filter(ele -> ele.equals("Fizz")).count());
        assertEquals(38, list.stream().filter(this::isNumber).count());

    }


    @Test(expected = IllegalArgumentException.class)
    public void negativeFizzBuzzEnhanced() {
        obj.fizzBuzzEnhanced(-1);
    }

    private boolean isNumber(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException ne) {
            return false;
        }
    }
}