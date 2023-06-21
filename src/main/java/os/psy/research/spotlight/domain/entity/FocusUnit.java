package os.psy.research.spotlight.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "focus_unit")
@NoArgsConstructor
public class FocusUnit extends AbstractEntity {

    private String userId;

    // linkedResources

    // workingTime

    // breakTime

    // Assessment



    @Builder
    public FocusUnit(String userId, String id) {
        super(id);
        this.userId = userId;
    }

}
