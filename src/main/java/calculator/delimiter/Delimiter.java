package calculator.delimiter;

public enum Delimiter {
    COMMA(","),
    COLON(":");

    final String value;

    Delimiter(String value) {
        this.value = value;
    }

    public String getDelimiter() {
        return value;
    }
}
