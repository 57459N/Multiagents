package validation;

import geometry.Point;

public class TriangleValidator {

    public static boolean isValidTriangle(Point p1, Point p2, Point p3) {
        double area = (p1.getX() * (p2.getY() - p3.getY()) +
                p2.getX() * (p3.getY() - p1.getY()) +
                p3.getX() * (p1.getY() - p2.getY())) / 2.0;
        return area != 0;
    }

    public static boolean isValidLine(String line) {
        String[] parts = line.trim().split(" ");
        if (parts.length != 6) {
            return false;
        }
        try {
            for (String part : parts) {
                Double.parseDouble(part);
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

