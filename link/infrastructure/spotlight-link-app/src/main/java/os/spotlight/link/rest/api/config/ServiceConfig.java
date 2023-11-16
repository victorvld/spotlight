package os.spotlight.link.rest.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import os.spotlight.link.rest.api.factory.impl.ProjectManagerVendorFactoryImpl;
import os.spotlight.persistance.entity.LinkService;

@Configuration
@RequiredArgsConstructor
public class ServiceConfig {

    private final FactoryConfig factoryConfig;

    private final RepositoryConfig repositoryConfig;
    @Bean
    public LinkService createLinkService() {
        ProjectManagerVendorFactoryImpl factory = factoryConfig.createFactoryImpl();
        return new LinkService(factory, repositoryConfig.createAccountRepository());
    }
}
