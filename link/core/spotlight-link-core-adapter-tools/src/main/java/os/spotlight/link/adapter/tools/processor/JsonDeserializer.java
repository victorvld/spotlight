package os.spotlight.link.adapter.tools.processor;

public interface JsonDeserializer {
    <T> T deserialize(String response, Class<T> type);
}
