package os.psy.research.spotlight.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "break_time")
@NoArgsConstructor
@SuperBuilder
public class BreakTime extends AbstractEntity {
    private OffsetDateTime startedAt;
    private OffsetDateTime completedAt;
    private int plannedMinutes;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "break_time_id")
    private List<Interruption> interruptions;
}
