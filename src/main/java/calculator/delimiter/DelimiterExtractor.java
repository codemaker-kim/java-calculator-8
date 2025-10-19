package calculator.delimiter;

import calculator.delimiter.validator.DelimiterValidator;

public class DelimiterExtractor {

    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\\n";

    public static String getRegex(final String delimiterPart) {
        if (DelimiterValidator.delimiterPartIsNull(delimiterPart)) {
            return Delimiter.getDefaultRegex();
        }

        String customDelimiter = extractDelimiter(delimiterPart);

        DelimiterValidator.validateEmpty(customDelimiter);
        DelimiterValidator.validateForbiddenDelimiter(customDelimiter);

        return Delimiter.getRegex(customDelimiter);
    }

    private static String extractDelimiter(String delimiterPart) {
        int startIndex = getStartIndex(delimiterPart);
        int endIndex = getEndIndex(delimiterPart);

        return delimiterPart.substring(startIndex, endIndex);
    }

    private static int getStartIndex(String delimiterPart) {
        return delimiterPart.indexOf(CUSTOM_DELIMITER_PREFIX) + CUSTOM_DELIMITER_PREFIX.length();
    }

    private static int getEndIndex(String delimiterPart) {
        return delimiterPart.indexOf(CUSTOM_DELIMITER_SUFFIX);
    }
}
