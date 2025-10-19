package calculator.io.input.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"-1", "-5", "-10"})
    @DisplayName("음수 입력에 대해 예외 발생")
    void validatePositiveNumber_WithNegativeNumber_ThrowsException(String number) {
        assertThatThrownBy(() -> InputValidator.validatePositiveNumber(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "5", "100"})
    @DisplayName("양수 입력에 대해 예외 발생하지 않음")
    void validatePositiveNumber_WithPositiveNumber_DoesNotThrow(String number) {
        assertDoesNotThrow(() -> InputValidator.validatePositiveNumber(number));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "1a", "a1", "1.5.3"})
    @DisplayName("숫자가 아닌 문자 입력에 대해 예외 발생")
    void validateNumeric_WithNonNumericString_ThrowsException(String input) {
        assertThatThrownBy(() -> InputValidator.validateNumeric(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("정수 범위 초과 시 예외 발생")
    void validateNumberRange_WithOverflow_ThrowsException() {
        String overflowNumber = "2147483648"; // Integer.MAX_VALUE + 1
        assertThatThrownBy(() -> InputValidator.validateNumberRange(overflowNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
