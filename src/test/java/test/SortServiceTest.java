package test;

import entity.ArrayEntity;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import service.SortService;

public class SortServiceTest {

    private final SortService sortService = new SortService();
    private final ArrayEntity unsortedArray = new ArrayEntity(new int[]{5, 1, 4, 2, 8});
    private final int[] expectedSortedArray = new int[]{1, 2, 4, 5, 8};


    @Test
    public void testBubbleSort() {
        sortService.bubbleSort(unsortedArray);
        Assert.assertEquals(unsortedArray.getArray(), expectedSortedArray);
    }

    @Test
    public void testSelectionSort() {
        sortService.selectionSort(unsortedArray);
        Assert.assertEquals(unsortedArray.getArray(), expectedSortedArray);
    }

    @Test
    public void testQuickSort() {
        sortService.quickSort(unsortedArray);
        Assert.assertEquals(unsortedArray.getArray(), expectedSortedArray);
    }
}
