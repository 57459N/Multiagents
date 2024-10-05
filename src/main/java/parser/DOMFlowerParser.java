package parser;

import flower.Flower;
import flower.GrowingTips;
import flower.VisualParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMFlowerParser implements FlowerParser {

    private static final Logger logger = LogManager.getLogger(DOMFlowerParser.class);

    @Override
    public List<Flower> parse(String filePath) throws FileNotFoundException {
        List<Flower> flowers = new ArrayList<>();

        logger.info("Processing file: {}", filePath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document = null;
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new File(filePath));

        } catch (ParserConfigurationException e) {
            logger.error("Failed to create DocumentBuilder", e);
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            logger.error("File not found: {}", filePath);
            throw new FileNotFoundException(e.getMessage());
        } catch (IOException | SAXException e) {
            throw new RuntimeException(e);
        }
        document.getDocumentElement().normalize();

        NodeList flowerList = document.getElementsByTagName("Flower");
        logger.debug("Found {} flowers", flowerList.getLength());

        for (int i = 0; i < flowerList.getLength(); i++) {
            Element flowerElement = (Element) flowerList.item(i);

            String id = flowerElement.getAttribute("id");
            logger.debug("Parsing flower: {}", id);

            String name = flowerElement.getElementsByTagName("Name").item(0).getTextContent();
            String soil = flowerElement.getElementsByTagName("Soil").item(0).getTextContent();
            String origin = flowerElement.getElementsByTagName("Origin").item(0).getTextContent();

            VisualParameters visualParameters = getVisualParameters(flowerElement);
            GrowingTips growingTips = getGrowingTips(flowerElement);

            String multiplying = flowerElement.getElementsByTagName("Multiplying").item(0).getTextContent();

            Flower flower = new Flower(id, name, soil, origin, visualParameters, growingTips, multiplying);
            flowers.add(flower);
        }

        logger.info("Successfully parsed {} flowers", flowers.size());

        return flowers;
    }

    private static GrowingTips getGrowingTips(Element flowerElement) {
        GrowingTips growingTips = new GrowingTips();
        NodeList growingTipsList = flowerElement.getElementsByTagName("Tip");
        for (int j = 0; j < growingTipsList.getLength(); j++) {
            Element tipElement = (Element) growingTipsList.item(j);
            String type = tipElement.getAttribute("type");
            String value = tipElement.getTextContent();
            growingTips.addTip(type, value);
        }
        return growingTips;
    }

    private static VisualParameters getVisualParameters(Element flowerElement) {
        VisualParameters visualParameters = new VisualParameters();
        NodeList visualParamsList = flowerElement.getElementsByTagName("Parameter");
        for (int j = 0; j < visualParamsList.getLength(); j++) {
            Element paramElement = (Element) visualParamsList.item(j);
            String type = paramElement.getAttribute("type");
            String value = paramElement.getTextContent();
            visualParameters.addParameter(type, value);
        }
        return visualParameters;
    }
}
