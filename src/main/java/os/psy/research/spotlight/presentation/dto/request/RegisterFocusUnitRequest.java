package os.psy.research.spotlight.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import os.psy.research.spotlight.presentation.dto.LinkedResourceDto;
import os.psy.research.spotlight.presentation.dto.WorkingTimeDto;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterFocusUnitRequest {

    @NotBlank(message = "Invalid user id. Empty id.")
    @NotNull(message = "Invalid user id. Null id.")
    private String userId;

    @NotNull
    private LinkedResourceDto linkedResourceDto;

    @NotNull
    private WorkingTimeDto workingTimeDto;
}
