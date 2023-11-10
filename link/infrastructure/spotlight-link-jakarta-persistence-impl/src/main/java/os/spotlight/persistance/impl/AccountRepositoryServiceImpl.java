package os.spotlight.persistance.impl;

import lombok.RequiredArgsConstructor;
import os.spotlight.persistance.entity.Account;
import os.spotlight.persistance.mapper.AccountRepositoryMapper;
import os.spotlight.persistance.respository.AccountRepository;
import os.spotlight.repository.AccountRepositoryService;

@RequiredArgsConstructor
public class AccountRepositoryServiceImpl implements AccountRepositoryService {

    private final AccountRepository repository;

    private final AccountRepositoryMapper mapper;
    @Override
    public Account findByEntityId(String accountId) {
        return mapper.toDomain(repository.findByEntityId(accountId));
    }
}
