package service;

import entity.ArrayEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArrayService {
    private static final Logger logger = LogManager.getLogger(ArrayService.class);

    public int findMin(ArrayEntity arrayEntity) {
        int[] array = arrayEntity.getArray();
        int min = array[0];
        for (int value : array) {
            if (value < min) {
                min = value;
            }
        }
        logger.info("Минимальное значение найдено: {}", min);
        return min;
    }

    public int findMax(ArrayEntity arrayEntity) {
        int[] array = arrayEntity.getArray();
        int max = array[0];
        for (int value : array) {
            if (value > max) {
                max = value;
            }
        }
        logger.info("Максимальное значение найдено: {}", max);
        return max;
    }

    public void replaceElements(ArrayEntity arrayEntity, int oldValue, int newValue) {
        int[] array = arrayEntity.getArray();
        for (int i = 0; i < array.length; i++) {
            if (array[i] == oldValue) {
                array[i] = newValue;
            }
        }
        logger.info("Элементы массива заменены: {} на {}", oldValue, newValue);
    }

    public double calculateAverage(ArrayEntity arrayEntity) {
        int[] array = arrayEntity.getArray();
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        double average = (double) sum / array.length;
        logger.info("Среднее значение: " + average);
        return average;
    }

    public int calculateSum(ArrayEntity arrayEntity) {
        int[] array = arrayEntity.getArray();
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        logger.info("Сумма элементов: {}", sum);
        return sum;
    }

    public int countPositive(ArrayEntity arrayEntity) {
        int[] array = arrayEntity.getArray();
        int count = 0;
        for (int value : array) {
            if (value > 0) {
                count++;
            }
        }
        logger.info("Количество положительных элементов: {}", count);
        return count;
    }

    public int countNegative(ArrayEntity arrayEntity) {
        int[] array = arrayEntity.getArray();
        int count = 0;
        for (int value : array) {
            if (value < 0) {
                count++;
            }
        }
        logger.info("Количество отрицательных элементов: {}", count);
        return count;
    }
}
