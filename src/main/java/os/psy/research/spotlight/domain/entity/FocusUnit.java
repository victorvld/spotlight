package os.psy.research.spotlight.domain.entity;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FocusUnit extends AbstractEntity {

    private String userUuid;
    @Builder
    public FocusUnit(String userUuid) {
        this.userUuid = userUuid;
    }

}
