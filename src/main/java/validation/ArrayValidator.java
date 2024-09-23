package validation;

import exception.InvalidArrayFormatException;

public class ArrayValidator {
    public static boolean isValidArrayString(String line) {
        return line.matches("^\\d+(\\s*[,-]\\s*\\d+)*$");
    }

    public static int[] parseArray(String line) throws InvalidArrayFormatException {
        if (!isValidArrayString(line)) {
            throw new InvalidArrayFormatException("Некорректная строка массива: " + line);
        }
        String[] parts = line.split("[,\\s-]+");
        int[] array = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            array[i] = Integer.parseInt(parts[i]);
        }
        return array;
    }
}
