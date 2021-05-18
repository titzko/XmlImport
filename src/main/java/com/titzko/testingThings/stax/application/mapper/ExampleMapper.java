package com.titzko.testingThings.stax.application.mapper;

import com.titzko.testingThings.stax.adapter.repositories.NoteRepository;
import com.titzko.testingThings.stax.application.model.Example;
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
public class ExampleMapper implements MappingCapable{


    static final String NOTE = "note";
    static final String ID = "id";
    static final String RECEIVER = "receiver";
    static final String SENDER = "sender";
    static final String HEADING = "heading";
    static final String MESSAGE = "message";

    @Autowired
    NoteRepository noteRepository;


    @Override
    public void mapToModels(File xmlFile) throws FileNotFoundException, XMLStreamException {
        List<Example> examples = new ArrayList<>();
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        InputStream inputStream = new FileInputStream(xmlFile);
        XMLEventReader xmlEventReader = inputFactory.createXMLEventReader(inputStream);

        Example example = null;
        while(xmlEventReader.hasNext()){
            XMLEvent event = xmlEventReader.nextEvent();

            if(event.isStartElement()){
                StartElement startElement = event.asStartElement();
                String elementName = startElement.getName().getLocalPart();

                switch (elementName) {
                    case NOTE:
                        example = new Example();
                        break;
                    case ID:
                        event = xmlEventReader.nextEvent();
                        example.setId(Long.valueOf(event.asCharacters().getData()));
                        break;
                    case RECEIVER:
                        event = xmlEventReader.nextEvent();
                        example.setReceiver(event.asCharacters().getData());
                        break;
                    case SENDER:
                        event = xmlEventReader.nextEvent();
                        example.setSender(event.asCharacters().getData());
                        break;
                    case HEADING:
                        event = xmlEventReader.nextEvent();
                        example.setHeading(event.asCharacters().getData());
                        break;
                    case MESSAGE:
                        event = xmlEventReader.nextEvent();
                        example.setMessage(event.asCharacters().getData());
                        break;

                }
            }if(event.isEndElement()){
                EndElement endElement = event.asEndElement();
                if(endElement.getName().getLocalPart().equals(NOTE)){
                    examples.add(example);
                }
            }
        }
        noteRepository.saveAll(examples);
    }
}
