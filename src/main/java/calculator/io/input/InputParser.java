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

        List<Integer> numbers = getNumbers(delimiterPart, expressionPart);

        return new CalculatorInput(numbers);
    }

    private static List<Integer> getNumbers(String delimiterPart, String expressionPart) {
        String regex = DelimiterExtractor.getRegex(delimiterPart);

        return parseNumbers(expressionPart, regex);
    }

    private static List<Integer> parseNumbers(String expressionPart, String regex) {
        if (isNullOrEmpty(expressionPart)) {
            return List.of();
        }

        String[] tokens = expressionPart.split(regex);
        InputValidator.validateNotOnlyDelimiters(tokens);

        return Arrays.stream(tokens)
                .filter(token -> !token.isEmpty())
                .map(InputParser::parseToInt)
                .toList();
    }

    private static int parseToInt(String token) {
        try {
            InputValidator.validateNumeric(token);
            InputValidator.validateNumberRange(token);

            int number = Integer.parseInt(token);
            InputValidator.validatePositiveNumber(token);

            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_NUMBER_FORMAT.getMessage() + token);
        }
    }

    private static boolean isNullOrEmpty(String expressionPart) {
        return expressionPart == null || expressionPart.isEmpty();
    }
}
