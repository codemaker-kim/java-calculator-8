package calculator.io.input;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import calculator.Application;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputExtractorTest extends NsTest {

    @Test
    @DisplayName("커스텀 구분자 형식이 있으면 커스텀 구분자 양식을 반환한다.")
    void hasCustomDelimiter() {
        assertSimpleTest(() -> {
            String hasDelimiterPart = InputExtractor.getCustomDelimiterPart("//;\\n1,2:3;4");
            assertThat(hasDelimiterPart).isEqualTo("//;\\n");
        });
    }

    @Test
    @DisplayName("커스텀 구분자 형식이 없으면 null을 반환한다.")
    void noCustomDelimiter() {
        assertSimpleTest(() -> {
            String nullDelimiterPart = InputExtractor.getCustomDelimiterPart("1,2:3");
            assertThat(nullDelimiterPart).isEqualTo(null);
        });
    }

    @Test
    @DisplayName("커스텀 구분자가 없는 계산식은 그대로 반환한다.")
    void extractExpressionNoCustomDelimiter() {
        assertSimpleTest(() -> {
            String expression = InputExtractor.getExpressionPart("1,2,3");
            assertThat(expression).isEqualTo("1,2,3");
        });
    }

    @Test
    @DisplayName("커스텀 구분자가 있는 계산식에서는 계산할 부분만 반환한다.")
    void extractExpressionWithCustomDelimiter() {
        assertSimpleTest(() -> {
            String expression = InputExtractor.getExpressionPart("//;\\n1,2,3;4");
            assertThat(expression).isEqualTo("1,2,3;4");
        });
    }

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}