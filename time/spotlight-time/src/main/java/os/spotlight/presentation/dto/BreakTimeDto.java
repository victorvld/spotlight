package os.spotlight.presentation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
public record BreakTimeDto(@NotNull OffsetDateTime startedAt,
                           @NotNull OffsetDateTime completedAt,
                           @NotNull(message = "Planned minutes could not be null")
                           @Positive(message = "Planned minutes should be greater than zero")
                           int plannedMinutes,
                           List<InterruptionDto> interruptionsDto) {
}
