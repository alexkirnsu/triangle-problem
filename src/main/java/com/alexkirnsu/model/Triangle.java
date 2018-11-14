package com.alexkirnsu.model;

import static java.lang.Math.abs;

public class Triangle {
    /**
     * Precision of comparison for {@code double}
     */
    public static final double THRESHOLD = 1e-14;
    public static final int SIDE_COUNT = 3;
    private static final String TRIANGLE_INEQUALITY_DOESNT_HOLD = "Triangle inequality doesn't hold";

    private double[] sides;
    private TriangleType type;

    /**
     * Creates triangle instance if {@code sides} satisfy
     * triangle inequality.
     *
     * @param sides  lengths of triangle sides
     */
    public Triangle(double[] sides) {
        if (!holdsTriangleInequality(sides)) {
            throw new IllegalArgumentException(TRIANGLE_INEQUALITY_DOESNT_HOLD);
        }
        this.sides = sides;
        this.type = TriangleType.SCALENE;
    }

    /**
     * Returns true if {@code sides} satisfy triangle inequality
     * otherwise false.
     *
     * @param sides  lengths of triangle sides
     * @return  true if {@code sides} satisfy triangle inequality
     * otherwise false.
     */
    private boolean holdsTriangleInequality(double[] sides) {
        for (int i = 0; i < SIDE_COUNT; i++) {
            if (sides[i] + sides[(i + 1) % SIDE_COUNT] <= sides[(i + 2) % SIDE_COUNT]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns suitable {@code TriangleType} object
     *
     * @return {@code TriangleType} object
     */
    public TriangleType getType() {
        int numberOfEqualSidePairs = getNumberOfEqualSidePairs();

        if (numberOfEqualSidePairs == SIDE_COUNT) {
            type = TriangleType.EQUILATERAL;
        } else if (numberOfEqualSidePairs > 0) {
            type = TriangleType.ISOSCELES;
        }
        return type;
    }

    /**
     * Returns number of equal triangle side pairs
     *
     * @return number of equal pairs
     */
    private int getNumberOfEqualSidePairs() {
        int numberOfEqualSidePairs = 0;

        for (int i = 0; i < SIDE_COUNT; i++) {
            if (abs(sides[i] - sides[(i + 1) % SIDE_COUNT]) < THRESHOLD) {
                numberOfEqualSidePairs++;
            }
        }
        return numberOfEqualSidePairs;
    }
}