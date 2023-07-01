package os.psy.research.spotlight.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class WorkingTime {
    private final OffsetDateTime startedAt;
    private final OffsetDateTime completedAt;
    private final Duration selectedDuration;
    private final List<Interruption> interruptions;
}
