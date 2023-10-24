package os.psy.research.spotlight.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
