package com.gmail.bogatyr.alexander.dzen.lab.authentication.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

/**
 * Created by alexander on 16.05.16.
 */
@Component
public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper() {
        registerModule(new JavaTimeModule());
    }
}
