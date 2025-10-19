package calculator.calculator;

public enum CalculatorErrorMessage {
    SUM_OVERFLOW("합계가 숫자 범위를 초과했습니다.");

    final String message;

    CalculatorErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
