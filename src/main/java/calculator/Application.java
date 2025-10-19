package calculator;

import calculator.calculator.Calculator;
import calculator.io.input.Input;
import calculator.io.input.InputParser;
import calculator.io.input.dto.CalculatorInput;
import calculator.io.output.OutputFormatter;

public class Application {

    public static void main(String[] args) {
        OutputFormatter.printInputPrompt();
        String input = Input.getInput();

        CalculatorInput calculatorInput = InputParser.toCalculatorInput(input);
        Calculator calculator = new Calculator(calculatorInput.numbers());

        int result = calculator.calculate();

        OutputFormatter.printResult(result);
    }
}