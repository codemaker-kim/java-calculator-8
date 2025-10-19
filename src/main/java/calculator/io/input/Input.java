package calculator.io.input;

import camp.nextstep.edu.missionutils.Console;
import java.util.NoSuchElementException;

public class Input {

    private static final String BLANK = "";

    public static String getInput() {
        try {
            return Console.readLine();
        } catch (NoSuchElementException e) {
            return BLANK;
        }
    }
}
