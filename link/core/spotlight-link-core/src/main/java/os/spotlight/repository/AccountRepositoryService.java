package os.spotlight.repository;

import os.spotlight.persistance.entity.Account;

public interface AccountRepositoryService {
    Account findByEntityId(String accountId);
}
