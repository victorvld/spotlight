package os.spotlight.link.factory.adpater.processor;

import com.networknt.schema.ValidationMessage;

import java.util.Set;

public interface JsonValidator {
    Set<ValidationMessage> validate(String response, String schemaClasspath);
}
