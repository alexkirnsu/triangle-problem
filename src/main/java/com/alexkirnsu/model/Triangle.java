package com.alexkirnsu.model;

import lombok.extern.log4j.Log4j2;

import java.util.ResourceBundle;

import static java.lang.Math.abs;

@Log4j2
public class Triangle {
    /**
     * Precision of comparison for {@code double}
     */
    public static final double THRESHOLD = 1e-14;
    public static final int SIDE_COUNT = 3;
    private static final ResourceBundle resource;
    private static final String TRIANGLE_INEQUALITY_DOESNT_HOLD;

    private double[] sides;
    private TriangleType type;

    static {
        resource = ResourceBundle.getBundle("error.errorMessage");
        TRIANGLE_INEQUALITY_DOESNT_HOLD =
                resource.getString("TRIANGLE_INEQUALITY_DOESNT_HOLD");
    }
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
        this.type = defineType();
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
                log.info("Sides are not satisfy triangle inequality ({} + {} <= {}", sides[i],
                        sides[(i + 1) % SIDE_COUNT], sides[(i + 2) % SIDE_COUNT]);
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
    private TriangleType defineType() {
        int numberOfEqualSidePairs = getNumberOfEqualSidePairs();

        this.type = TriangleType.SCALENE;
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

    /**
     * Returns type (@code TriangleType) of triangle
     *
     * @return type field
     */
    public TriangleType getType() {
        return type;
    }
}