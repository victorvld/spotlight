package os.spotlight.adapter.tools.impl.processor;

import lombok.RequiredArgsConstructor;
import os.spotlight.adapter.tools.impl.exception.ValidationException;
import os.spotlight.link.adapter.tools.processor.JsonDeserializer;
import os.spotlight.link.adapter.tools.processor.JsonProcessor;
import os.spotlight.link.adapter.tools.processor.JsonValidator;

import java.util.Set;

@RequiredArgsConstructor
public class JsonProcessorImpl implements JsonProcessor {

    private final JsonValidator validator;

    private final JsonDeserializer deserializer;

    @Override
    public <T> T process(String response, String schemaClasspath, Class<T> type) {
        Set<String> errors = validator.validate(response, schemaClasspath);
        if (errors != null && errors.isEmpty()) {
            return deserializer.deserialize(response, type);
        }
        throw new ValidationException(String.format("Jira Software Cloud Rest API contain validation errors. Errors [%s]", errors));
    }
}
