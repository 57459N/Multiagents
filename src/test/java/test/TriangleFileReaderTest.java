package test;

import geometry.Triangle;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.TriangleFileReader;

public class TriangleFileReaderTest {

    @Test
    public void testReadFromFile_withValidTriangle() {
        String path = "src/test/resources/triangles/valid_triangle.txt";
        Triangle[] triangles = TriangleFileReader.readFromFile(path);

        Assert.assertNotNull(triangles, "Triangle array should not be null.");
        Assert.assertEquals(triangles.length, 1, "Should contain one valid triangle.");
    }

    @Test
    public void testReadFromFile_withNotEnoughData() {
        String path = "src/test/resources/triangles/invalid_triangle_not_enough_data.txt";
        Triangle[] triangles = TriangleFileReader.readFromFile(path);

        Assert.assertNotNull(triangles, "Triangle array should not be null.");
        Assert.assertEquals(triangles.length, 0, "Should not contain any valid triangles.");
    }

    @Test
    public void testReadFromFile_withInvalidCharacters() {
        String path = "src/test/resources/triangles/invalid_triangle_invalid_characters.txt";
        Triangle[] triangles = TriangleFileReader.readFromFile(path);

        Assert.assertNotNull(triangles, "Triangle array should not be null.");
        Assert.assertEquals(triangles.length, 0, "Should not contain any valid triangles.");
    }

    @Test
    public void testReadFromFile_withMixedValidAndInvalid() {
        String path = "src/test/resources/triangles/mixed_valid_and_invalid.txt";
        Triangle[] triangles = TriangleFileReader.readFromFile(path);

        Assert.assertNotNull(triangles, "Triangle array should not be null.");
        Assert.assertEquals(triangles.length, 1, "Should contain only one valid triangle.");
    }
}
