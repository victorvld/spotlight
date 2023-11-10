package os.spotlight.link.factory.adpater.processor;

public interface JsonProcessor {
    <T> T process(String response, String schemaClasspath, Class<T> type);
}
