package calculator.io.input.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputValidatorTest {

    @Test
    @DisplayName("null 입력에 대해 true 반환")
    void isEmpty_WithNull_ReturnsTrue() {
        assertTrue(InputValidator.isEmpty(null));
    }

    @Test
    @DisplayName("빈 문자열 입력에 대해 true 반환")
    void isEmpty_WithEmptyString_ReturnsTrue() {
        assertTrue(InputValidator.isEmpty(""));
    }

    @Test
    @DisplayName("공백만 있는 문자열 입력에 대해 false 반환")
    void isEmpty_WithWhitespace_ReturnsFalse() {
        assertFalse(InputValidator.isEmpty(" "));
    }

    @Test
    @DisplayName("일반 문자열 입력에 대해 false 반환")
    void isEmpty_WithNormalString_ReturnsFalse() {
        assertFalse(InputValidator.isEmpty("1,2"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "-5", "-10"})
    @DisplayName("음수 입력에 대해 예외 발생")
    void validatePositiveNumber_WithNegativeNumber_ThrowsException(String number) {
        assertThrows(IllegalArgumentException.class,
                () -> InputValidator.validatePositiveNumber(number));
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
        assertThrows(IllegalArgumentException.class,
                () -> InputValidator.validateNumeric(input));
    }

    @Test
    @DisplayName("정수 범위 초과 시 예외 발생")
    void validateNumberRange_WithOverflow_ThrowsException() {
        String overflowNumber = "2147483648"; // Integer.MAX_VALUE + 1
        assertThrows(IllegalArgumentException.class,
                () -> InputValidator.validateNumberRange(overflowNumber));
    }

    @Test
    @DisplayName("커스텀 구분자 양식이 올바르지 않을 때 예외 발생")
    void validateCustomDelimiterFormat_WithInvalidFormat_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> InputValidator.validateCustomDelimiterFormat("//"));
        assertThrows(IllegalArgumentException.class,
                () -> InputValidator.validateCustomDelimiterFormat("//\n"));
    }

    @Test
    @DisplayName("구분자만 있고 숫자가 없는 경우 예외 발생")
    void validateNotOnlyDelimiters_WithOnlyDelimiters_ThrowsException() {
        String[] onlyDelimiters = {"", "", ""};
        assertThrows(IllegalArgumentException.class,
                () -> InputValidator.validateNotOnlyDelimiters(onlyDelimiters));
    }

    @Test
    @DisplayName("금지된 구분자(.) 사용 시 예외 발생")
    void validateForbiddenDelimiter_WithDot_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> InputValidator.validateForbiddenDelimiter("."));
    }
}
