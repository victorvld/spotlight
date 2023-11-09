package os.psy.research.spotlight.domain.adpater.processor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersionDetector;
import com.networknt.schema.ValidationMessage;

import java.util.Set;

public class JsonValidator {

    private final ObjectMapper mapper;

    public JsonValidator(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Set<ValidationMessage> validate(String response, String schemaClasspath) {
        var schema = getJsonSchemaFromClasspath(schemaClasspath);
        var node = getJsonNodeFromStringContent(response);
        return schema.validate(node);
    }

    private JsonNode getJsonNodeFromStringContent(String content) {
        try {
            return mapper.readTree(content);
        } catch (Exception e) {
            var msg = String.format("Error while deserializing incoming Json:%n%s.", content);
            throw new RuntimeException();
//            throw new DeserializationException(msg, e);
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
            var msg = String.format("Error while deserializing schema:%n%s.", name);
//            throw new DeserializationException(msg, e);
            throw new RuntimeException();
        }
    }
}
