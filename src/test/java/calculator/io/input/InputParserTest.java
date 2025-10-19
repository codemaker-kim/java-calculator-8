package calculator.io.input;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputParserTest {

    @Test
    @DisplayName("기본 구분자로 정수 문자열 분리")
    void parseNumbers_WithDefaultDelimiters_ReturnsSplitNumbers() {
        List<Double> result = InputParser.toCalculatorInput("1,2:3")
                .numbers();

        assertThat(result).isEqualTo(List.of(1.0, 2.0, 3.0));
    }

    @Test
    @DisplayName("기본 구분자로 소수 문자열 분리")
    void parseNumbers_WithDefaultDelimitersAndDecimals_ReturnsSplitNumbers() {
        List<Double> result = InputParser.toCalculatorInput("1.5,2.3:3.7")
                .numbers();

        assertThat(result).isEqualTo(List.of(1.5, 2.3, 3.7));
    }

    @Test
    @DisplayName("커스텀 구분자로 문자열 분리")
    void parseNumbers_WithCustomDelimiter_ReturnsSplitNumbers() {
        List<Double> result = InputParser.toCalculatorInput("//;\\n1;2;3")
                .numbers();

        assertThat(result).isEqualTo(List.of(1.0, 2.0, 3.0));
    }

    @Test
    @DisplayName("커스텀 구분자로 소수 문자열 분리")
    void parseNumbers_WithCustomDelimiterAndDecimals_ReturnsSplitNumbers() {
        List<Double> result = InputParser.toCalculatorInput("//;\\n1.5;2.3;3.7")
                .numbers();

        assertThat(result).isEqualTo(List.of(1.5, 2.3, 3.7));
    }

    @Test
    @DisplayName("단일 정수 파싱")
    void parseNumbers_WithSingleNumber_ReturnsSingleElement() {
        List<Double> result = InputParser.toCalculatorInput("5")
                .numbers();

        assertThat(result).isEqualTo(List.of(5.0));
    }

    @Test
    @DisplayName("단일 소수 파싱")
    void parseNumbers_WithSingleDecimal_ReturnsSingleElement() {
        List<Double> result = InputParser.toCalculatorInput("5.5")
                .numbers();

        assertThat(result).isEqualTo(List.of(5.5));
    }

    @Test
    @DisplayName("빈 문자열 파싱")
    void parseNumbers_WithEmptyString_ReturnsEmptyArray() {
        List<Double> result = InputParser.toCalculatorInput("")
                .numbers();

        assertThat(result).isEmpty();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,a,3", "1:b:3", "x,y,z", "1.5.3", "1..5", "1.", ".5", "1..,2.3", ".1,.2"})
    @DisplayName("숫자가 아닌 토큰 및 잘못된 소수 형식 파싱 시 예외 발생")
    void parseNumbers_WithNonNumericToken_ThrowsException(String input) {
        assertThatThrownBy(() -> InputParser.toCalculatorInput(input))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("음수 포함 시 예외 발생")
    void parseNumbers_WithNegativeNumber_ThrowsException() {
        assertThatThrownBy(() -> InputParser.toCalculatorInput("1,-2,3"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("음수 소수 포함 시 예외 발생")
    void parseNumbers_WithNegativeDecimal_ThrowsException() {
        assertThatThrownBy(() -> InputParser.toCalculatorInput("1.5,-2.3,3.7"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Double 범위 초과 시 예외 발생")
    void parseNumbers_WithDoubleOverflow_ThrowsException() {
        String overflowInput = "1,1e309,3"; // Double.MAX_VALUE를 확실히 초과하는 값

        assertThatThrownBy(() -> InputParser.toCalculatorInput(overflowInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구분자만 있는 경우 예외 발생")
    void parseNumbers_WithOnlyDelimiters_ThrowsException() {
        assertThatThrownBy(() -> InputParser.toCalculatorInput(",,:"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
