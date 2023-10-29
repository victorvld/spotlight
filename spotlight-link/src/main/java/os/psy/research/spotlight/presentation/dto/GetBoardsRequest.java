package os.psy.research.spotlight.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record GetBoardsRequest(
        @NotBlank(message = "Invalid field. Empty accountId.")
        @NotNull(message = "Invalid field. Null accountId.")
        String accountId) {
}
