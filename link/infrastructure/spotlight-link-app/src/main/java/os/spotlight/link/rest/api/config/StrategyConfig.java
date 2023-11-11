package os.spotlight.link.rest.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import os.spotlight.adapter.tools.impl.strategy.ThrowExceptionStrategy;
import os.spotlight.link.adapter.tools.response.handling.strategy.ResponseHandlingStrategy;

@Configuration
public class StrategyConfig {
    @Bean
    public ResponseHandlingStrategy createThrowExceptionStrategy() {
        return new ThrowExceptionStrategy();
    }

}
