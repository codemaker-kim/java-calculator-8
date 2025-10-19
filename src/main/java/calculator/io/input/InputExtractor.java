package calculator.io.input;

import static java.math.BigDecimal.ZERO;

public class InputExtractor {

    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\\n";

    public static String getDelimiterPart(final String target) {
        if (hasCustomDelimiter(target)) {
            int endIndex = getDelimiterPartEndIndex(target);
            return target.substring(ZERO.intValue(), endIndex);
        }

        return null;
    }

    public static String getExpressionPart(final String target) {
        if (hasCustomDelimiter(target)) {
            int startIndex = getExpressionPartStartIndex(target);
            return target.substring(startIndex);
        }

        return target;
    }

    private static boolean hasCustomDelimiter(String target) {
        return target.startsWith(CUSTOM_DELIMITER_PREFIX) &&
                target.contains(CUSTOM_DELIMITER_SUFFIX);
    }

    private static int getDelimiterPartEndIndex(String target) {
        return target.indexOf(CUSTOM_DELIMITER_SUFFIX) +
                CUSTOM_DELIMITER_SUFFIX.length();
    }

    private static int getExpressionPartStartIndex(String target) {
        return target.lastIndexOf(CUSTOM_DELIMITER_SUFFIX) +
                CUSTOM_DELIMITER_SUFFIX.length();
    }
}
