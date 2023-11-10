package os.spotlight.link.rest.api.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import os.spotlight.persistance.impl.AccountRepositoryServiceImpl;
import os.spotlight.persistance.mapper.AccountRepositoryMapper;
import os.spotlight.persistance.respository.AccountRepository;

@EnableJpaRepositories(basePackages = "os.spotlight.persistance.respository")
@EntityScan(basePackages = "os.spotlight.persistance.entity")
@Configuration
@RequiredArgsConstructor
public class RepositoryConfig {

    private final AccountRepository accountRepository;

    @Bean
    public AccountRepositoryServiceImpl createAccountRepository() {
        return new AccountRepositoryServiceImpl(accountRepository, createAccountRepositoryMapper());
    }

    @Bean
    public AccountRepositoryMapper createAccountRepositoryMapper() {
        return new AccountRepositoryMapper();
    }
}
