package calculator.io.output;

import java.text.DecimalFormat;

public class OutputFormatter {

    private static final String INPUT_MSG = "덧셈할 문자열을 입력해 주세요.";
    private static final String RESULT_MSG = "결과 : ";
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##########");

    public static void printResult(double result) {
        System.out.println(formatResult(result));
    }

    public static void printInputPrompt() {
        System.out.println(INPUT_MSG);
    }

    private static String formatResult(double result) {
        return RESULT_MSG + (isIntegerValue(result) ? (long) result : DECIMAL_FORMAT.format(result));
    }

    private static boolean isIntegerValue(double result) {
        return result == Math.floor(result) && !Double.isInfinite(result);
    }
}
