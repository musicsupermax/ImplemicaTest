package task3;

import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        BigInteger number = Factorial.getFactorial(100);
        System.out.println(Factorial.getSumOfDigits(number));
    }
}
