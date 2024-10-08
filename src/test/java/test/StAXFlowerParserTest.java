package test;

import flower.Flower;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import parser.StAXFlowerParser;

import java.io.FileNotFoundException;
import java.util.List;

public class StAXFlowerParserTest {

    private StAXFlowerParser stAXFlowerParser;
    private String xmlFilePath;

    @BeforeClass
    public void setUp() {
        stAXFlowerParser = new StAXFlowerParser();
        xmlFilePath = "src/main/resources/flowers.xml";
    }

    @Test
    public void testParseFlowers() throws FileNotFoundException {
        List<Flower> flowers = stAXFlowerParser.parse(xmlFilePath);
        Assert.assertNotNull(flowers, "Parsed flowers list should not be null");
        Assert.assertFalse(flowers.isEmpty(), "Parsed flowers list should not be empty");
        Assert.assertEquals(flowers.size(), 16, "Expected number of flowers should be 16");

        Flower firstFlower = flowers.get(0);
        Assert.assertEquals(firstFlower.getName(), "Роза", "First flower name should be Роза");
        Assert.assertEquals(firstFlower.getSoil(), "Грунтовая", "First flower soil should be Грунтовая");
        Assert.assertEquals(firstFlower.getOrigin(), "Иран", "First flower origin should be Иран");
        Assert.assertEquals(firstFlower.getMultiplying(), "Черенками", "First flower multiplying should be Черенками");
        Assert.assertEquals(firstFlower.getVisualParameters().getParameter("stemColor"), "Красный", "Stem color should be Красный");

        Flower secondFlower = flowers.get(1);
        Assert.assertEquals(secondFlower.getName(), "Тюльпан", "Second flower name should be Тюльпан");
        Assert.assertEquals(secondFlower.getSoil(), "Дерново-подзолистая", "Second flower soil should be Дерново-подзолистая");
        Assert.assertEquals(secondFlower.getOrigin(), "Турция", "Second flower origin should be Турция");
        Assert.assertEquals(secondFlower.getMultiplying(), "Семена", "Second flower multiplying should be Семена");
        Assert.assertEquals(secondFlower.getVisualParameters().getParameter("stemColor"), "Желтый", "Stem color should be Желтый");
    }

    @Test
    public void testParseInvalidFile() {
        String invalidXmlFilePath = "invalid_flowers.xml";
        Assert.assertThrows(FileNotFoundException.class, () -> stAXFlowerParser.parse(invalidXmlFilePath));
    }
}
