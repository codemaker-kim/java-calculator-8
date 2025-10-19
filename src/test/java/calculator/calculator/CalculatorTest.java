package calculator.calculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    private Calculator calculator;

    @Test
    @DisplayName("정수 배열의 합 계산")
    void calculateSum_WithValidIntegers_ReturnsSum() {
        double result = calculatorInit(List.of(1.0, 2.0, 3.0))
                .calculate();

        assertThat(result).isEqualTo(6.0);
    }

    @Test
    @DisplayName("소수 배열의 합 계산")
    void calculateSum_WithValidDecimals_ReturnsSum() {
        double result = calculatorInit(List.of(1.5, 2.3, 3.2))
                .calculate();

        assertThat(result).isEqualTo(7.0, within(0.0001));
    }

    @Test
    @DisplayName("빈 배열의 합은 0")
    void calculateSum_WithEmptyArray_ReturnsZero() {
        double result = calculatorInit(List.of())
                .calculate();

        assertThat(result).isEqualTo(0.0);
    }

    @Test
    @DisplayName("단일 숫자의 합")
    void calculateSum_WithSingleNumber_ReturnsSingleNumber() {
        double result = calculatorInit(List.of(5.5))
                .calculate();

        assertThat(result).isEqualTo(5.5);
    }

    @Test
    @DisplayName("매우 큰 숫자들의 합 계산")
    void calculateSum_WithLargeNumbers_DoesNotThrow() {
        Calculator calculator = calculatorInit(
                List.of(Double.MAX_VALUE / 10, Double.MAX_VALUE / 10, Double.MAX_VALUE / 10));

        assertDoesNotThrow(calculator::calculate);
    }

    @Test
    @DisplayName("매우 작은 소수들의 합 계산")
    void calculateSum_WithSmallDecimals_ReturnsSum() {
        double result = calculatorInit(List.of(0.1, 0.2, 0.3))
                .calculate();

        assertThat(result).isEqualTo(0.6, within(0.0001));
    }

    @Test
    @DisplayName("Double 오버플로우 시 예외 발생")
    void calculateSum_WithOverflow_ThrowsException() {
        Calculator calculator = calculatorInit(List.of(Double.MAX_VALUE, Double.MAX_VALUE));

        assertThatThrownBy(calculator::calculate)
                .isInstanceOf(IllegalArgumentException.class);
    }

    private Calculator calculatorInit(List<Double> numbers) {
        return new Calculator(numbers);
    }
}
