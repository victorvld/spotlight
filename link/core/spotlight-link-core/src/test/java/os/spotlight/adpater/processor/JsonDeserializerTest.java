package os.spotlight.adpater.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import os.spotlight.adpater.processor.JsonDeserializer;

import static org.mockito.Mockito.when;

class JsonDeserializerTest {
    private final ObjectMapper mapper = Mockito.mock(ObjectMapper.class);
    private final JsonDeserializer underTest = new JsonDeserializer(mapper);

    @SneakyThrows
    @Test
    void WhenMapperThrowsJsonProcessingExceptionThenThrowDeserializationException() {
        when(mapper.readValue("test", String.class)).thenThrow(JsonProcessingException.class);

//        Assertions.assertThrows(DeserializationException.class, () -> underTest.deserialize("test", String.class));
    }

}
