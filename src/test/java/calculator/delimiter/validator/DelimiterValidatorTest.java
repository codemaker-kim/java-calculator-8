package calculator.delimiter.validator;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DelimiterValidatorTest {

    @Test
    @DisplayName("빈 구분자일 시 예외 발생")
    void validateBlankDelimiter_ThrowsException() {
        assertThatThrownBy(() -> DelimiterValidator.validateEmpty(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("금지된 구분자(.) 사용 시 예외 발생")
    void validateForbiddenDelimiter_WithDot_ThrowsException() {
        assertThatThrownBy(() -> DelimiterValidator.validateForbiddenDelimiter("."))
                .isInstanceOf(IllegalArgumentException.class);
    }
}