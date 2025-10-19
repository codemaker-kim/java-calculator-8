package calculator.io.input;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputParserTest {

    @Test
    @DisplayName("기본 구분자로 문자열 분리")
    void parseNumbers_WithDefaultDelimiters_ReturnsSplitNumbers() {
        List<Integer> result = InputParser.toCalculatorInput("1,2:3")
                .numbers();

        assertEquals(List.of(1, 2, 3), result);
    }

    @Test
    @DisplayName("커스텀 구분자로 문자열 분리")
    void parseNumbers_WithCustomDelimiter_ReturnsSplitNumbers() {
        List<Integer> result = InputParser.toCalculatorInput("//;\\n1;2;3")
                .numbers();

        assertEquals(List.of(1, 2, 3), result);
    }

    @Test
    @DisplayName("단일 숫자 파싱")
    void parseNumbers_WithSingleNumber_ReturnsSingleElement() {
        List<Integer> result = InputParser.toCalculatorInput("5")
                .numbers();

        assertEquals(List.of(5), result);
    }

    @Test
    @DisplayName("빈 문자열 파싱")
    void parseNumbers_WithEmptyString_ReturnsEmptyArray() {
        List<Integer> result = InputParser.toCalculatorInput("")
                .numbers();

        assertEquals(List.of(), result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,a,3", "1:b:3", "x,y,z"})
    @DisplayName("숫자가 아닌 토큰 파싱 시 예외 발생")
    void parseNumbers_WithNonNumericToken_ThrowsException(String input) {
        assertThrows(IllegalArgumentException.class,
                () -> InputParser.toCalculatorInput(input));
    }

    @Test
    @DisplayName("음수 포함 시 예외 발생")
    void parseNumbers_WithNegativeNumber_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> InputParser.toCalculatorInput("1,-2,3"));
    }

    @Test
    @DisplayName("숫자 범위 초과 시 예외 발생")
    void parseNumbers_WithNumberOverflow_ThrowsException() {
        String overflowInput = "1,2147483648,3"; // Integer.MAX_VALUE + 1

        assertThrows(IllegalArgumentException.class,
                () -> InputParser.toCalculatorInput(overflowInput));
    }

    @Test
    @DisplayName("구분자만 있는 경우 예외 발생")
    void parseNumbers_WithOnlyDelimiters_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> InputParser.toCalculatorInput(",,:"));
    }
}
