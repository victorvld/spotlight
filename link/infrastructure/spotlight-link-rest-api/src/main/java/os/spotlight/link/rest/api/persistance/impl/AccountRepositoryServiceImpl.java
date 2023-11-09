package os.spotlight.link.rest.api.persistance.impl;

import lombok.AllArgsConstructor;
import os.psy.research.spotlight.domain.entity.Account;
import os.psy.research.spotlight.domain.repository.AccountRepositoryService;
import os.spotlight.link.rest.api.persistance.respository.AccountRepository;

@AllArgsConstructor
public class AccountRepositoryServiceImpl implements AccountRepositoryService {

    private final AccountRepository repository;

    private final AccountRepositoryMapper mapper;
    @Override
    public Account findByEntityId(String accountId) {
        return mapper.toDomain(repository.findByEntityId(accountId));
    }
}
