package os.psy.research.spotlight.domain.adpater.client.impl;

import okhttp3.Credentials;
import okhttp3.Request;
import os.psy.research.spotlight.domain.adpater.client.HttpClient;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class OkHttpClient implements HttpClient {

    private final okhttp3.OkHttpClient client = new okhttp3.OkHttpClient.Builder()
            .readTimeout(1000, TimeUnit.MILLISECONDS)
            .writeTimeout(1000, TimeUnit.MILLISECONDS)
            .build();

    @Override
    public Map.Entry<Integer, String> sendGetRequest(String username, String token, String url) throws IOException {
        var credential = Credentials.basic(username, token);
        var request = new Request.Builder().url(url).get().addHeader("Authorization", credential).build();
        var response = client.newCall(request).execute();
        return Map.entry(response.code(), Objects.requireNonNull(response.body()).string());
    }

    @Override
    public Map.Entry<Integer, String> sendGetRequest(String username, String token, String url, String query) {
        return null;
    }
}
