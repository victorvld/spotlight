package os.spotlight.persistance.mapper;

import os.spotlight.persistance.entity.Account;
import os.spotlight.persistance.entity.AccountEntity;

public class AccountRepositoryMapper {
    public Account toDomain(AccountEntity entity) {
        return Account.builder()
                .username(entity.getUsername())
                .token(entity.getToken())
                .domain(entity.getWebDomain())
                .type(entity.getType())
                .build();
    }
}
