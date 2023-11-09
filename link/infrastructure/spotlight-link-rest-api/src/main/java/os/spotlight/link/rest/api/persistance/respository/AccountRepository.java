package os.spotlight.link.rest.api.persistance.respository;

import os.psy.research.spotlight.core.repository.GenericRepository;
import os.spotlight.link.rest.api.persistance.entity.AccountEntity;

public interface AccountRepository extends GenericRepository<AccountEntity> {
    AccountEntity findByEntityId(String entityId);
}
