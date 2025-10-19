package calculator;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("-1,2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("빈 값이 입력되면 0을 반환한다.")
    void inputEmpty() {
        assertSimpleTest(() -> {
            run();
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    @DisplayName("개행만 입력되면 0을 반환한다.")
    void inputOnlyNewline() {
        assertSimpleTest(() -> {
            run("\n");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    @DisplayName("숫자 하나만 들어올 경우 해당 숫자를 반환한다.")
    void inputOnlyOneNumber() {
        assertSimpleTest(() -> {
            run("12345");
            assertThat(output()).contains("결과 : 12345");
        });
    }

    @ParameterizedTest
    @DisplayName("','이나 ':'로 구분된 계산식의 결과 출력에 성공한다.")
    @CsvSource(
            value = {
                    "1,2,3,4&결과 : 10",
                    "5:6:7:8&결과 : 26",
                    "10,20:30,40&결과 : 100"
            },
            delimiter = '&'
    )
    void calculateResult(String input, String expected) {
        assertSimpleTest(() -> {
            run(input);
            assertThat(output()).contains(expected);
        });
    }

    @Test
    @DisplayName("금지된 커스텀 구분자 지정 시 예외를 발생시킨다.")
    void bannedDelimiter() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//.\\n1,2:3.4"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @ParameterizedTest
    @DisplayName("기존에 존재하는 구분자로 커스텀 구분자를 지정해도 정상적으로 결과를 출력한다.")
    @CsvSource(
            value = {
                    "//:\\n1,2:3:4&결과 : 10",
                    "//,\\n1,2:3:4&결과 : 10"
            },
            delimiter = '&'
    )
    void alreadyExistDelimiter(String input, String expected) {
        assertSimpleTest(() -> {
            run(input);
            assertThat(output()).contains(expected);
        });
    }

    @Test
    @DisplayName("문자열에 구분자가 아닌 문자가 존재할 경우 예외 발생")
    void invalidInput() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("12,3:4=5"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("입력에 숫자 범위를 초과하는 값이 나오면 예외를 발생시킨다")
    void inputOverflowNumber() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("2147483648,1,2"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("커스텀 구분자 지정 양식은 있지만 구분자가 없으면 예외를 발생시킨다")
    void emptyCustomDelimiter() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//\\n1:2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("숫자 없이 구분자만 입력했을 경우 예외를 발생시킨다")
    void onlyDelimitersInput() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(",,:"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("커스텀 구분자로만 구성된 입력의 경우 예외를 발생시킨다")
    void onlyCustomDelimitersInput() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("//;\\n;;;"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("합이 숫자 범위를 초과하는 값이 나오면 예외를 발생시킨다")
    void sumOverflow() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("2147483647,1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("올바른 커스텀 구분자 양식만 존재하고, 계산식이 없는 경우 0을 반환한다.")
    void hasOnlyDelimiters() {
        assertSimpleTest(() -> {
            run("//;\\n");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
