package os.psy.research.spotlight.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record FocusUnitDto(
        @NotBlank(message = "Invalid user id. Empty id.")
        @NotNull(message = "Invalid user id. Null id.")
        String userId,
        @NotNull
        LinkedResourceDto linkedResourceDto,
        @NotNull
        WorkingTimeDto workingTimeDto,
        String id) {

}
