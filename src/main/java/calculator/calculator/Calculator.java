package calculator.calculator;

import calculator.delimiter.DelimiterExtractor;
import calculator.io.input.Input;
import calculator.io.input.NumberParser;
import calculator.io.input.validator.InputValidator;

public class Calculator {

    private final Input input;

    public Calculator(Input input) {
        this.input = input;
    }

    public int calculate() {
        String value = input.getValue();

        if (InputValidator.isEmpty(value)) {
            return 0;
        }

        // 구분자 패턴 구성
        String delimiterPattern = DelimiterExtractor.getDefaultDelimiterPattern();

        if (DelimiterExtractor.hasCustomDelimiter(value)) {
            String customDelimiter = DelimiterExtractor.extractCustomDelimiter(value);
            delimiterPattern += "|" + customDelimiter;
            value = DelimiterExtractor.extractExpression(value);
        }

        // 숫자 파싱
        int[] numbers = NumberParser.parseNumbers(value, delimiterPattern);

        // 합계 계산
        return calculateSum(numbers);
    }

    public static int calculateSum(int[] numbers) {
        int sum = 0;
        for (int number : numbers) {
            validateSumOverflow(sum, number);
            sum += number;
        }
        return sum;
    }

    public static void validateSumOverflow(int currentSum, int numberToAdd) {
        // 오버플로우 체크
        if (numberToAdd > 0 && currentSum > Integer.MAX_VALUE - numberToAdd) {
            throw new IllegalArgumentException("합계가 숫자 범위를 초과했습니다.");
        }
        // 언더플로우 체크
        if (numberToAdd < 0 && currentSum < Integer.MIN_VALUE - numberToAdd) {
            throw new IllegalArgumentException("합계가 숫자 범위를 초과했습니다.");
        }
    }
}
