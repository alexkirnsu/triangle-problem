package com.alexkirnsu.parser;

import org.junit.Test;

import static org.mockito.Matchers.*;
import static org.junit.Assert.assertArrayEquals;

public class DataParserTest {

    private static final double DELTA_FOR_COMPARISON = 1e-14;

    private static final int NEGATIVE_SIDE_COUNT = -1;
    private static final int ZERO_SIDE_COUNT = 0;
    private static final int TRIANGLE_SIDE_COUNT = 3;
    private static final String INPUT_DATA_WITH_ILLEGAL_SIDE_COUNT = "1.1 1.1 1.1 1.1";
    private static final String INPUT_DATA_WITH_ILLEGAL_SIDE_LENGTH_TYPE = "1.1 test 1.1";
    private static final String CORRECT_INPUT_DATA = "1.1 1.1 1.1";
    private static final String INPUT_DATA_WITH_COMMAS = "1,1 1,1 1.1";

    private final double[] expectedResult = {1.1, 1.1, 1.1};

    @Test(expected = NullPointerException.class)
    public void parseSides_IfInputDataIsNull_ThrowIllegalArgumentException() {
        DataParser.parseSides(null, anyInt());
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseSides_IfExpectedCountOfSidesIsNegative_ThrowIllegalArgumentException() {
        DataParser.parseSides(anyString(), NEGATIVE_SIDE_COUNT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseSides_IfExpectedCountOfSidesIsZero_ThrowIllegalArgumentException() {
        DataParser.parseSides(anyString(), ZERO_SIDE_COUNT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseSides_IfActualCountOfSidesIsNotEqualToExpected_ThrowIllegalArgumentException() {
        DataParser.parseSides(INPUT_DATA_WITH_ILLEGAL_SIDE_COUNT, TRIANGLE_SIDE_COUNT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseSides_IfTypeOfSideLengthIsIllegal_ThrowIllegalArgumentException() {
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
