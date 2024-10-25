package geometry;

import java.util.Objects;

public class Triangle extends Shape {
    private final Point point1;
    private final Point point2;
    private final Point point3;

    public Triangle(String name, Point p1, Point p2, Point p3) {
        super(name);
        this.point1 = p1;
        this.point2 = p2;
        this.point3 = p3;
    }

    public Point getPoint(int idx) {
        switch (idx) {
            case 1:
                return point1;
            case 2:
                return point2;
            case 3:
                return point3;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return String.format("Triangle(%s){p1=%s, p2=%s, p3=%s}", name, point1, point2, point1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Triangle triangle = (Triangle) o;
        return Objects.equals(point1, triangle.point1) && Objects.equals(point2, triangle.point2) && Objects.equals(point3, triangle.point3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), point1, point2, point3);
    }
}