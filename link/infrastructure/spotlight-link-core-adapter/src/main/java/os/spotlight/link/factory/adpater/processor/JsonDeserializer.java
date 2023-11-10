package os.spotlight.link.factory.adpater.processor;

public interface JsonDeserializer {
    <T> T deserialize(String response, Class<T> type);
}
