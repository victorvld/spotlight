package os.spotlight.link.rest.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import os.spotlight.link.rest.api.gateway.client.OkHttpClient;

@Configuration
public class HttpClientConfig {
    @Bean
    public OkHttpClient createHttpClient() {
        return new OkHttpClient();
    }

}
