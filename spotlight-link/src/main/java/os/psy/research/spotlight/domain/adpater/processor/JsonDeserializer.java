package os.psy.research.spotlight.domain.adpater.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDeserializer {

    private final ObjectMapper mapper;

    public JsonDeserializer(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public <T> T deserialize(String response, Class<T> type) {
        try {
            return mapper.readValue(response, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();

//            throw new DeserializationException("Error while deserializing Json response.", e);
        }
    }
}
