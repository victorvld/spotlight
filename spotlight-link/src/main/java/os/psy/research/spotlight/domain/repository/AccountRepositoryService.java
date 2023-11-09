package os.psy.research.spotlight.domain.repository;

import os.psy.research.spotlight.domain.entity.Account;

public interface AccountRepositoryService {
    Account findByEntityId(String accountId);
}
