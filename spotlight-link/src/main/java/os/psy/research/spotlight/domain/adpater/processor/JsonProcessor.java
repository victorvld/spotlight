package os.psy.research.spotlight.domain.adpater.processor;

import com.networknt.schema.ValidationMessage;
import jira.software.cloud.rest.api.RawBoards;

import java.util.Set;

public class JsonProcessor {
    private final JsonValidator validator;
    private final JsonDeserializer deserializer;

    public JsonProcessor(JsonValidator validator, JsonDeserializer deserializer) {
        this.deserializer = deserializer;
        this.validator = validator;
    }

    public RawBoards process(String response, String schemaClasspath) {
        Set<ValidationMessage> errors = validator.validate(response, schemaClasspath);
        if (errors != null && errors.isEmpty()) {
            return deserializer.deserialize(response, RawBoards.class);
        }
        throw new RuntimeException();
        //throw new ValidationException(String.format("Jira Software Cloud Rest API contain validation errors. Errors [%s]", errors));
    }
}
