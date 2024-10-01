package test;

import entity.ArrayEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.FileArrayService;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class FileArrayServiceTest {

    private FileArrayService fileArrayService;

    @BeforeClass
    public void setUp() {
        fileArrayService = new FileArrayService();
    }

    private Path getResourcePath(String resourceName) throws URISyntaxException {
        String resourcePath = "arrays/" + resourceName;
        URL resourceUrl = getClass().getClassLoader().getResource(resourcePath);

        if (resourceUrl == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }

        return Paths.get(resourceUrl.toURI());
    }

    @Test
    public void testReadCorrectArray() throws URISyntaxException {
        Path path = getResourcePath("correct_array.txt");
        Optional<ArrayEntity> result = fileArrayService.readArrayFromFile(path);

        Assert.assertTrue(result.isPresent(), "Array should be present");
        Assert.assertEquals(result.get().getArray(), new int[]{1, 2, 3}, "Array should be {1, 2, 3}");
    }

    @Test
    public void testReadCorrectSecondArray() throws URISyntaxException {
        Path path = getResourcePath("correct_second_array.txt");
        Optional<ArrayEntity> result = fileArrayService.readArrayFromFile(path);

        Assert.assertTrue(result.isPresent(), "Array should be present");
        Assert.assertEquals(result.get().getArray(), new int[]{4, 5, 6}, "Array should be {4, 5, 6}");
    }

    @Test
    public void testReadIncorrectArray() throws URISyntaxException {
        Path path = getResourcePath("incorrect_array.txt");
        Optional<ArrayEntity> result = fileArrayService.readArrayFromFile(path);

        Assert.assertFalse(result.isPresent(), "Array should not be present as all lines are incorrect");
    }

    @Test
    public void testReadMultipleCorrectArrays() throws URISyntaxException {
        Path path = getResourcePath("multiple_correct_arrays.txt");
        Optional<ArrayEntity> result = fileArrayService.readArrayFromFile(path);

        Assert.assertTrue(result.isPresent(), "Array should be present");
        Assert.assertEquals(result.get().getArray(), new int[]{7, 8, 9}, "Array should be {7, 8, 9} as it's the first correct line");
    }
}
