package os.psy.research.spotlight.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@Table(name = "focus_unit")
@NoArgsConstructor
@SuperBuilder
public class FocusUnit extends AbstractEntity {

    private String userId;

    // linkedResources
    private LinkedResource linkedResource;

    // workingTime

    // breakTime

    // Assessment

}
