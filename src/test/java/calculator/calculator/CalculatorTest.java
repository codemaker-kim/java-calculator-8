package calculator.calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    private Calculator calculator;

    @Test
    @DisplayName("숫자 배열의 합 계산")
    void calculateSum_WithValidNumbers_ReturnsSum() {
        int result = calculatorInit(List.of(1, 2, 3))
                .calculate();

        assertThat(6).isEqualTo(result);
    }

    @Test
    @DisplayName("빈 배열의 합은 0")
    void calculateSum_WithEmptyArray_ReturnsZero() {
        int result = calculatorInit(List.of())
                .calculate();

        assertThat(0).isEqualTo(result);
    }

    @Test
    @DisplayName("단일 숫자의 합")
    void calculateSum_WithSingleNumber_ReturnsSingleNumber() {
        int result = calculatorInit(List.of(5))
                .calculate();

        assertThat(5).isEqualTo(result);
    }

    @Test
    @DisplayName("합계 결과 오버플로우 검증")
    void calculateSum_WithOverflow_ThrowsException() {
        Calculator calculator = calculatorInit(List.of(Integer.MAX_VALUE, 1));

        assertThatThrownBy(calculator::calculate)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("오버플로우 검증 - 정상 범위")
    void validateSumOverflow_WithValidSum_DoesNotThrow() {
        Calculator calculator = calculatorInit(List.of(100, 200));

        assertDoesNotThrow(calculator::calculate);
    }

    private Calculator calculatorInit(List<Integer> input) {
        return new Calculator(input);
    }
}
