package util;

import geometry.Point;
import geometry.Triangle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TriangleFileReader {
    private static final Logger logger = LogManager.getLogger(TriangleFileReader.class);

    public static Triangle[] readFromFile(String path) {
        logger.info(String.format("Reading file: %s", path));
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            List<Triangle> triangles = lines
                    .map(TriangleFileReader::processLine)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            logger.info(String.format("Finished reading. Got %d triangles: ", triangles.size()));
            return triangles.toArray(new Triangle[0]);
        } catch (Exception e) {
            logger.error(String.format("Error while reading file: %s\n%s", path, e));
            return new Triangle[0];
        }
    }

    private static Triangle processLine(String line) {
        String[] parts = line.split(" ");
        try {
            double x1 = Double.parseDouble(parts[0]);
            double y1 = Double.parseDouble(parts[1]);
            double x2 = Double.parseDouble(parts[2]);
            double y2 = Double.parseDouble(parts[3]);
            double x3 = Double.parseDouble(parts[4]);
            double y3 = Double.parseDouble(parts[5]);

            Point p1 = new Point(x1, y1);
            Point p2 = new Point(x2, y2);
            Point p3 = new Point(x3, y3);
            Triangle t = new Triangle("Triangle", p1, p2, p3);
            logger.debug(String.format("Successfully created triangle: %s", t));
            return t;
        } catch (Exception e) {
            logger.warn(String.format("Incorrect line: %s", line));
            return null;
        }
    }
}
