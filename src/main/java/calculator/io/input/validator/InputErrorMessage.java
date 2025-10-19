package calculator.io.input.validator;

public enum InputErrorMessage {
    NEGATIVE_NUMBER("음수는 입력할 수 없습니다."),
    NON_NUMERIC("숫자가 아닌 문자가 포함되어 있습니다."),
    NUMBER_OUT_OF_RANGE("숫자 범위를 초과했습니다."),
    ONLY_DELIMITERS("구분자만 있고 숫자가 없습니다.");

    final String message;

    InputErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
