package calculator.delimiter;

public enum Delimiter {
    COMMA(","),
    COLON(":"),
    PERIOD("."); // 사용 금지 구분자.

    final String value;

    Delimiter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static String getDefaultRegex() {
        return String.join("|", COMMA.getValue(), COLON.getValue());
    }

    public static String getRegex(String customDelimiter) {
        return String.join("|", COMMA.getValue(), COLON.getValue(), customDelimiter);
    }
}
