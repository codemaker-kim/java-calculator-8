package calculator;

import calculator.calculator.Calculator;
import calculator.io.input.Input;
import calculator.io.output.OutputFormatter;
import camp.nextstep.edu.missionutils.Console;
import java.util.NoSuchElementException;

public class Application {

    public static void main(String[] args) {
        OutputFormatter.printInputPrompt();
        Input input;

        try {
            input = new Input(Console.readLine());
        } catch (NoSuchElementException e) {
            input = new Input("");
        }

        Calculator calculator = new Calculator(input);
        int result = calculator.calculate();

        OutputFormatter.printResult(result);
    }
}