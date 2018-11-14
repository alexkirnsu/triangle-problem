package com.github.alexkirnsu.model;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class TriangleTest {

    private static final String TRIANGLE_INEQUALITY_DOESNT_HOLD = "Triangle inequality doesn't hold";

    private final double[] illegalTriangleLengths = {1, 2, 3};
    private final double[] equilateralTriangleLengths = {2, 2, 2};
    private final double[] isoscelesTriangleLengths = {1, 10.0000000001, 10.0000000001};
    private final double[] scaleneTriangleLengths = {3, 4, 5};
    private final double[] differenceBetweenTwoLengthLessThenThreshold =
            {1, 10 + 0.1 * Triangle.THRESHOLD, 10 + 0.2 * Triangle.THRESHOLD};

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void constructor_IfTriangleInequalityDoesntHold_ThrowIllegalArgumentException() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage(TRIANGLE_INEQUALITY_DOESNT_HOLD);

        new Triangle(illegalTriangleLengths);
    }

    @Test
    public void getType_IfAllLengthsOfTriangleAreEqual_ReturnEquilateral() {
        Triangle equilateralTriangle = new Triangle(equilateralTriangleLengths);

        assertEquals(equilateralTriangle.getType(), TriangleType.EQUILATERAL);
    }

    @Test
    public void getType_IfTwoLengthsOfTriangleAreEqual_ReturnIsosceles() {
        Triangle isoscelesTriangle = new Triangle(isoscelesTriangleLengths);

        assertEquals(isoscelesTriangle.getType(), TriangleType.ISOSCELES);
    }

    @Test
    public void getType_IfAllLengthsOfTriangleAreDifferent_ReturnScalene() {
        Triangle scaleneTriangle = new Triangle(scaleneTriangleLengths);

        assertEquals(scaleneTriangle.getType(), TriangleType.SCALENE);
    }

    @Test
    public void getType_IfDifferenceBetweenTwoLengthLessThenTriangle_ReturnIsosceles() {
        Triangle triangleWithTwoCloseSides = new Triangle(differenceBetweenTwoLengthLessThenThreshold);

        assertEquals(triangleWithTwoCloseSides.getType(), TriangleType.ISOSCELES);
    }
}