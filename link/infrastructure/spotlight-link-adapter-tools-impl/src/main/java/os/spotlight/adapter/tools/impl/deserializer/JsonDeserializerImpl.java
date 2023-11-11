package os.spotlight.adapter.tools.impl.deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import os.spotlight.adapter.tools.impl.exception.DeserializationException;
import os.spotlight.link.adapter.tools.processor.JsonDeserializer;

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
