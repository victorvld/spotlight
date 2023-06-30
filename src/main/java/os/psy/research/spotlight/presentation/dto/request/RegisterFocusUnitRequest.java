package os.psy.research.spotlight.presentation.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import os.psy.research.spotlight.presentation.dto.BreakTimeDto;
import os.psy.research.spotlight.presentation.dto.LinkedResourceDto;
import os.psy.research.spotlight.presentation.dto.WorkingTimeDto;

@Builder
public record RegisterFocusUnitRequest(
        @NotBlank(message = "Invalid user id. Empty id.")
        @NotNull(message = "Invalid user id. Null id.")
        String userId,
        @NotNull
        @Valid LinkedResourceDto linkedResourceDto,
        @NotNull
        @Valid WorkingTimeDto workingTimeDto,

        @NotNull
        @Valid BreakTimeDto breakTimeDto
) {
}
