package os.spotlight.link.rest.api.persistance.impl;

import lombok.AllArgsConstructor;
import os.spotlight.entity.Account;
import os.spotlight.link.rest.api.persistance.respository.AccountRepository;
import os.spotlight.repository.AccountRepositoryService;

@AllArgsConstructor
public class AccountRepositoryServiceImpl implements AccountRepositoryService {

    private final AccountRepository repository;

    private final AccountRepositoryMapper mapper;
    @Override
    public Account findByEntityId(String accountId) {
        return mapper.toDomain(repository.findByEntityId(accountId));
    }
}
