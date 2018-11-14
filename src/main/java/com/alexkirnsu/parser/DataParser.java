package com.alexkirnsu.parser;


import lombok.extern.log4j.Log4j2;

@Log4j2
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
            log.info("inputData parameter is null");
            throw new NullPointerException(STRING_TO_PARSE_IS_NULL);
        }
        if (sideCount <= 0) {
            log.info("sideCount parameter isn't positive ({})", sideCount);
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
        int actualSideCount = sidesAsString.length;

        if (actualSideCount != sideCount) {
            log.info("Actual count of parameters ({}) isn't equal to expected one ({})",
                    actualSideCount, sideCount);
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
                log.info("Input side length is not double ({})", sidesAsString[i]);
                throw new IllegalArgumentException(WRONG_TYPE_OF_INPUT_DATA);
            }
        }
        return sides;
    }
}
