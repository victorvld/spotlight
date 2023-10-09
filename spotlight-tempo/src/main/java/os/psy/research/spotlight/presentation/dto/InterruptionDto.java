package os.psy.research.spotlight.presentation.dto;

import lombok.Builder;

import java.time.Duration;
import java.time.OffsetDateTime;

@Builder
public record InterruptionDto(String type, String reasonType, OffsetDateTime recordedAt, Duration duration) {
}
