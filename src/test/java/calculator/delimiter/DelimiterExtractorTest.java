package calculator.delimiter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DelimiterExtractorTest {

    @Test
    @DisplayName("커스텀 구분자 양식이 null로 들어왔을 경우, 기본 정규식을 반환한다.")
    void returnDefaultRegex() {
        String result = DelimiterExtractor.getRegex(null);

        assertThat(result).isEqualTo(",|:");
    }

    @Test
    @DisplayName("커스텀 구분자 양식이 올바르게 들어왔을 경우, 커스텀 구분자를 합친 정규식을 반환한다.")
    void returnCustomRegex() {
        String result = DelimiterExtractor.getRegex("//!!\\n");

        assertThat(result).isEqualTo(",|:|!!");
    }

    @Test
    @DisplayName("커스텀 구분자 양식 내부 길이가 0일 경우, 예외를 발생시킨다.")
    void throwExceptionDelimiterPartIsEmpty() {
        assertThatThrownBy(() -> DelimiterExtractor.getRegex("//\\n"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
