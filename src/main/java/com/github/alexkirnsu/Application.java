package com.github.alexkirnsu;

import com.github.alexkirnsu.model.Triangle;
import com.github.alexkirnsu.parser.DataParser;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] sides = getTriangleSides(scanner);
        Triangle triangle = new Triangle(sides);

        System.out.println("Triangle is " + triangle.getType());
    }

    private static double[] getTriangleSides(Scanner scanner) {
        System.out.println("Please, enter length of triangle sides via space...");
        String arguments = scanner.nextLine();

        try {
            return DataParser.parseSides(arguments, Triangle.SIDE_COUNT);
        } catch(RuntimeException e) {
            System.out.println(e.getMessage());
            return getTriangleSides(scanner);
        }
    }
}