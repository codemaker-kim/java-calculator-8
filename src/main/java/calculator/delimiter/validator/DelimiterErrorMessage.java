package calculator.delimiter.validator;

public enum DelimiterErrorMessage {
    EMPTY_CUSTOM_DELIMITER("커스텀 구분자가 지정되지 않았습니다."),
    FORBIDDEN_DELIMITER("금지된 구분자(.)는 사용할 수 없습니다.");

    final String message;

    DelimiterErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
