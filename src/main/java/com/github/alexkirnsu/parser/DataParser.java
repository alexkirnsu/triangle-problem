package com.github.alexkirnsu.parser;

public class DataParser {

    private static final String STRING_TO_PARSE_IS_NULL = "Can't parse null string";
    private static final String SIDE_COUNT_NOT_POSITIVE =
            "Expected count of sides should be positive integer";
    private static final String WRONG_ACTUAL_COUNT_OF_SIDES = "Wrong actual count of sides";
    private static final String WRONG_TYPE_OF_INPUT_DATA = "Length of side should be double";

    /**
     * Returns array of triangle side length.
     *
     * @param inputData  string to parse
     * @param sideCount  count of figure sides
     *
     * @throws  NullPointerException
     *          if {@code inputData} is null
     * @throws  IllegalArgumentException
     *          if count of figure sides({@code sideCount}) isn't positive
     *
     * @return  triangle side lengths
     */
    public static double[] parseSides(String inputData, int sideCount) {
        if (inputData == null) {
            throw new NullPointerException(STRING_TO_PARSE_IS_NULL);
        }
        if (sideCount <= 0) {
            throw new IllegalArgumentException(SIDE_COUNT_NOT_POSITIVE);
        }
        return parse(inputData, sideCount);
    }

    /**
     * Returns array of triangle side length.
     * @param inputData  string to parse
     * @param sideCount  count of figure sides
     *
     * @return  triangle side lengths
     */
    private static double[] parse(String inputData, int sideCount) {
        inputData = allowCommas(inputData);
        return getSides(inputData, sideCount);
    }

    /**
     * Returns string where all commas replaced by points.
     *
     * @param inputData string to parse
     * @return string where all commas replaced by points.
     */
    private static String allowCommas(String inputData) {
        return inputData.replaceAll(",", ".");
    }

    /**
     * Returns array of triangle side length.
     *
     * @param inputData  string to parse
     * @param sideCount  count of figure sides
     *
     * @throws  IllegalArgumentException
     *          if actual count of figure sides(from {@code inputData}) is not equal to
     *          expected count of figure sides({@code sideCount})
     *
     * @return  triangle side lengths
     */
    private static double[] getSides(String inputData, int sideCount) {
        String[] sidesAsString = getSidesAsString(inputData);

        if (sidesAsString.length != sideCount) {
            throw new IllegalArgumentException(WRONG_ACTUAL_COUNT_OF_SIDES);
        }
        return getDoublesFromStrings(sidesAsString, sideCount);
    }

    /**
     * Returns array of triangle sides as strings.
     *
     * @param inputData  string to parse
     * @return  triangle sides as strings
     */
    private static String[] getSidesAsString(String inputData) {
        inputData = inputData.trim();
        return inputData.split("\\s+");
    }

    /**
     * Returns array of {@code double} based on array of {@code String}.
     *
     * @param sidesAsString  triangle sides as strings
     * @param sideCount  count of figure sides
     * @return  array of {@code double}
     */
    private static double[] getDoublesFromStrings(String[] sidesAsString, int sideCount) {
        double[] sides = new double[sideCount];

        for (int i = 0; i < sideCount; i++) {
            try {
                sides[i] = Double.valueOf(sidesAsString[i]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(WRONG_TYPE_OF_INPUT_DATA);
            }
        }
        return sides;
    }
}
