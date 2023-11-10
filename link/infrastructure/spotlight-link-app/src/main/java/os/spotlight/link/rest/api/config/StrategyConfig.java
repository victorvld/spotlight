package os.spotlight.link.rest.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import os.spotlight.link.factory.adpater.response.handling.strategy.ResponseHandlingStrategy;
import os.spotlight.link.rest.api.gateway.strategy.ThrowExceptionStrategy;

@Configuration
public class StrategyConfig {
    @Bean
    public ResponseHandlingStrategy createThrowExceptionStrategy() {
        return new ThrowExceptionStrategy();
    }

}
