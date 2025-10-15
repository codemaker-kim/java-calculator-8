package calculator;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class Application {
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\\n";

    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input;

        try {
            input = Console.readLine();
        } catch (NoSuchElementException e) {
            input = "";
        }

        int result = calculate(input);
        System.out.println("결과 : " + result);
    }

    private static int calculate(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String regex = ",|:";
        String customDelimiter = getCustomDelimiter(input);

        if (customDelimiter != null) {
            regex += "|" + customDelimiter;
            input = getExpressionPart(input);
        }

        String[] targets = input.split(regex);

        return Arrays.stream(targets)
                .mapToInt(Application::isNegative)
                .sum();
    }

    private static String getExpressionPart(String input) {
        return input.substring(input.indexOf(CUSTOM_DELIMITER_SUFFIX) + CUSTOM_DELIMITER_SUFFIX.length());
    }

    private static int isNegative(String s) {
        int num = Integer.parseInt(s);
        if (num < 0) {
            throw new IllegalArgumentException("음수는 허용되지 않습니다: " + num);
        }

        return num;
    }

    private static String getCustomDelimiter(final String delimiterPart) {
        if (!delimiterPart.startsWith(CUSTOM_DELIMITER_PREFIX) || !delimiterPart.contains(CUSTOM_DELIMITER_SUFFIX)) {
            return null;
        }

        return delimiterPart.substring(CUSTOM_DELIMITER_PREFIX.length(),
                delimiterPart.lastIndexOf(CUSTOM_DELIMITER_SUFFIX));
    }
}