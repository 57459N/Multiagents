package util;

import entity.ArrayEntity;
import exception.InvalidArrayFormatException;
import validation.ArrayValidator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ArrayFileReader {

    public static ArrayEntity readArrayFromFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int[] array = ArrayValidator.parseArray(line);
                    return new ArrayEntity(array);
                } catch (InvalidArrayFormatException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        return null;
    }
}
