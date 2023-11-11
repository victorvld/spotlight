package os.spotlight.link.rest.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import os.spotlight.link.adapter.tools.client.HttpClient;
import os.spotlight.adapter.tools.impl.client.OkHttpClient;
@Configuration
public class HttpClientConfig {
    @Bean
    public HttpClient createHttpClient() {
        return new OkHttpClient();
    }

}
