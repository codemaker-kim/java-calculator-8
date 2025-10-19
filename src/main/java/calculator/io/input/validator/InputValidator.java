package calculator.io.input.validator;

import static calculator.io.input.validator.InputErrorMessage.NEGATIVE_NUMBER;
import static calculator.io.input.validator.InputErrorMessage.NON_NUMERIC;
import static calculator.io.input.validator.InputErrorMessage.NUMBER_OUT_OF_RANGE;
import static calculator.io.input.validator.InputErrorMessage.ONLY_DELIMITERS;
import static java.math.BigDecimal.ZERO;

import java.util.Arrays;
import java.util.regex.Pattern;

public class InputValidator {

    private static final Pattern VALID_NUMBER_PATTERN = Pattern.compile("^\\d+(\\.\\d+)?$");

    public static void validatePositiveNumber(String number) {
        double value = Double.parseDouble(number);

        if (value < ZERO.doubleValue()) {
            throw new IllegalArgumentException(NEGATIVE_NUMBER.getMessage());
        }
    }

    public static void validateNumeric(String input) {
        validateNumberFormat(input);

        try {
            Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NON_NUMERIC.getMessage());
        }
    }

    public static void validateNumberRange(String number) {
        try {
            validateFiniteAndNotNaN(Double.parseDouble(number));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NUMBER_OUT_OF_RANGE.getMessage());
        }
    }

    public static void validateNotOnlyDelimiters(String[] tokens) {
        if (!containsValidNumber(tokens)) {
            throw new IllegalArgumentException(ONLY_DELIMITERS.getMessage());
        }
    }

    private static boolean containsValidNumber(String[] tokens) {
        return Arrays.stream(tokens)
                .anyMatch(token -> !token.isEmpty());
    }

    private static void validateFiniteAndNotNaN(double value) {
        if (Double.isInfinite(value) || Double.isNaN(value)) {
            throw new IllegalArgumentException(NUMBER_OUT_OF_RANGE.getMessage());
        }
    }

    private static void validateNumberFormat(String input) {
        if (!VALID_NUMBER_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException(NON_NUMERIC.getMessage());
        }
    }
}
