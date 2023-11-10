package os.spotlight.link.rest.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import os.spotlight.link.adapter.jira.software.cloud.rest.api.JiraSoftwareCloudAdapterImpl;
import os.spotlight.link.adapter.monday.graph.ql.api.MondayGraphQlAdapterImpl;
import os.spotlight.link.rest.api.factory.impl.ProjectManagerVendorFactoryImpl;
import os.spotlight.link.rest.api.gateway.client.OkHttpClient;
import os.spotlight.link.rest.api.gateway.deserializer.JsonDeserializerImpl;
import os.spotlight.link.rest.api.gateway.processor.JsonProcessorImpl;
import os.spotlight.link.rest.api.gateway.processor.JsonValidatorImpl;
import os.spotlight.link.rest.api.gateway.strategy.ThrowExceptionStrategy;
import os.spotlight.link.rest.api.persistance.impl.AccountRepositoryMapper;
import os.spotlight.link.rest.api.persistance.impl.AccountRepositoryServiceImpl;
import os.spotlight.link.rest.api.persistance.respository.AccountRepository;
import os.spotlight.service.LinkService;

@Configuration
public class LinkConfiguration {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ObjectMapper mapper;

    @Bean
    public LinkService createLinkService() {
        ProjectManagerVendorFactoryImpl factory = createFactoryImpl();
        return new LinkService(factory, createAccountRepository());
    }

    @Bean
    public ProjectManagerVendorFactoryImpl createFactoryImpl() {
        return new ProjectManagerVendorFactoryImpl(
                new JiraSoftwareCloudAdapterImpl(
                        createJsonProcessor(createJsonDeserializer()),
                        createHttpClient(),
                        createThrowExceptionStrategy()
                ),
                new MondayGraphQlAdapterImpl(
                        createHttpClient(),
                        createThrowExceptionStrategy(),
                        createJsonDeserializer()
                ));
    }

    @Bean
    public ThrowExceptionStrategy createThrowExceptionStrategy() {
        return new ThrowExceptionStrategy();
    }

    @Bean
    public OkHttpClient createHttpClient() {
        return new OkHttpClient();
    }

    @Bean
    public JsonDeserializerImpl createJsonDeserializer() {
        return new JsonDeserializerImpl(mapper);
    }

    @Bean
    public JsonProcessorImpl createJsonProcessor(JsonDeserializerImpl deserializer) {
        var validator = createJsonValidator();
        return new JsonProcessorImpl(validator, deserializer);
    }

    @Bean
    public JsonValidatorImpl createJsonValidator() {
        return new JsonValidatorImpl(mapper);
    }

    @Bean
    public AccountRepositoryServiceImpl createAccountRepository() {
        return new AccountRepositoryServiceImpl(accountRepository, createAccountRepositoryMapper());
    }

    @Bean
    public AccountRepositoryMapper createAccountRepositoryMapper() {
        return new AccountRepositoryMapper();
    }
}
