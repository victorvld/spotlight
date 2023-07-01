package os.psy.research.spotlight.presentation.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@Builder
public record BreakTimeDto(@NotNull OffsetDateTime startedAt,
                           @NotNull OffsetDateTime completedAt,
                           @NotNull Duration selectedDuration,
                           List<InterruptionDto> interruptionsDto) {
}
