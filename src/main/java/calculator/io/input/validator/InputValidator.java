package calculator.io.input.validator;

import static calculator.io.input.validator.InputErrorMessage.NEGATIVE_NUMBER;
import static calculator.io.input.validator.InputErrorMessage.NON_NUMERIC;
import static calculator.io.input.validator.InputErrorMessage.NUMBER_OUT_OF_RANGE;
import static calculator.io.input.validator.InputErrorMessage.ONLY_DELIMITERS;

import java.util.Arrays;

public class InputValidator {

    public static void validatePositiveNumber(String number) {
        int value = Integer.parseInt(number);
        if (value < 0) {
            throw new IllegalArgumentException(NEGATIVE_NUMBER.getMessage());
        }
    }

    public static void validateNumeric(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NON_NUMERIC.getMessage());
        }
    }

    public static void validateNumberRange(String number) {
        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_OUT_OF_RANGE.getMessage());
        }
    }

    public static void validateNotOnlyDelimiters(String[] tokens) {
        boolean hasValidNumber = Arrays.stream(tokens)
                .anyMatch(token -> !token.isEmpty());

        if (!hasValidNumber) {
            throw new IllegalArgumentException(ONLY_DELIMITERS.getMessage());
        }
    }
}
