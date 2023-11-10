package os.spotlight.persistance.respository;

import os.spotlight.persistance.entity.AccountEntity;
import os.spotlight.repository.GenericRepository;

public interface AccountRepository extends GenericRepository<AccountEntity> {
    AccountEntity findByEntityId(String entityId);
}
