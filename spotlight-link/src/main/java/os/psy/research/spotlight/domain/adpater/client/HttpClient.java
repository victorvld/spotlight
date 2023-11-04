package os.psy.research.spotlight.domain.adpater.client;

import java.io.IOException;
import java.util.Map;

public interface HttpClient {

    Map.Entry<Integer, String> sendGetRequest(String username, String token, String url) throws IOException;

    Map.Entry<Integer, String> sendGetRequest(String username, String token, String url, String query);

}
