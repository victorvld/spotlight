package os.spotlight.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record GetFocusUnitsRequest(
        @NotBlank(message = "Invalid user id. Empty id.")
        @NotNull(message = "Invalid user id. Null id.")
        String userId
) {
}
