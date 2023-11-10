package os.spotlight.link.rest.api.gateway.processor;

import com.networknt.schema.ValidationMessage;
import lombok.RequiredArgsConstructor;
import os.spotlight.link.factory.adpater.processor.JsonDeserializer;
import os.spotlight.link.factory.adpater.processor.JsonProcessor;
import os.spotlight.link.factory.adpater.processor.JsonValidator;
import os.spotlight.link.rest.api.exception.ValidationException;

import java.util.Set;

@RequiredArgsConstructor
public class JsonProcessorImpl implements JsonProcessor {

    private final JsonValidator validator;

    private final JsonDeserializer deserializer;

    @Override
    public <T> T process(String response, String schemaClasspath, Class<T> type) {
        Set<ValidationMessage> errors = validator.validate(response, schemaClasspath);
        if (errors != null && errors.isEmpty()) {
            return deserializer.deserialize(response, type);
        }
        throw new ValidationException(String.format("Jira Software Cloud Rest API contain validation errors. Errors [%s]", errors));
    }
}
