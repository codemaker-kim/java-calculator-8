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

    // 객체 단위로 쪼개서 구현하기.
    private static int calculate(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String regex = ",|:";

        String[] targets = input.split(regex);

        return Arrays.stream(targets)
                .mapToInt(Integer::parseInt)
                .sum();
    }
}