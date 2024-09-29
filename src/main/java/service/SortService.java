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
}
