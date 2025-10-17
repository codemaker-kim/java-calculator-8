package calculator.io.input.validator;

import java.util.Arrays;

public class InputValidator {

    public static boolean isEmpty(String input) {
        return input == null || input.isEmpty();
    }

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

    public static void validateCustomDelimiterFormat(String input) {
        if (input.equals("//") || input.equals("//\n")) {
            throw new IllegalArgumentException("커스텀 구분자 양식이 올바르지 않습니다.");
        }

        if (input.startsWith("//") && input.contains("\n")) {
            String delimiterPart = input.substring(2, input.indexOf('\n'));
            if (delimiterPart.isEmpty()) {
                throw new IllegalArgumentException("커스텀 구분자가 지정되지 않았습니다.");
            }
        }
    }

    public static void validateNotOnlyDelimiters(String[] tokens) {
        boolean hasValidNumber = Arrays.stream(tokens)
                .anyMatch(token -> !token.isEmpty());

        if (!hasValidNumber) {
            throw new IllegalArgumentException("구분자만 있고 숫자가 없습니다.");
        }
    }

    public static void validateForbiddenDelimiter(String delimiter) {
        if (".".equals(delimiter)) {
            throw new IllegalArgumentException("금지된 구분자(.)는 사용할 수 없습니다.");
        }
    }
}
