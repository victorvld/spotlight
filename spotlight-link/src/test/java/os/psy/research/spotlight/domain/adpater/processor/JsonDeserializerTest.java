package os.psy.research.spotlight.domain.adpater.processor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import os.psy.research.spotlight.infrastructure.errorhandling.exceptions.DeserializationException;

import static org.mockito.Mockito.when;

class JsonDeserializerTest {
    private final ObjectMapper mapper = Mockito.mock(ObjectMapper.class);
    private final JsonDeserializer underTest = new JsonDeserializer(mapper);

    @SneakyThrows
    @Test
    void WhenMapperThrowsJsonProcessingExceptionThenThrowDeserializationException() {
        when(mapper.readValue("test", String.class)).thenThrow(JsonProcessingException.class);

        Assertions.assertThrows(DeserializationException.class, () -> underTest.deserialize("test", String.class));
    }

}
