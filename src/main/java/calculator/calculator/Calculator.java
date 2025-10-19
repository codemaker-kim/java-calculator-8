package calculator.calculator;

import static calculator.calculator.CalculatorErrorMessage.SUM_OVERFLOW;

import java.util.List;

public class Calculator {

    private final List<Double> numbers;

    public Calculator(List<Double> numbers) {
        this.numbers = numbers;
    }

    public double calculate() {
        return calculateSum(numbers);
    }

    private double calculateSum(List<Double> numbers) {
        double sum = 0.0;
        for (double number : numbers) {
            sum += number;
            validateSumOverflow(sum);
        }
        return sum;
    }

    private void validateSumOverflow(double sum) {
        if (Double.isInfinite(sum) || Double.isNaN(sum)) {
            throw new IllegalArgumentException(SUM_OVERFLOW.getMessage());
        }
    }
}
