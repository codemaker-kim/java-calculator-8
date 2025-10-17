package calculator.io.input;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.Application;
import calculator.io.input.validator.InputValidator;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputValidatorTest extends NsTest {

    @Test
    @DisplayName("길이가 0인 문자열일 경우 참을 반환한다.")
    void isEmptyTest() {
        assertSimpleTest(() -> {
            boolean isValid = InputValidator.isEmpty("");
            assertThat(isValid).isTrue();
        });
    }

    @Test
    @DisplayName("null일 경우 참을 반환한다.")
    void isEmptyTest2() {
        assertSimpleTest(() -> {
            boolean isValid = InputValidator.isEmpty(null);
            assertThat(isValid).isTrue();
        });
    }


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

    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}