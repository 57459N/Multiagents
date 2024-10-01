package test;

import entity.ArrayEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import service.ArrayService;

public class ArrayServiceTest {

    private ArrayService arrayService;
    private ArrayEntity arrayEntity;

    @BeforeMethod
    public void setUp() {
        arrayService = new ArrayService();
        arrayEntity = new ArrayEntity(new int[]{1, -2, 3, 4, 5});
    }

    @Test
    public void testFindMin() {
        int min = arrayService.findMin(arrayEntity);
        Assert.assertEquals(min, -2);
    }

    @Test
    public void testFindMax() {
        int max = arrayService.findMax(arrayEntity);
        Assert.assertEquals(max, 5);
    }

    @Test
    public void testReplaceElements_withMatchingCondition() {
        arrayService.replaceElements(arrayEntity, 5, value -> value == -2);
        Assert.assertEquals(arrayEntity.getArray(), new int[]{1, 5, 3, 4, 5}, "The array should have -2 replaced with 5");
    }

    @Test
    public void testReplaceElements_withNoMatchingCondition() {
        arrayService.replaceElements(arrayEntity, 5, value -> value == 6);
        Assert.assertEquals(arrayEntity.getArray(), new int[]{1, -2, 3, 4, 5}, "The array should remain unchanged since there are no 6s to replace");
    }

    @Test
    public void testReplaceElements_withMultipleReplacements() {
        arrayService.replaceElements(arrayEntity, 3, value -> value == 1);
        Assert.assertEquals(arrayEntity.getArray(), new int[]{3, -2, 3, 4, 5}, "The first element should be replaced with 3");
    }

    @Test
    public void testReplaceElements_withEmptyArray() {
        ArrayEntity emptyArrayEntity = new ArrayEntity(new int[]{});
        arrayService.replaceElements(emptyArrayEntity, 5, value -> value == 2);
        Assert.assertEquals(emptyArrayEntity.getArray(), new int[]{}, "The empty array should remain unchanged");
    }
}


