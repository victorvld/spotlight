package os.psy.research.spotlight.presentation.dto;

import lombok.Builder;

@Builder
public record UserAssessmentDto(String mood, String feedback) {
}
