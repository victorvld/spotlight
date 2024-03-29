package os.spotlight.adapter.tools.impl.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersionDetector;
import com.networknt.schema.ValidationMessage;
import os.spotlight.link.adapter.tools.processor.JsonValidator;
import os.spotlight.adapter.tools.impl.exception.DeserializationException;

import java.util.Set;
import java.util.stream.Collectors;

public class JsonValidatorImpl implements JsonValidator {

    private final ObjectMapper mapper;

    public JsonValidatorImpl(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Set<String> validate(String response, String schemaClasspath) {
        var schema = getJsonSchemaFromClasspath(schemaClasspath);
        var node = getJsonNodeFromStringContent(response);
        return schema.validate(node).stream().map(ValidationMessage::getMessage).collect(Collectors.toSet());
    }

    private JsonNode getJsonNodeFromStringContent(String content) {
        try {
            return mapper.readTree(content);
        } catch (Exception e) {
            throw new DeserializationException(String.format("Error while deserializing incoming Json:%n%s.", content), e);
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
        } catch (Exception e) {
            throw new DeserializationException(String.format("Error while deserializing schema:%n%s.", name), e);
        }
    }
}
