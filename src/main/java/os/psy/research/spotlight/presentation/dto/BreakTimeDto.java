package os.psy.research.spotlight.presentation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.Duration;
import java.time.OffsetDateTime;

@Builder
public record BreakTimeDto(@NotNull OffsetDateTime startedAt,
                           @NotNull OffsetDateTime completedAt,
                           @NotNull Duration selectedDuration) {
}
