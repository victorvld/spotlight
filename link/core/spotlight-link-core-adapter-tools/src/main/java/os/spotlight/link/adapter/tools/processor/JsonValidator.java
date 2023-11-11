package os.spotlight.link.adapter.tools.processor;

import java.util.Set;

public interface JsonValidator {
    Set<String> validate(String response, String schemaClasspath);
}
