package calculator.delimiter.validator;

import static calculator.delimiter.Delimiter.PERIOD;

public class DelimiterValidator {

    public static boolean delimiterPartIsNull(String delimiterPart) {
        return delimiterPart == null;
    }

    public static void validateEmpty(String delimiter) {
        if (delimiter.isEmpty()) {
            throw new IllegalArgumentException("커스텀 구분자가 지정되지 않았습니다.");
        }
    }

    public static void validateForbiddenDelimiter(String delimiter) {
        if (delimiter.equals(PERIOD.getValue())) {
            throw new IllegalArgumentException("금지된 구분자(.)는 사용할 수 없습니다.");
        }
    }
}
