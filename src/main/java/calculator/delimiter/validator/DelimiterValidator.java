package calculator.delimiter.validator;

import static calculator.delimiter.Delimiter.PERIOD;
import static calculator.delimiter.validator.DelimiterErrorMessage.EMPTY_CUSTOM_DELIMITER;
import static calculator.delimiter.validator.DelimiterErrorMessage.FORBIDDEN_DELIMITER;

public class DelimiterValidator {

    public static boolean delimiterPartIsNull(String delimiterPart) {
        return delimiterPart == null;
    }

    public static void validateEmpty(String delimiter) {
        if (delimiter.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_CUSTOM_DELIMITER.getMessage());
        }
    }

    public static void validateForbiddenDelimiter(String delimiter) {
        if (delimiter.equals(PERIOD.getValue())) {
            throw new IllegalArgumentException(FORBIDDEN_DELIMITER.getMessage());
        }
    }
}
