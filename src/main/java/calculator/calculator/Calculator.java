package calculator.calculator;

import java.util.List;

public class Calculator {

    private final List<Integer> numbers;

    public Calculator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public int calculate() {
        return calculateSum(numbers);
    }

    public int calculateSum(List<Integer> numbers) {
        int sum = 0;
        for (int number : numbers) {
            validateSumOverflow(sum, number);
            sum += number;
        }
        return sum;
    }

    private void validateSumOverflow(int currentSum, int numberToAdd) {
        // 오버플로우 체크
        if (numberToAdd > 0 && currentSum > Integer.MAX_VALUE - numberToAdd) {
            throw new IllegalArgumentException("합계가 숫자 범위를 초과했습니다.");
        }
    }
}
