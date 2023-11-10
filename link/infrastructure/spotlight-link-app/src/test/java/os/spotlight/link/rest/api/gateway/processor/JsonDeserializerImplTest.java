package os.spotlight.link.rest.api.gateway.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import os.spotlight.link.rest.api.gateway.exception.DeserializationException;
import os.spotlight.link.rest.api.gateway.deserializer.JsonDeserializerImpl;

import static org.mockito.Mockito.when;

class JsonDeserializerImplTest {
    private final ObjectMapper mapper = Mockito.mock(ObjectMapper.class);
    private final JsonDeserializerImpl underTest = new JsonDeserializerImpl(mapper);

    @SneakyThrows
    @Test
    void WhenMapperThrowsJsonProcessingExceptionThenThrowDeserializationException() {
        when(mapper.readValue("test", String.class)).thenThrow(JsonProcessingException.class);

        Assertions.assertThrows(DeserializationException.class, () -> underTest.deserialize("test", String.class));
    }

}
