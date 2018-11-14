package com.github.alexkirnsu.parser;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.Matchers.*;
import static org.junit.Assert.assertArrayEquals;

public class DataParserTest {

    private static final double DELTA_FOR_COMPARISON = 1e-14;

    private static final String STRING_TO_PARSE_IS_NULL = "Can't parse null string";
    private static final String EXPECTED_SIDE_COUNT_NOT_POSITIVE =
            "Expected count of sides should be positive integer";
    private static final String WRONG_ACTUAL_COUNT_OF_SIDES = "Wrong actual count of sides";
    private static final String WRONG_TYPE_OF_INPUT_DATA = "Length of side should be double";

    private static final int NEGATIVE_SIDE_COUNT = -1;
    private static final int ZERO_SIDE_COUNT = 0;
    private static final int TRIANGLE_SIDE_COUNT = 3;
    private static final String INPUT_DATA_WITH_ILLEGAL_SIDE_COUNT = "1.1 1.1 1.1 1.1";
    private static final String INPUT_DATA_WITH_ILLEGAL_SIDE_LENGTH_TYPE = "1.1 test 1.1";
    private static final String CORRECT_INPUT_DATA = "1.1 1.1 1.1";
    private static final String INPUT_DATA_WITH_COMMAS = "1,1 1,1 1.1";

    private final double[] expectedResult = {1.1, 1.1, 1.1};

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void parseSides_IfInputDataIsNull_ThrowIllegalArgumentException() {
        thrown.expect(NullPointerException.class);
        thrown.expectMessage(STRING_TO_PARSE_IS_NULL);

        DataParser.parseSides(null, anyInt());
    }

    @Test
    public void parseSides_IfExpectedCountOfSidesIsNegative_ThrowIllegalArgumentException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(EXPECTED_SIDE_COUNT_NOT_POSITIVE);

        DataParser.parseSides(anyString(), NEGATIVE_SIDE_COUNT);
    }

    @Test
    public void parseSides_IfExpectedCountOfSidesIsZero_ThrowIllegalArgumentException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(EXPECTED_SIDE_COUNT_NOT_POSITIVE);

        DataParser.parseSides(anyString(), ZERO_SIDE_COUNT);
    }

    @Test
    public void parseSides_IfActualCountOfSidesIsNotEqualToExpected_ThrowIllegalArgumentException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(WRONG_ACTUAL_COUNT_OF_SIDES);

        DataParser.parseSides(INPUT_DATA_WITH_ILLEGAL_SIDE_COUNT, TRIANGLE_SIDE_COUNT);
    }

    @Test
    public void parseSides_IfTypeOfSideLengthIsIllegal_ThrowIllegalArgumentException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(WRONG_TYPE_OF_INPUT_DATA);

        DataParser.parseSides(INPUT_DATA_WITH_ILLEGAL_SIDE_LENGTH_TYPE, TRIANGLE_SIDE_COUNT);
    }

    @Test
    public void parseSides_IfInputParametersAreCorrect_ReturnArrayOfSideLengths() {
        double[] actualResult = DataParser.parseSides(CORRECT_INPUT_DATA, TRIANGLE_SIDE_COUNT);

        assertArrayEquals(expectedResult, actualResult, DELTA_FOR_COMPARISON);
    }

    @Test
    public void parseSides_IfInputParametersHaveCommaInsteadPoint_ReturnArrayOfSideLengths() {
        double[] actualResult = DataParser.parseSides(INPUT_DATA_WITH_COMMAS, TRIANGLE_SIDE_COUNT);

        assertArrayEquals(expectedResult, actualResult, DELTA_FOR_COMPARISON);
    }
}
