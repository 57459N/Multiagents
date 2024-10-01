package validation;

import exception.InvalidArrayFormatException;


public class ArrayValidator {
    static final String ValidArrayStringRegex = "^\\d+(\\s*[,-]\\s*\\d+)*$";
    static final String ArrayPartsRegex = "[,\\s-]+";

    public static boolean isValidArrayString(String line) {
        return line.matches(ValidArrayStringRegex);
    }

    public static int[] parseArray(String line) throws InvalidArrayFormatException {
        if (!isValidArrayString(line)) {
            throw new InvalidArrayFormatException("Некорректная строка массива: ".concat(line));
        }
        String[] parts = line.split(ArrayPartsRegex);
        int[] array = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            array[i] = Integer.parseInt(parts[i]);
        }
        return array;
    }
}
