package parser;

import flower.Flower;
import flower.GrowingTips;
import flower.VisualParameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.Characters;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class StAXFlowerParser implements FlowerParser {

    private static final Logger logger = LogManager.getLogger(StAXFlowerParser.class);

    @Override
    public List<Flower> parse(String filePath) throws FileNotFoundException {
        List<Flower> flowers = new ArrayList<>();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader;
        try {
            eventReader = factory.createXMLEventReader(new FileReader(filePath));
        } catch (FileNotFoundException e)  {
            logger.error("File not found: {}", filePath);
            throw new FileNotFoundException(e.getMessage());
        }catch (XMLStreamException e) {
            logger.error("Error while parsing {}: {}", filePath, e.getMessage());
            throw new RuntimeException(e);
        }
        logger.info("Processing file: {}", filePath);

        Flower currentFlower = null;
        StringBuilder currentValue = new StringBuilder();
        String currentType = null;

        while (eventReader.hasNext()) {
            XMLEvent event;
            try {
                event = eventReader.nextEvent();
            } catch (XMLStreamException e) {
                logger.error("Error while parsing {}: {}", filePath, e.getMessage());
                throw new RuntimeException(e);
            }

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                String qName = startElement.getName().getLocalPart();

                if ("Flower".equals(qName)) {
                    String id = startElement.getAttributeByName(new javax.xml.namespace.QName("id")).getValue();
                    currentFlower = new Flower(id, "", "", "", new VisualParameters(), new GrowingTips(), "");
                    logger.debug("Parsing flower: {}", currentFlower.getId());
                } else if ("Parameter".equals(qName) || "Tip".equals(qName)) {
                    currentType = startElement.getAttributeByName(new javax.xml.namespace.QName("type")).getValue();
                }
            } else if (event.isCharacters()) {
                currentValue.append(event.asCharacters().getData());
            } else if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                String qName = endElement.getName().getLocalPart();

                if ("Flower".equals(qName)) {
                    if (currentFlower != null) {
                        flowers.add(currentFlower);
                    }
                } else if ("Name".equals(qName)) {
                    if (currentFlower != null) {
                        currentFlower.setName(currentValue.toString().trim());
                    }
                } else if ("Soil".equals(qName)) {
                    if (currentFlower != null) {
                        currentFlower.setSoil(currentValue.toString().trim());
                    }
                } else if ("Origin".equals(qName)) {
                    if (currentFlower != null) {
                        currentFlower.setOrigin(currentValue.toString().trim());
                    }
                } else if ("Multiplying".equals(qName)) {
                    if (currentFlower != null) {
                        currentFlower.setMultiplying(currentValue.toString().trim());
                    }
                } else if ("Parameter".equals(qName) && currentFlower != null) {
                    String value = currentValue.toString().trim();
                    currentFlower.getVisualParameters().addParameter(currentType, value);
                } else if ("Tip".equals(qName) && currentFlower != null) {
                    String value = currentValue.toString().trim();
                    currentFlower.getGrowingTips().addTip(currentType, value);
                }

                currentValue.setLength(0);
            }
        }

        logger.info("Successfully parsed {} flowers", flowers.size());

        return flowers;
    }
}
