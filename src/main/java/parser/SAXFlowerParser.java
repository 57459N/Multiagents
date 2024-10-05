package parser;

import flower.Flower;
import flower.GrowingTips;
import flower.VisualParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SAXFlowerParser implements FlowerParser {

    private static final Logger logger = LogManager.getLogger(SAXFlowerParser.class);

    @Override
    public List<Flower> parse(String filePath) throws RuntimeException, FileNotFoundException {
        List<Flower> flowers = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException("File not found: " + filePath);
            }

            logger.info("Processing file: {}", filePath);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(file, new FlowerHandler(flowers));
            logger.info("Successfully parsed {} flowers", flowers.size());
        } catch (FileNotFoundException e) {
            logger.error("File not found: {}", filePath);
            throw new FileNotFoundException(e.getMessage());
        } catch (Exception e) {
            logger.error("Error while parsing {}: {}", filePath, e.getMessage());
            throw new RuntimeException(e);
        }
        return flowers;
    }

    private static class FlowerHandler extends DefaultHandler {
        private static final String FLOWER_TAG = "Flower";
        private static final String NAME_TAG = "Name";
        private static final String SOIL_TAG = "Soil";
        private static final String ORIGIN_TAG = "Origin";
        private static final String PARAMETER_TAG = "Parameter";
        private static final String TIP_TAG = "Tip";
        private static final String MULTIPLYING_TAG = "Multiplying";

        private final List<Flower> flowers;
        private Flower currentFlower;
        private StringBuilder currentValue;
        private String currentParameterType;
        private String currentTipType;

        public FlowerHandler(List<Flower> flowers) {
            this.flowers = flowers;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (FLOWER_TAG.equals(qName)) {
                String id = attributes.getValue("id");
                currentFlower = new Flower(id, "", "", "", new VisualParameters(), new GrowingTips(), "");
                logger.debug("Parsing flower: {}", id);
            } else if (PARAMETER_TAG.equals(qName)) {
                currentParameterType = attributes.getValue("type");
            } else if (TIP_TAG.equals(qName)) {
                currentTipType = attributes.getValue("type");
            }
            currentValue = new StringBuilder();
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            currentValue.append(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (FLOWER_TAG.equals(qName)) {
                flowers.add(currentFlower);
            } else if (NAME_TAG.equals(qName)) {
                currentFlower.setName(currentValue.toString().trim());
            } else if (SOIL_TAG.equals(qName)) {
                currentFlower.setSoil(currentValue.toString().trim());
            } else if (ORIGIN_TAG.equals(qName)) {
                currentFlower.setOrigin(currentValue.toString().trim());
            } else if (PARAMETER_TAG.equals(qName) && currentFlower != null) {
                String value = currentValue.toString().trim();
                currentFlower.getVisualParameters().addParameter(currentParameterType, value);
            } else if (TIP_TAG.equals(qName) && currentFlower != null) {
                String value = currentValue.toString().trim();
                currentFlower.getGrowingTips().addTip(currentTipType, value);
            } else if (MULTIPLYING_TAG.equals(qName)) {
                currentFlower.setMultiplying(currentValue.toString().trim());
            }

            if (PARAMETER_TAG.equals(qName)) {
                currentParameterType = null;
            } else if (TIP_TAG.equals(qName)) {
                currentTipType = null;
            }
        }
    }

}
