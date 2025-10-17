package calculator.delimiter;

import calculator.io.input.validator.InputValidator;

public class DelimiterExtractor {

    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\n";
    private static final String DEFAULT_DELIMITER_PATTERN = ",|:";

    public static String getDefaultDelimiterPattern() {
        return DEFAULT_DELIMITER_PATTERN;
    }

    public static boolean hasCustomDelimiter(String input) {
        if (input == null) {
            return false;
        }

        // \\n을 실제 \n으로 변환하여 확인
        String normalizedInput = input.replace("\\n", "\n");
        return normalizedInput.startsWith(CUSTOM_DELIMITER_PREFIX) &&
                normalizedInput.contains(CUSTOM_DELIMITER_SUFFIX);
    }

    public static String extractCustomDelimiter(String input) {
        if (!hasCustomDelimiter(input)) {
            return null;
        }

        // \\n을 실제 \n으로 변환
        String normalizedInput = input.replace("\\n", "\n");
        InputValidator.validateCustomDelimiterFormat(normalizedInput);

        int prefixEnd = CUSTOM_DELIMITER_PREFIX.length();
        int suffixStart = normalizedInput.indexOf(CUSTOM_DELIMITER_SUFFIX);
        String delimiter = normalizedInput.substring(prefixEnd, suffixStart);

        InputValidator.validateForbiddenDelimiter(delimiter);

        // 정규식에서 특수문자는 이스케이프 처리
        return escapeSpecialCharacters(delimiter);
    }

    public static String extractExpression(String input) {
        if (!hasCustomDelimiter(input)) {
            return input;
        }

        // \\n을 실제 \n으로 변환
        String normalizedInput = input.replace("\\n", "\n");
        int suffixStart = normalizedInput.indexOf(CUSTOM_DELIMITER_SUFFIX);
        return normalizedInput.substring(suffixStart + CUSTOM_DELIMITER_SUFFIX.length());
    }

    private static String escapeSpecialCharacters(String delimiter) {
        // 정규식에서 특별한 의미를 가지는 문자들을 이스케이프
        String[] specialChars = {"\\", "^", "$", ".", "|", "?", "*", "+", "(", ")", "[", "]", "{"};
        String result = delimiter;

        for (String specialChar : specialChars) {
            if (result.contains(specialChar)) {
                result = result.replace(specialChar, "\\" + specialChar);
            }
        }

        return result;
    }
}
