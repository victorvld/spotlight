package os.spotlight.link.rest.api.gateway.processor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import os.spotlight.link.rest.api.exception.ValidationException;
import os.spotlight.link.rest.api.gateway.deserializer.JsonDeserializerImpl;

import java.util.Set;

import static org.mockito.Mockito.when;

class JsonProcessorImplTest {

    private final JsonValidatorImpl validator = Mockito.mock(JsonValidatorImpl.class);
    private final JsonDeserializerImpl deserializer = Mockito.mock(JsonDeserializerImpl.class);
    private final JsonProcessorImpl underTest = new JsonProcessorImpl(validator, deserializer);

    @Test
    void WhenJsonIsSuccessfullyProcessedThenReturnRawBoard() {
        var response = "response";
        var schemaClasspath = "schemaClasspath";
        when(validator.validate(response, schemaClasspath)).thenReturn(Set.of());
        when(deserializer.deserialize(response, String.class)).thenReturn("value");

        var result = underTest.process(response, schemaClasspath, String.class);

        Assertions.assertInstanceOf(String.class, result);
    }

    @Test
    void WhenValidationReturnErrorsThenThrowValidationException() {
        var response = "response";
        var schemaClasspath = "schemaClasspath";
        when(validator.validate(response, schemaClasspath)).thenReturn(null);
        when(deserializer.deserialize(response, String.class)).thenReturn("value");

        Assertions.assertThrows(ValidationException.class, () -> underTest.process(response, schemaClasspath, String.class));

    }

}
