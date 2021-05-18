package com.titzko.testingThings.stax.application.mapper;

import com.titzko.testingThings.stax.application.model.MessageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.Optional;

@Service
public class MapperRegistry {

    private EnumMap<MessageType, MappingCapable> mappers = new EnumMap<MessageType, MappingCapable>(MessageType.class);

    @Autowired
    public MapperRegistry(ItemMapper itemMapper, AddressMapper addressMapper, ExampleMapper exampleMapper){
        mappers.put(MessageType.ITEM, itemMapper);
        mappers.put(MessageType.ADDRESS, addressMapper);
        mappers.put(MessageType.EXAMPLE, exampleMapper);
    }

    public Optional<MappingCapable> getMessageMapper(MessageType type) {
        return Optional.ofNullable(mappers.get(type));
    }
}
