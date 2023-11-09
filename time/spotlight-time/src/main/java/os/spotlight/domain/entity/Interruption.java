package os.spotlight.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import os.psy.research.spotlight.core.entity.AbstractEntity;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(name = "interruption")
@NoArgsConstructor
@SuperBuilder
public class Interruption extends AbstractEntity {
    private String type;
    private String reason;
    private OffsetDateTime recordedAt;
}
