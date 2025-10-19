package calculator.io.input;

import static calculator.io.input.validator.InputErrorMessage.INVALID_NUMBER_FORMAT;

import calculator.delimiter.DelimiterExtractor;
import calculator.io.input.dto.CalculatorInput;
import calculator.io.input.validator.InputValidator;
import java.util.Arrays;
import java.util.List;

public class InputParser {

    public static CalculatorInput toCalculatorInput(String input) {
        String delimiterPart = InputExtractor.getDelimiterPart(input);
        String expressionPart = InputExtractor.getExpressionPart(input);

        List<Double> numbers = getNumbers(delimiterPart, expressionPart);

        return new CalculatorInput(numbers);
    }

    private static List<Double> getNumbers(String delimiterPart, String expressionPart) {
        String regex = DelimiterExtractor.getRegex(delimiterPart);

        return parseNumbers(expressionPart, regex);
    }

    private static List<Double> parseNumbers(String expressionPart, String regex) {
        if (InputValidator.isNullOrEmpty(expressionPart)) {
            return List.of();
        }

        String[] tokens = expressionPart.split(regex);
        InputValidator.validateNotOnlyDelimiters(tokens);

        return convertToNumberList(tokens);
    }

    private static List<Double> convertToNumberList(String[] tokens) {
        return Arrays.stream(tokens)
                .filter(token -> !token.isEmpty())
                .map(InputParser::parseToDouble)
                .toList();
    }

    private static Double parseToDouble(String token) {
        try {
            InputValidator.validateNumeric(token);
            InputValidator.validateNumberRange(token);

            double number = Double.parseDouble(token);
            InputValidator.validatePositiveNumber(token);

            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER_FORMAT.getMessage() + token);
        }
    }
}
