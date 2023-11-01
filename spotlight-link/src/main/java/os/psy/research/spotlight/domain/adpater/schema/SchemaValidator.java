package os.psy.research.spotlight.domain.adpater.schema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.RuntimeJsonMappingException;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersionDetector;
import jira.software.cloud.rest.api.RawBoards;
import os.psy.research.spotlight.infrastructure.errorHandling.exceptions.DeserializationException;
import os.psy.research.spotlight.infrastructure.errorHandling.exceptions.ValidationException;

import java.io.IOException;

public class SchemaValidator {
    private final ObjectMapper mapper = new ObjectMapper();

    public RawBoards validate(String response, String schemaClasspath) {
        var schema = getJsonSchemaFromClasspath(schemaClasspath);
        var node = getJsonNodeFromStringContent(response);
        var errors = schema.validate(node);
        if (errors != null && errors.isEmpty()) {
            return handleDeserialization(response);
        } else {
            throw new ValidationException("Jira Software Cloud Rest API contain errors");
        }
    }

    private JsonNode getJsonNodeFromStringContent(String content) {
        try {
            return mapper.readTree(content);
        } catch (JsonProcessingException e) {
            var msg = String.format("Error while deserializing incoming Json:%n%s.", content);
            throw new DeserializationException(msg, e);
        }
    }

    private JsonSchema getJsonSchemaFromClasspath(String name) {
        var jsonNode = getJsonNodeFromClassPath(name);
        return getJsonSchemaFromJsonNode(jsonNode);
    }

    private JsonSchema getJsonSchemaFromJsonNode(JsonNode jsonNode) {
        return JsonSchemaFactory.getInstance(SpecVersionDetector.detect(jsonNode)).getSchema(jsonNode);
    }

    private JsonNode getJsonNodeFromClassPath(String name) {
        try {
            return mapper.readTree(Thread.currentThread().getContextClassLoader().getResourceAsStream(name));
        } catch (IOException e) {
            throw new RuntimeJsonMappingException(e.getMessage());
        }
    }

    private RawBoards handleDeserialization(String response) {
        try {
            return mapper.readValue(response, RawBoards.class);
        } catch (JsonProcessingException e) {
            throw new DeserializationException("Error while deserializing Json response from Get All Boards request", e);
        }
    }
}
