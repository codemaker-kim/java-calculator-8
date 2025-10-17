package calculator.io.output;

public class OutputFormatter {

    private static final String INPUT_MSG = "덧셈할 문자열을 입력해 주세요.";
    private static final String RESULT_MSG = "결과 : ";

    public static void printResult(int result) {
        System.out.println(formatResult(result));
    }

    public static void printInputPrompt() {
        System.out.println(INPUT_MSG);
    }

    private static String formatResult(int result) {
        return RESULT_MSG + result;
    }
}
