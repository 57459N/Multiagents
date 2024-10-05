package parser;

import flower.Flower;

import java.io.FileNotFoundException;
import java.util.List;

public interface FlowerParser {
    List<Flower> parse(String filePath) throws FileNotFoundException;
}

