package com.skarpness.blog.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    // Mapper implementations (i.e. PostMapperImpl) requires a bean of type
    // org.modelmapper.ModelMapper, so defining a bean of this type here
    // allows Spring to recognize it.

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
