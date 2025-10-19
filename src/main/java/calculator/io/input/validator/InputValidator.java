package calculator.io.input.validator;

import java.util.Arrays;

public class InputValidator {

    public static void validatePositiveNumber(String number) {
        int value = Integer.parseInt(number);
        if (value < 0) {
            throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
        }
    }

    public static void validateNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 문자가 포함되어 있습니다.");
        }
    }

    public static void validateNumberRange(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 범위를 초과했습니다.");
        }
    }

    public static void validateNotOnlyDelimiters(String[] tokens) {
        boolean hasValidNumber = Arrays.stream(tokens)
                .anyMatch(token -> !token.isEmpty());

        if (!hasValidNumber) {
            throw new IllegalArgumentException("구분자만 있고 숫자가 없습니다.");
        }
    }
}
