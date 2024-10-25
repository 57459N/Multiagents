package test;

import action.TriangleActions;
import geometry.Point;
import geometry.Triangle;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TriangleActionsTest {

    @Test
    public void testCalculateArea() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 3);
        Point p3 = new Point(4, 0);
        Triangle triangle = new Triangle("TestTriangle", p1, p2, p3);

        double area = TriangleActions.calculateArea(triangle);
        Assert.assertEquals(area, 6.0, "Area must be 6.0.");
    }

    @Test
    public void testCalculatePerimeter() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 3);
        Point p3 = new Point(4, 0);
        Triangle triangle = new Triangle("TestTriangle", p1, p2, p3);

        double perimeter = TriangleActions.calculatePerimeter(triangle);
        Assert.assertEquals(perimeter, 12.0, "Perimeter must be 12.0.");
    }

    @Test
    public void testIsRightTriangle() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(0, 3);
        Point p3 = new Point(4, 0);
        Triangle triangle = new Triangle("TestTriangle", p1, p2, p3);

        boolean isRight = TriangleActions.isRightTriangle(triangle);
        Assert.assertTrue(isRight, "Triangle must be right.");
    }

    @Test
    public void testIsIsosceles() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(2, 3);
        Point p3 = new Point(4, 0);
        Triangle triangle = new Triangle("TestTriangle", p1, p2, p3);

        boolean isIsosceles = TriangleActions.isIsosceles(triangle);
        Assert.assertTrue(isIsosceles, "Triangle must be isosceles.");
    }

    @Test
    public void testIsEquilateral() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(1, Math.sqrt(3));
        Point p3 = new Point(2, 0);
        Triangle triangle = new Triangle("TestTriangle", p1, p2, p3);

        boolean isEquilateral = TriangleActions.isEquilateral(triangle);
        Assert.assertTrue(isEquilateral, "Triangle must be equilateral.");
    }

    @DataProvider(name = "acuteTriangles")
    public Object[][] acuteTriangles() {
        return new Object[][]{
                {new Triangle("Triangle1", new Point(0, 0), new Point(2, 0), new Point(1, 1)), true}, // Acute triangle
                {new Triangle("NonAcuteTriangle1", new Point(0, 0), new Point(2, 0), new Point(2, 2)), false}, // Right triangle
        };
    }

    @DataProvider(name = "obtuseTriangles")
    public Object[][] obtuseTriangles() {
        return new Object[][]{
                {new Triangle("Triangle4", new Point(0, 0), new Point(3, 0), new Point(-1, 4)), true}, // Obtuse triangle
                {new Triangle("Triangle1", new Point(0, 0), new Point(2, 0), new Point(1, 1)), false}, // Acute triangle
        };
    }

    @Test(dataProvider = "acuteTriangles")
    public void testIsAcuteTriangle(Triangle triangle, boolean expected) {
        Assert.assertEquals(TriangleActions.isAcuteTriangle(triangle), expected);
    }

    @Test(dataProvider = "obtuseTriangles")
    public void testIsObtuseTriangle(Triangle triangle, boolean expected) {
        Assert.assertEquals(TriangleActions.isObtuseTriangle(triangle), expected);
    }
}

