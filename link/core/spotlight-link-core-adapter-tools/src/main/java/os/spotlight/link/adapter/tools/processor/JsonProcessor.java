package os.spotlight.link.adapter.tools.processor;

public interface JsonProcessor {
    <T> T process(String response, String schemaClasspath, Class<T> type);
}
