package os.psy.research.spotlight.domain.repository;

import os.psy.research.spotlight.core.repository.GenericRepository;
import os.psy.research.spotlight.domain.entity.Account;

import java.util.List;

public interface AccountRepository extends GenericRepository<Account> {
    Account findByEntityId(String entityId);
}
