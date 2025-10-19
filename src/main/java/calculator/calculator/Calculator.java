package calculator.calculator;

import static calculator.calculator.CalculatorErrorMessage.SUM_OVERFLOW;

import java.util.List;

public class Calculator {

    private final List<Integer> numbers;

    public Calculator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public int calculate() {
        return calculateSum(numbers);
    }

    private int calculateSum(List<Integer> numbers) {
        int sum = 0;
        for (int number : numbers) {
            validateSumOverflow(sum, number);
            sum += number;
        }
        return sum;
    }

    private void validateSumOverflow(int currentSum, int numberToAdd) {
        if (numberToAdd > 0 && currentSum > Integer.MAX_VALUE - numberToAdd) {
            throw new IllegalArgumentException(SUM_OVERFLOW.getMessage());
        }
    }
}
