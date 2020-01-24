package com.mycomany.fizzbuzz;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FizzBuzz {

    private static final String FIZZ_BUZZ = "FizzBuzz";
    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";


    private final Predicate<Integer> divisibleBy3And5 = number -> (number % 3 == 0 && number % 5 == 0);
    private final Predicate<Integer> divisibleBy5 = number -> number % 5 == 0;
    private final Predicate<Integer> divisibleBy3 = number -> number % 3 == 0;

    private final Predicate<Integer> divisibleBy3OrContains3 = number -> (divisibleBy3.test(number) || String.valueOf(number).contains("3"));
    private final Predicate<Integer> divisibleBy5OrContains5 = number -> (divisibleBy5.test(number) || String.valueOf(number).contains("5"));
    private final Predicate<Integer> divisibleByOrContains3And5 = number -> (divisibleBy3OrContains3.test(number) && divisibleBy5OrContains5.test(number));


    /**
     * Prints out the following for contiguous range of numbers
     *  the number
     *  fizz for numbers that are multiples of 3
     *  buzz for numbers that are multiples of 5
     *  fizzbuzz for numbers that are multiples of 3 & 5
     *
     * @param n the max number to print till
     * @return list of strings
     */
    public List<String> fizzBuzz(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Invalid value passed " + n);
        }
        List<String> list = new ArrayList<>(n);
        Stream<Integer> numbers = IntStream.iterate(1, e -> e + 1).boxed();


        Stream<String> stream = numbers.map(String::valueOf)
                .map(number -> getValue(number, divisibleBy3And5, () -> FIZZ_BUZZ))
                .map(number -> getValue(number, divisibleBy3, () -> FIZZ))
                .map(number -> getValue(number, divisibleBy5, () -> BUZZ));

        stream.limit(n).forEach((ele) -> {
            list.add(ele);
            System.out.println(ele);
        });

        return list;
    }


    /**
     * Prints out the following for contiguous range of numbers
     *  the number
     *  fizz for numbers that are multiples of 3 or have 3 in it
     *  buzz for numbers that are multiples of 5 or have 5 in it
     *  fizzbuzz for numbers that satisfy both conditions above
     *
     * @param n the max number to print till
     * @return list of strings
     */

    public List<String> fizzBuzzEnhanced(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Invalid value passed " + n);
        }
        List<String> list = new ArrayList<>(n);
        Stream<Integer> numbers = IntStream.iterate(1, e -> e + 1).boxed();

        Stream<String> stream = numbers.map(String::valueOf)
                .map(number -> getValue(number, divisibleByOrContains3And5, () -> FIZZ_BUZZ))
                .map(number -> getValue(number, divisibleBy3.or(divisibleBy3OrContains3), () -> FIZZ))
                .map(number -> getValue(number, divisibleBy5.or(divisibleBy5OrContains5), () -> BUZZ));

        stream.limit(n).forEach((ele) -> {
            list.add(ele);
            System.out.println(ele);
        });

        return list;
    }


    private String getValue(String number, Predicate<Integer> predicate, Supplier<String> supplier) {

        try {
            if (predicate.test(Integer.valueOf(number))) {
                return supplier.get();
            }
            return number;
        } catch (NumberFormatException ne) {
            return number;
        }
    }
}
