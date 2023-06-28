package os.psy.research.spotlight.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LinkedResourceDto(@NotBlank String projectId,
                                @NotBlank String taskId) {
}
