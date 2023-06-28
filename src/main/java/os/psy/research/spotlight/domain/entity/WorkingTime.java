package os.psy.research.spotlight.domain.entity;

import lombok.Builder;

import java.time.Duration;
import java.time.OffsetDateTime;

@Builder

public record WorkingTime(OffsetDateTime startedAt, OffsetDateTime completedAt, Duration selectedDuration) {
}
