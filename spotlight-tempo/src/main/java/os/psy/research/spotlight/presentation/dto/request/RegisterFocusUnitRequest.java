package os.psy.research.spotlight.presentation.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import os.psy.research.spotlight.presentation.dto.BreakTimeDto;
import os.psy.research.spotlight.presentation.dto.UserAssessmentDto;
import os.psy.research.spotlight.presentation.dto.WorkingTimeDto;

@Builder
public record RegisterFocusUnitRequest(
        @NotBlank(message = "Invalid user id. Empty id.")
        @NotNull(message = "Invalid user id. Null id.")
        String userId,
        @NotBlank(message = "Invalid linked task id. Empty id.")
        @NotNull(message = "Invalid linked task id. Null id.")
        String linkedTaskId,
        @NotNull
        @Valid WorkingTimeDto workingTimeDto,

        @NotNull
        @Valid BreakTimeDto breakTimeDto,

        @Nullable UserAssessmentDto userAssessmentDto
) {
}
