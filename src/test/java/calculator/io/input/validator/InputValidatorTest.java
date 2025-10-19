package calculator.io.input.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = {"-1", "-5", "-10", "-1.5", "-0.1"})
    @DisplayName("음수 입력에 대해 예외 발생")
    void validatePositiveNumber_WithNegativeNumber_ThrowsException(String number) {
        assertThatThrownBy(() -> InputValidator.validatePositiveNumber(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "1", "5", "100", "1.5", "3.14", "0.5"})
    @DisplayName("양수 및 소수 입력에 대해 예외 발생하지 않음")
    void validatePositiveNumber_WithPositiveNumber_DoesNotThrow(String number) {
        assertDoesNotThrow(() -> InputValidator.validatePositiveNumber(number));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "1a", "a1", "1.5.3", "", "..5", "1.", ".5", "1..", ".."})
    @DisplayName("숫자가 아닌 문자 및 잘못된 소수 형식 입력에 대해 예외 발생")
    void validateNumeric_WithNonNumericString_ThrowsException(String input) {
        assertThatThrownBy(() -> InputValidator.validateNumeric(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "1.0", "1.5", "999999.999", "0.0001"})
    @DisplayName("유효한 숫자 형식에 대해 예외 발생하지 않음")
    void validateNumeric_WithValidNumber_DoesNotThrow(String input) {
        assertDoesNotThrow(() -> InputValidator.validateNumeric(input));
    }

    @Test
    @DisplayName("Double 범위 초과 시 예외 발생")
    void validateNumberRange_WithOverflow_ThrowsException() {
        String overflowNumber = "1e309"; // Double.MAX_VALUE를 확실히 초과하는 값
        assertThatThrownBy(() -> InputValidator.validateNumberRange(overflowNumber))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("유효한 Double 범위 내 숫자는 예외 발생하지 않음")
    void validateNumberRange_WithValidRange_DoesNotThrow() {
        assertDoesNotThrow(() -> InputValidator.validateNumberRange("1.5"));
        assertDoesNotThrow(() -> InputValidator.validateNumberRange("999999.999"));
    }
}
