package calculator.io.input;

import static java.math.BigDecimal.ZERO;

public class Input {
    private final String value;

    public Input(String value) {
        this.value = value;
    }

    public String getValue() {
        return isNullOrEmpty() ? ZERO.toString() : value;
    }

    private boolean isNullOrEmpty() {
        return this.value == null || this.value.isEmpty();
    }
}
