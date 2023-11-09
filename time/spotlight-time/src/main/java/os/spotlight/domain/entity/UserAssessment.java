package os.spotlight.domain.entity;

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
@Table(name = "user_assessment")
@NoArgsConstructor
@SuperBuilder
public class UserAssessment extends AbstractEntity {
    String mood;
    String feedback;
}
