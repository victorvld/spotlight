package os.psy.research.spotlight.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FocusUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    //@Setter(AccessLevel.NONE)
    private String id;

    private String userUuid;
    @Builder
    public FocusUnit(String userUuid, String id) {
        this.userUuid = userUuid;
        this.id = id;
    }
}
