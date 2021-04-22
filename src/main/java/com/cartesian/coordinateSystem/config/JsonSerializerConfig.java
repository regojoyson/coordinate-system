package com.cartesian.coordinateSystem.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

/**
 * 
 * Configuration to ensure Record can be processed by Spring and Jackson
 *
 */
@Configuration
public class JsonSerializerConfig {

	/**
	 * Returns the JacksonObjectMapperBuilder after customizing the Serializer
	 * @return
	 */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
        return builder -> builder.visibility(FIELD, ANY);
    }
}
