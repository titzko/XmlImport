package com.titzko.testingThings.stax.application.mapper;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.FileNotFoundException;

public interface MappingCapable {

    void mapToModels(File xmlFile) throws FileNotFoundException, XMLStreamException;
}
