package com.github.alexkirnsu.model;

public enum TriangleType {
    EQUILATERAL("equilateral"),
    ISOSCELES("isosceles"),
    SCALENE("scalene");

    private final String simpleName;

    private TriangleType(String simpleName) {
        this.simpleName = simpleName;
    }

    @Override
    public String toString() {
        return simpleName;
    }
}
