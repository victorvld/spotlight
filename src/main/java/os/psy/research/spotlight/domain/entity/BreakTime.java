package os.psy.research.spotlight.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Duration;
import java.time.OffsetDateTime;

@Getter
@Builder
@AllArgsConstructor
public class BreakTime {
    private final OffsetDateTime startedAt;
    private final OffsetDateTime completedAt;
    private final Duration selectedDuration;
}
