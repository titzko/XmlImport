package com.titzko.testingThings.stax.adapter;

import com.titzko.testingThings.stax.adapter.repositories.AddressRepository;
import com.titzko.testingThings.stax.adapter.repositories.ItemRepository;
import com.titzko.testingThings.stax.application.mapper.ItemMapper;
import com.titzko.testingThings.stax.application.mapper.MapperRegistry;
import com.titzko.testingThings.stax.application.mapper.MappingCapable;
import com.titzko.testingThings.stax.application.model.MessageType;
import com.titzko.testingThings.stax.port.XmlImportPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Optional;


@Service
public class XmlAdapter implements XmlImportPort {


    @Autowired
    AddressRepository addressRepository;
    @Autowired
    XmlImportPort xmlImportPort;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemMapper itemMapper;
    @Autowired
    MapperRegistry mapperRegistry;


    @Override
    public void importXmlFile(String filePath) throws FileNotFoundException, XMLStreamException {
        File xmlFile = new File(filePath);
        MessageType xmlType = this.getXmlType(xmlFile);
        Optional<MappingCapable> messageMapper = mapperRegistry.getMessageMapper(xmlType);
        if(messageMapper.isPresent()){
            messageMapper.get().mapToModels(xmlFile);
        }

    }

    public MessageType getXmlType(File xmlFile) throws FileNotFoundException, XMLStreamException {
        InputStream inputStream = new FileInputStream(xmlFile);
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = inputFactory.createXMLEventReader(inputStream);

        while (xmlEventReader.hasNext()) {
            XMLEvent event = xmlEventReader.nextEvent();

            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                String elementName = startElement.getName().getLocalPart();
                if (elementName.equals("MESSAGE_TYPE")) {
                    event = xmlEventReader.nextEvent();
                    String messageType = event.asCharacters().getData();
                    return MessageType.valueOf(messageType.toUpperCase());
                }

            }
        }
        return null;
    }
}
