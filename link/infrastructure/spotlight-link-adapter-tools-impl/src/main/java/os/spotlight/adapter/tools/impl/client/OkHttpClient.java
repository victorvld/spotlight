package os.spotlight.adapter.tools.impl.client;

import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.Request;
import os.spotlight.adapter.tools.impl.exception.RestCallException;
import os.spotlight.link.adapter.tools.client.HttpClient;

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
    public Map.Entry<Integer, String> sendGetRequest(String username, String token, String url) {
        var credential = Credentials.basic(username, token);
        var request = new Request.Builder().url(url).get().addHeader("Authorization", credential).build();
        try {
            var response = client.newCall(request).execute();
            return Map.entry(response.code(), Objects.requireNonNull(response.body()).string());
        } catch (IOException e) {
            throw new RestCallException(String.format("Failed to get response from url: %s", url), e);
        }
    }

    @Override
    public Map.Entry<Integer, String> sendGetRequest(String username, String token, String url, String query) {
        try {
            var response = client.newCall(buildRequest(token, buildGraphQlUrl(url, query))).execute();
            return Map.entry(response.code(), Objects.requireNonNull(response.body()).string());
        } catch (IOException e) {
            throw new RestCallException(String.format("Failed to get response from url: %s", url), e);
        }
    }
    private HttpUrl buildGraphQlUrl(String url, String query) {
        return Objects.requireNonNull(HttpUrl.parse(url))
                .newBuilder()
                .addQueryParameter("query", query)
                .build();
    }

    private Request buildRequest(String token, HttpUrl url) {
        return new Request.Builder()
                .url(url)
                .get()
                .addHeader("Authorization", token)
                .build();
    }
}
