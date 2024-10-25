package action;

import geometry.Point;
import geometry.Triangle;

public class TriangleActions {

    public static double calculateArea(Triangle triangle) {
        Point p1 = triangle.getPoint(1);
        Point p2 = triangle.getPoint(2);
        Point p3 = triangle.getPoint(3);
        return Math.abs(
                (p1.getX() * (p2.getY() - p3.getY()) +
                        p2.getX() * (p3.getY() - p1.getY()) +
                        p3.getX() * (p1.getY() - p2.getY())) / 2.0);
    }

    public static double calculatePerimeter(Triangle triangle) {
        return distance(triangle.getPoint(1), triangle.getPoint(2)) +
                distance(triangle.getPoint(2), triangle.getPoint(3)) +
                distance(triangle.getPoint(3), triangle.getPoint(1));
    }

    private static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.getX() - p1.getX(), 2) + Math.pow(p2.getY() - p1.getY(), 2));
    }

    private static Double[] getDistances(Triangle t) {
        return new Double[]{
                distance(t.getPoint(1), t.getPoint(2)),
                distance(t.getPoint(2), t.getPoint(3)),
                distance(t.getPoint(3), t.getPoint(1))};
    }

    public static boolean isAcuteTriangle(Triangle triangle) {
        Double[] distances = getDistances(triangle);

        double a = distances[0];
        double b = distances[1];
        double c = distances[2];

        // Use the properties of triangle sides to check angles
        return (a * a + b * b > c * c &&
                b * b + c * c > a * a &&
                c * c + a * a > b * b);
    }

    public static boolean isObtuseTriangle(Triangle triangle) {
        Double[] distances = getDistances(triangle);

        double a = distances[0];
        double b = distances[1];
        double c = distances[2];

        // Use the properties of triangle sides to check angles
        return (a * a + b * b < c * c ||
                b * b + c * c < a * a ||
                c * c + a * a < b * b);
    }

    public static boolean isRightTriangle(Triangle triangle) {
        Double[] distances = getDistances(triangle);

        double a = distances[0];
        double b = distances[1];
        double c = distances[2];

        // Check if the triangle satisfies the Pythagorean theorem
        return (Math.abs(a * a + b * b - c * c) < 1e-10 ||
                Math.abs(b * b + c * c - a * a) < 1e-10 ||
                Math.abs(c * c + a * a - b * b) < 1e-10);
    }

    public static boolean isIsosceles(Triangle triangle) {
        Double[] distances = getDistances(triangle);

        double a = distances[0];
        double b = distances[1];
        double c = distances[2];

        return (Math.abs(a - b) < 1e-10 ||
                Math.abs(b - c) < 1e-10 ||
                Math.abs(c - a) < 1e-10);
    }

    public static boolean isEquilateral(Triangle triangle) {
        Double[] distances = getDistances(triangle);

        double a = distances[0];
        double b = distances[1];
        double c = distances[2];

        return (Math.abs(a - b) < 1e-10 &&
                Math.abs(b - c) < 1e-10 &&
                Math.abs(c - a) < 1e-10);
    }
}
