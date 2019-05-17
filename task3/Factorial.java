package task3;

import java.math.BigInteger;

class Factorial {

    static BigInteger getFactorial(int number) {
        if (number == 0) {
            return BigInteger.ONE;
        } else if (number > 0) {
            BigInteger factorial = BigInteger.ONE;
            int i = 1;
            while (i <= number) {
                factorial = factorial.multiply(BigInteger.valueOf(i));
                i++;
            }
            return factorial;
        } else {
            return BigInteger.ZERO;
        }
    }

    static int getSumOfDigits(BigInteger factorial) {
        int sum = 0;
        String result = factorial.toString();
        for (int i = 0; i < result.length(); i++) {
            sum += Integer.parseInt(Character.toString(result.charAt(i)));
        }
        return sum;
    }
}
