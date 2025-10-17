package calculator.io.input;

import calculator.io.input.validator.InputValidator;
import java.util.Arrays;

public class NumberParser {

    public static int[] parseNumbers(String input, String delimiterPattern) {
        if (InputValidator.isEmpty(input)) {
            return new int[0];
        }

        String[] tokens = input.split(delimiterPattern);
        InputValidator.validateNotOnlyDelimiters(tokens);

        return Arrays.stream(tokens)
                .filter(token -> !token.isEmpty())
                .mapToInt(NumberParser::parseToInt)
                .toArray();
    }

    public static int parseToInt(String token) {
        try {
            InputValidator.validateNumeric(token);
            InputValidator.validateNumberRange(token);

            int number = Integer.parseInt(token);
            InputValidator.validatePositiveNumber(token);

            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 형식이 올바르지 않습니다: " + token);
        }
    }
}
