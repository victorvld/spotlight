package os.spotlight.repository;

import os.spotlight.entity.Account;

public interface AccountRepositoryService {
    Account findByEntityId(String accountId);
}
