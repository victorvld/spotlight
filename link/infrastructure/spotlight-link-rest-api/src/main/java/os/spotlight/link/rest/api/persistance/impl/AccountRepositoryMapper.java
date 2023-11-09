package os.spotlight.link.rest.api.persistance.impl;

import os.psy.research.spotlight.domain.entity.Account;
import os.spotlight.link.rest.api.persistance.entity.AccountEntity;

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
