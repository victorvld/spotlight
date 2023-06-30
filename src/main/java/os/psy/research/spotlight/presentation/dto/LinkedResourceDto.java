package os.psy.research.spotlight.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

/**
 * Data transfer Object DTO that specifies the linked resources of a given FocusUnit such as ProjectId, TaskId.
 * @param projectId must be filled in, if no project is linked then its value is "non defined"
 * @param taskId must be filled in, if no project is linked then its value is "non defined"
 */
@Builder
public record LinkedResourceDto(@NotNull @NotBlank String projectId,
                                @NotNull @NotBlank String taskId) {
}
