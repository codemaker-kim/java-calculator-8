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

        String[] targets = getTargetResult(input, regex);

        // 구분자만 있고 숫자가 없는 경우 검증
        validateNotOnlyDelimiters(targets);

        return Arrays.stream(targets)
                .mapToInt(Application::parseAndValidateNumber)
                .reduce(0, Application::addWithOverflowCheck);
    }

    private static String[] getTargetResult(String input, String regex) {
        try {
            return input.split(regex);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력에 구분자가 아닌 문자가 포함되어 있습니다.");
        }
    }

    private static String getExpressionPart(String input) {
        return input.substring(input.indexOf(CUSTOM_DELIMITER_SUFFIX) + CUSTOM_DELIMITER_SUFFIX.length());
    }

    private static void validateNotOnlyDelimiters(String[] targets) {
        boolean hasNumber = false;
        for (String target : targets) {
            if (!target.trim().isEmpty()) {
                hasNumber = true;
                break;
            }
        }
        if (!hasNumber) {
            throw new IllegalArgumentException("숫자 없이 구분자만 입력할 수 없습니다.");
        }
    }

    private static int parseAndValidateNumber(String s) {
        if (s.trim().isEmpty()) {
            return 0;
        }

        try {
            long longValue = Long.parseLong(s.trim());
            if (longValue > Integer.MAX_VALUE || longValue < Integer.MIN_VALUE) {
                throw new IllegalArgumentException("입력 숫자가 범위를 초과했습니다: " + s);
            }

            int num = (int) longValue;
            if (num < 0) {
                throw new IllegalArgumentException("음수는 허용되지 않습니다: " + num);
            }

            return num;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("입력에 구분자가 아닌 문자가 포함되어 있습니다: " + s);
        }
    }

    private static int addWithOverflowCheck(int sum, int num) {
        try {
            return Math.addExact(sum, num);
        } catch (ArithmeticException e) {
            throw new IllegalArgumentException("합계가 숫자 범위를 초과했습니다.");
        }
    }

    private static String getCustomDelimiter(final String delimiterPart) {
        if (!delimiterPart.startsWith(CUSTOM_DELIMITER_PREFIX) || !delimiterPart.contains(CUSTOM_DELIMITER_SUFFIX)) {
            return null;
        }

        String customDelimiter = delimiterPart.substring(CUSTOM_DELIMITER_PREFIX.length(),
                delimiterPart.lastIndexOf(CUSTOM_DELIMITER_SUFFIX));

        if (customDelimiter.equals(".")) {
            throw new IllegalArgumentException("'.'은 커스텀 구분자로 사용할 수 없습니다.");
        }

        if (customDelimiter.isEmpty()) {
            throw new IllegalArgumentException("커스텀 구분자에 길이가 0인 문자열은 쓸 수 없습니다.");
        }

        return customDelimiter;
    }
}