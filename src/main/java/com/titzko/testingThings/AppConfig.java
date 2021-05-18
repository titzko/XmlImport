package com.titzko.testingThings;

import com.titzko.testingThings.stax.adapter.repositories.ItemRepository;
import com.titzko.testingThings.stax.port.XmlImportPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.stream.XMLStreamException;
import java.io.FileNotFoundException;
import java.nio.file.*;


@Configuration
public class AppConfig {
    private static final String DIRECTORY="C:/Users/matthias.willers/Desktop/projectXmlImportDirectory";


    @Autowired
    XmlImportPort xmlImportPort;
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    public AppConfig(XmlImportPort xmlImportPort) {
        this.xmlImportPort = xmlImportPort;
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {

            Files.walk(Paths.get(DIRECTORY)).filter(Files::isRegularFile).forEach(file -> {
                try {
                    xmlImportPort.importXmlFile(file.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            });


            WatchService watchService  = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(DIRECTORY);
            path.register(
                    watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY);

            WatchKey key;
            while ((key = watchService.take()) != null) {
                for (WatchEvent<?> event : key.pollEvents()) {

                    if(event.kind().equals(StandardWatchEventKinds.ENTRY_CREATE)) {
                         xmlImportPort.importXmlFile(DIRECTORY + "/"+event.context());
                    }
                }
                key.reset();

            }

        };
    }
}
