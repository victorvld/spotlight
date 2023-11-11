package os.spotlight.link.rest.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import os.spotlight.adapter.tools.impl.deserializer.JsonDeserializerImpl;
import os.spotlight.adapter.tools.impl.processor.JsonProcessorImpl;
import os.spotlight.adapter.tools.impl.processor.JsonValidatorImpl;

@Configuration
@RequiredArgsConstructor
public class JsonConfig {

    private final ObjectMapper mapper;

    @Bean
    public JsonDeserializerImpl createJsonDeserializer() {
        return new JsonDeserializerImpl(mapper);
    }

    @Bean
    public JsonProcessorImpl createJsonProcessor(JsonDeserializerImpl deserializer) {
        var validator = createJsonValidator();
        return new JsonProcessorImpl(validator, deserializer);
    }

    @Bean
    public JsonValidatorImpl createJsonValidator() {
        return new JsonValidatorImpl(mapper);
    }
}
