package service;

import entity.ArrayEntity;
import exception.InvalidArrayFormatException;
import validation.ArrayValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

public class FileArrayService {
    private static final Logger logger = LogManager.getLogger(FileArrayService.class);

    public Optional<ArrayEntity> readArrayFromFile(Path filePath) {
        try (Stream<String> lines = Files.lines(filePath)) {
            Optional<int[]> validArray = lines
                    .map(this::processLine)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .findFirst();

            if (validArray.isPresent()) {
                return Optional.of(new ArrayEntity(validArray.get()));
            } else {
                logger.error("Файл не содержит корректных данных для создания массива.");
                return Optional.empty();
            }
        } catch (IOException e) {
            logger.error("Ошибка чтения файла: " + e.getMessage());
            return Optional.empty();
        }
    }

    private Optional<int[]> processLine(String line) {
        try {
            int[] array = ArrayValidator.parseArray(line);
            logger.info("Корректная строка: " + line);
            return Optional.of(array);
        } catch (InvalidArrayFormatException e) {
            logger.warn("Некорректная строка пропущена: " + line);
            return Optional.empty();
        }
    }
}
