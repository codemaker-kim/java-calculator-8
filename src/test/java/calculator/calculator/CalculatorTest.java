package calculator.calculator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTest {

    @Test
    @DisplayName("숫자 배열의 합 계산")
    void calculateSum_WithValidNumbers_ReturnsSum() {
        int result = Calculator.calculateSum(new int[]{1, 2, 3});
        assertEquals(6, result);
    }

    @Test
    @DisplayName("빈 배열의 합은 0")
    void calculateSum_WithEmptyArray_ReturnsZero() {
        int result = Calculator.calculateSum(new int[]{});
        assertEquals(0, result);
    }

    @Test
    @DisplayName("단일 숫자의 합")
    void calculateSum_WithSingleNumber_ReturnsSingleNumber() {
        int result = Calculator.calculateSum(new int[]{5});
        assertEquals(5, result);
    }

    @Test
    @DisplayName("합계 결과 오버플로우 검증")
    void calculateSum_WithOverflow_ThrowsException() {
        int[] numbers = {Integer.MAX_VALUE, 1};
        assertThrows(IllegalArgumentException.class,
                () -> Calculator.calculateSum(numbers));
    }

    @Test
    @DisplayName("오버플로우 검증 - 정상 범위")
    void validateSumOverflow_WithValidSum_DoesNotThrow() {
        assertDoesNotThrow(() -> Calculator.validateSumOverflow(100, 200));
    }

    @Test
    @DisplayName("오버플로우 검증 - 오버플로우 발생")
    void validateSumOverflow_WithOverflow_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> Calculator.validateSumOverflow(Integer.MAX_VALUE, 1));
    }

    @Test
    @DisplayName("언더플로우 검증 - 언더플로우 발생")
    void validateSumOverflow_WithUnderflow_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> Calculator.validateSumOverflow(Integer.MIN_VALUE, -1));
    }
}
