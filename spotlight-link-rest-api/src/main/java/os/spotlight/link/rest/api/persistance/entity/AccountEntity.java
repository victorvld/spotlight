package os.spotlight.link.rest.api.persistance.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import os.psy.research.spotlight.core.entity.AbstractEntity;


@Entity
@Getter
@Setter
@Table(name = "account")
@NoArgsConstructor
@SuperBuilder
public class AccountEntity extends AbstractEntity {
    private String type;
    private String username;
    private String webDomain;
    private String token;
}
