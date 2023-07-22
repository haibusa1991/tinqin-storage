package com.tinqin.storage.domain.zoostoreClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tinqin.zoostore.restexport.ZooStoreRestExport;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZooStoreRestClientFactory {

    @Bean
    ZooStoreRestExport getMyRestExportClient() {
        final ObjectMapper objectMapper = new ObjectMapper();

        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .target(ZooStoreRestExport.class, "http://localhost:8080");
    }

}
