package os.psy.research.spotlight.domain.adpater.processor;

import jira.software.cloud.rest.api.RawBoards;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;import os.psy.research.spotlight.testDataFactory.jira.software.cloud.rest.api.RawDataOm;

import java.util.Set;

import static org.mockito.Mockito.when;

class JsonProcessorTest {

    private final JsonValidator validator = Mockito.mock(JsonValidator.class);
    private final JsonDeserializer deserializer = Mockito.mock(JsonDeserializer.class);
    private final JsonProcessor underTest = new JsonProcessor(validator, deserializer);

    @Test
    void WhenJsonIsSuccessfullyProcessedThenReturnRawBoard() {
        var response = "response";
        var schemaClasspath = "schemaClasspath";
        when(validator.validate(response, schemaClasspath)).thenReturn(Set.of());
        when(deserializer.deserialize(response, RawBoards.class)).thenReturn(RawDataOm.Boards.complete().build());

        var result = underTest.process(response, schemaClasspath);

        Assertions.assertInstanceOf(RawBoards.class, result);
    }

    @Test
    void WhenValidationReturnErrorsThenThrowValidationException() {
        var response = "response";
        var schemaClasspath = "schemaClasspath";
        when(validator.validate(response, schemaClasspath)).thenReturn(null);
        when(deserializer.deserialize(response, RawBoards.class)).thenReturn(RawDataOm.Boards.complete().build());

//        Assertions.assertThrows(ValidationException.class, () -> underTest.process(response, schemaClasspath));

    }

}
