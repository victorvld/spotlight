package os.psy.research.spotlight.domain.adpater.client;

import java.util.Map;

public interface HttpClient {

    Map.Entry<Integer, String> sendGetRequest(String username, String token, String url);

    Map.Entry<Integer, String> sendGetRequest(String username, String token, String url, String query);

}
