package os.spotlight.link.rest.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import os.psy.research.spotlight.domain.service.LinkService;
import os.psy.research.spotlight.factory.PmFactoryImpl;
import os.spotlight.link.rest.api.persistance.impl.AccountRepositoryServiceImpl;
import os.spotlight.link.rest.api.persistance.impl.AccountRepositoryMapper;
import os.spotlight.link.rest.api.persistance.respository.AccountRepository;

@Configuration
public class LinkConfiguration {

    @Autowired
    AccountRepository accountRepository;

    @Bean
    public LinkService createLinkService() {
        return new LinkService(new PmFactoryImpl(), createAccountRepository());
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
