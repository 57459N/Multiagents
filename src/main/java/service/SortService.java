package service;

import entity.ArrayEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SortService {
    private static final Logger logger = LogManager.getLogger(SortService.class);

    public void bubbleSort(ArrayEntity arrayEntity) {
        int[] array = arrayEntity.getArray();
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
        logger.info("Массив отсортирован пузырьковой сортировкой.");
    }

    public void selectionSort(ArrayEntity arrayEntity) {
        int[] array = arrayEntity.getArray();
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }

        logger.info("Массив отсортирован методом выбора:");
    }

    public void quickSort(ArrayEntity arrayEntity) {
        int[] array = arrayEntity.getArray();
        quickSort(array, 0, array.length - 1);
        logger.info("Массив отсортирован быстрой сортировкой:");
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);

            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

}
