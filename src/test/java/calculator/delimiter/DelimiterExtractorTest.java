package calculator.delimiter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class DelimiterExtractorTest {

    @Test
    @DisplayName("기본 구분자(쉼표, 콜론) 패턴 반환")
    void getDefaultDelimiterPattern_ReturnsCommaAndColon() {
        String pattern = DelimiterExtractor.getDefaultDelimiterPattern();
        assertEquals(",|:", pattern);
    }

    @Test
    @DisplayName("커스텀 구분자 패턴이 있는지 감지")
    void hasCustomDelimiter_WithCustomPattern_ReturnsTrue() {
        assertTrue(DelimiterExtractor.hasCustomDelimiter("//;\n1;2;3"));
        assertTrue(DelimiterExtractor.hasCustomDelimiter("//|\n1|2|3"));
    }

    @Test
    @DisplayName("커스텀 구분자 패턴이 없는지 감지")
    void hasCustomDelimiter_WithoutCustomPattern_ReturnsFalse() {
        assertFalse(DelimiterExtractor.hasCustomDelimiter("1,2,3"));
        assertFalse(DelimiterExtractor.hasCustomDelimiter("1:2:3"));
        assertFalse(DelimiterExtractor.hasCustomDelimiter(""));
    }

    @ParameterizedTest
    @ValueSource(strings = {"//;\n1;2;3", "//|\n1|2|3", "//*\n1*2*3"})
    @DisplayName("커스텀 구분자 추출")
    void extractCustomDelimiter_WithValidPattern_ReturnsDelimiter(String input) {
        String delimiter = DelimiterExtractor.extractCustomDelimiter(input);
        assertNotNull(delimiter);
        assertFalse(delimiter.isEmpty());
    }

    @Test
    @DisplayName("세미콜론 커스텀 구분자 추출")
    void extractCustomDelimiter_WithSemicolon_ReturnsSemicolon() {
        String delimiter = DelimiterExtractor.extractCustomDelimiter("//;\n1;2;3");
        assertEquals(";", delimiter);
    }

    @Test
    @DisplayName("파이프 커스텀 구분자 추출")
    void extractCustomDelimiter_WithPipe_ReturnsPipe() {
        String delimiter = DelimiterExtractor.extractCustomDelimiter("//|\n1|2|3");
        assertEquals("\\|", delimiter); // 정규식에서 파이프는 이스케이프 필요
    }

    @Test
    @DisplayName("커스텀 구분자가 없을 때 null 반환")
    void extractCustomDelimiter_WithoutCustomPattern_ReturnsNull() {
        assertNull(DelimiterExtractor.extractCustomDelimiter("1,2,3"));
    }

    @Test
    @DisplayName("커스텀 구분자 패턴에서 표현식 부분 추출")
    void extractExpression_WithCustomDelimiter_ReturnsExpression() {
        String expression = DelimiterExtractor.extractExpression("//;\n1;2;3");
        assertEquals("1;2;3", expression);
    }

    @Test
    @DisplayName("일반 입력에서 표현식 부분 그대로 반환")
    void extractExpression_WithoutCustomDelimiter_ReturnsOriginal() {
        String expression = DelimiterExtractor.extractExpression("1,2,3");
        assertEquals("1,2,3", expression);
    }

    @Test
    @DisplayName("빈 문자열 처리")
    void extractExpression_WithEmptyString_ReturnsEmpty() {
        String expression = DelimiterExtractor.extractExpression("");
        assertEquals("", expression);
    }
}
