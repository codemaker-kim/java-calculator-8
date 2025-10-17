package calculator.io.input;

import static java.math.BigDecimal.ZERO;

public class InputExtractor {

    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\\n";

    public static String getCustomDelimiterPart(final String target) {
        if (hasCustomDelimiter(target)) {
            return target.substring(ZERO.intValue(),
                    target.indexOf(CUSTOM_DELIMITER_SUFFIX) + CUSTOM_DELIMITER_SUFFIX.length());
        }

        return null;
    }

    public static String getExpressionPart(final String target) {
        if (hasCustomDelimiter(target)) {
            return target.substring(target.lastIndexOf(CUSTOM_DELIMITER_SUFFIX) + CUSTOM_DELIMITER_SUFFIX.length());
        }

        return target;
    }

    private static boolean hasCustomDelimiter(String target) {
        return target.startsWith(CUSTOM_DELIMITER_PREFIX) &&
                target.contains(CUSTOM_DELIMITER_SUFFIX);
    }
}
