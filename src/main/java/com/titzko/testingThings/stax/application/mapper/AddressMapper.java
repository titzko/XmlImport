package com.titzko.testingThings.stax.application.mapper;

import com.titzko.testingThings.stax.adapter.repositories.AddressRepository;
import com.titzko.testingThings.stax.application.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressMapper implements MappingCapable{

    static final String ADDRESS = "address";
    static final String ID = "id";
    static final String NAME = "name";
    static final String STREET = "street";
    static final String PHONE_NUMBER = "phoneNumber";

    @Autowired
    AddressRepository addressRepository;

    @Override
    public void mapToModels(File xmlFile) throws FileNotFoundException, XMLStreamException {
        List<Address> addresses = new ArrayList<>();
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        InputStream inputStream = new FileInputStream(xmlFile);
        XMLEventReader xmlEventReader = inputFactory.createXMLEventReader(inputStream);

        Address address = null;
        while(xmlEventReader.hasNext()){
            XMLEvent event = xmlEventReader.nextEvent();

            if(event.isStartElement()){
                StartElement startElement = event.asStartElement();
                String elementName = startElement.getName().getLocalPart();

                switch (elementName) {
                    case ID:
                        address = new Address();
                        event = xmlEventReader.nextEvent();
                        address.setId(Long.valueOf(event.asCharacters().getData()));
                        break;
                    case NAME:
                        event = xmlEventReader.nextEvent();
                        address.setName(event.asCharacters().getData());
                        break;
                    case STREET:
                        event = xmlEventReader.nextEvent();
                        address.setStreet(event.asCharacters().getData());
                        break;
                    case PHONE_NUMBER:
                        event = xmlEventReader.nextEvent();
                        address.setPhoneNumber(event.asCharacters().getData());
                        break;

                }
            }if(event.isEndElement()){
                EndElement endElement = event.asEndElement();
                if(endElement.getName().getLocalPart().equals(ADDRESS)){
                    addresses.add(address);
                }
            }
        }
        addressRepository.saveAll(addresses);

    }
}



