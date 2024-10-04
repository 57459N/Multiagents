package test;

import entity.ArrayEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArrayEntityStreamTest {

    private ArrayEntity arrayEntity;

    @BeforeMethod
    public void setUp() {
        arrayEntity = new ArrayEntity(new int[]{1, -2, 3, 4, 5});
    }

    @Test
    public void testStream_sum() {
        int sum = arrayEntity.stream().mapToInt(Integer::intValue).sum();
        Assert.assertEquals(sum, 11, "The sum of the elements in the array should be 11.");
    }

    @Test
    public void testStream_filterPositiveNumbers() {
        List<Integer> positives = arrayEntity.stream()
                .filter(value -> value > 0)
                .collect(Collectors.toList());
        Assert.assertEquals(positives, List.of(1, 3, 4, 5), "Should filter and return all positive numbers.");
    }

    @Test
    public void testStream_max() {
        int max = arrayEntity.stream().mapToInt(Integer::intValue).max().orElseThrow();
        Assert.assertEquals(max, 5, "The max element should be 5.");
    }

    @Test
    public void testStream_min() {
        int min = arrayEntity.stream().mapToInt(Integer::intValue).min().orElseThrow();
        Assert.assertEquals(min, -2, "The min element should be -2.");
    }

    @Test
    public void testStream_count() {
        long count = arrayEntity.stream().count();
        Assert.assertEquals(count, 5L, "The count of elements should be 5.");
    }

    @Test
    public void testStream_collectToList() {
        List<Integer> list = arrayEntity.stream().collect(Collectors.toList());
        Assert.assertEquals(list, List.of(1, -2, 3, 4, 5), "The list should match the array elements.");
    }

    @Test
    public void testStream_average() {
        double average = arrayEntity.stream().mapToInt(Integer::intValue).average().orElseThrow();
        Assert.assertEquals(average, 2.2, "The average should be 2.2.");
    }

    @Test
    public void testStream_allMatch() {
        boolean allMatch = arrayEntity.stream().allMatch(value -> value != 0);
        Assert.assertTrue(allMatch, "All elements should be non-zero.");
    }

    @Test
    public void testStream_anyMatch() {
        boolean anyMatch = arrayEntity.stream().anyMatch(value -> value < 0);
        Assert.assertTrue(anyMatch, "At least one element should be negative.");
    }

    @Test
    public void testStream_noneMatch() {
        boolean noneMatch = arrayEntity.stream().noneMatch(value -> value == 0);
        Assert.assertTrue(noneMatch, "No elements should be zero.");
    }

    @Test
    public void testStream_sorted() {
        List<Integer> sorted = arrayEntity.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(sorted, List.of(-2, 1, 3, 4, 5), "The elements should be sorted.");
    }

    @Test
    public void testStream_limit() {
        List<Integer> limited = arrayEntity.stream().limit(3).collect(Collectors.toList());
        Assert.assertEquals(limited, List.of(1, -2, 3), "The limited stream should contain the first three elements.");
    }

    @Test
    public void testStream_skip() {
        List<Integer> skipped = arrayEntity.stream().skip(2).collect(Collectors.toList());
        Assert.assertEquals(skipped, List.of(3, 4, 5), "The stream should skip the first two elements.");
    }

    @Test
    public void testParallelStream() {
        List<Integer> list = arrayEntity.parallelStream().collect(Collectors.toList());
        Assert.assertEquals(list, List.of(1, -2, 3, 4, 5), "The parallel stream should return the same elements.");
    }
}
