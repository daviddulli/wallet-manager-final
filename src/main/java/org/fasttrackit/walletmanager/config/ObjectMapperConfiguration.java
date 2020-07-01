package org.fasttrackit.walletmanager.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperConfiguration {

    public static final ObjectMapper objectMapper = new ObjectMapper();


    //static block
    static {
        objectMapper.registerModule(new JavaTimeModule());
    }

}
