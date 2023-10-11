package os.psy.research.spotlight.presentation.dto;

import lombok.Builder;

import java.time.OffsetDateTime;

@Builder
public record InterruptionDto(String type, String reason, OffsetDateTime recordedAt) {
}
