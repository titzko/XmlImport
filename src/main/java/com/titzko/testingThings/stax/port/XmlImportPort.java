package com.titzko.testingThings.stax.port;


import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;

public interface XmlImportPort {
    void importXmlFile(String Filename) throws FileNotFoundException, XMLStreamException;
}
