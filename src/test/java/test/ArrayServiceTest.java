package test;

import entity.ArrayEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.ArrayService;

public class ArrayServiceTest {
    
    private ArrayService arrayService;
    private ArrayEntity arrayEntity;

    @BeforeClass
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


}
