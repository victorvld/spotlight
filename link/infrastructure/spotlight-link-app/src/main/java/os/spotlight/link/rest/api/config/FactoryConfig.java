package os.spotlight.link.rest.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import os.spotlight.link.adapter.jira.software.cloud.rest.api.JiraSoftwareCloudAdapterImpl;
import os.spotlight.link.adapter.monday.graph.ql.api.MondayGraphQlAdapterImpl;
import os.spotlight.link.rest.api.factory.impl.ProjectManagerVendorFactoryImpl;

@Configuration
@RequiredArgsConstructor
public class FactoryConfig {

    private final HttpClientConfig httpClientConfig;
    private final JsonConfig jsonConfig;
    private final StrategyConfig strategyConfig;

    @Bean
    public ProjectManagerVendorFactoryImpl createFactoryImpl() {
        var jiraGateway = new JiraSoftwareCloudAdapterImpl(
                jsonConfig.createJsonProcessor(jsonConfig.createJsonDeserializer()),
                jsonConfig.createJsonDeserializer(),
                httpClientConfig.createHttpClient(),
                strategyConfig.createThrowExceptionStrategy()
        );
        var mondayGateway = new MondayGraphQlAdapterImpl(
                httpClientConfig.createHttpClient(),
                strategyConfig.createThrowExceptionStrategy(),
                jsonConfig.createJsonDeserializer()
        );
        return new ProjectManagerVendorFactoryImpl(jiraGateway, mondayGateway);
    }
}
