package os.spotlight.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import os.psy.research.spotlight.core.entity.AbstractEntity;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "working_time")
@NoArgsConstructor
@SuperBuilder
public class WorkingTime extends AbstractEntity {
    private OffsetDateTime startedAt;
    private OffsetDateTime completedAt;
    private int plannedMinutes;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "working_time_id")
    private List<Interruption> interruptions;
}
