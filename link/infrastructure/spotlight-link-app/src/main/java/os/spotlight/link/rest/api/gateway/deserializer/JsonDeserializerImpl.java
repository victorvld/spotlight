package os.spotlight.link.rest.api.gateway.deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import os.spotlight.link.factory.adpater.processor.JsonDeserializer;
import os.spotlight.link.rest.api.exception.DeserializationException;

public class JsonDeserializerImpl implements JsonDeserializer {

    private final ObjectMapper mapper;

    public JsonDeserializerImpl(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public <T> T deserialize(String response, Class<T> type) {
        try {
            return mapper.readValue(response, type);
        } catch (JsonProcessingException e) {
            throw new DeserializationException("Error while deserializing Json response.", e);
        }
    }
}
