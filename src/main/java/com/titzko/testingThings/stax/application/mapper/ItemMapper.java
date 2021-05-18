package com.titzko.testingThings.stax.application.mapper;

import com.titzko.testingThings.stax.adapter.repositories.ItemRepository;
import com.titzko.testingThings.stax.application.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ItemMapper implements MappingCapable{

    static final String ITEM = "item";
    static final String ID = "id";
    static final String MODE = "mode";
    static final String UNIT = "unit";
    static final String CURRENT = "current";
    static final String INTERACTIVE = "interactive";

    @Autowired
    ItemRepository itemRepository;


    @Override
    public void mapToModels(File xmlFile) throws FileNotFoundException, XMLStreamException {
        List<Item> itemList = new ArrayList<>();
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        InputStream inputStream = new FileInputStream(xmlFile);
        XMLEventReader xmlEventReader = inputFactory.createXMLEventReader(inputStream);

        Item item = null;
        while(xmlEventReader.hasNext()){
            XMLEvent event = xmlEventReader.nextEvent();

            if(event.isStartElement()){
                StartElement startElement = event.asStartElement();
                String elementName = startElement.getName().getLocalPart();

                switch (elementName) {
                    case ITEM:
                        item = new Item();
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();
                            if (attribute.getName().toString().equals("date")) {
                                item.setDate(attribute.getValue());
                            }
                        }
                        break;
                    case ID:
                        event = xmlEventReader.nextEvent();
                        item.setId(Long.valueOf(event.asCharacters().getData()));
                        break;
                    case MODE:
                        event = xmlEventReader.nextEvent();
                        item.setMode(event.asCharacters().getData());
                        break;
                    case UNIT:
                        event = xmlEventReader.nextEvent();
                        item.setUnit(event.asCharacters().getData());
                        break;
                    case CURRENT:
                        event = xmlEventReader.nextEvent();
                        item.setCurrent(event.asCharacters().getData());
                        break;
                    case INTERACTIVE:
                        event = xmlEventReader.nextEvent();
                        item.setInteractive(event.asCharacters().getData());
                        break;
                }
            }if(event.isEndElement()){
                EndElement endElement = event.asEndElement();
                if(endElement.getName().getLocalPart().equals(ITEM)){
                    itemList.add(item);
                }
            }
        }
        itemRepository.saveAll(itemList);

    }


}
