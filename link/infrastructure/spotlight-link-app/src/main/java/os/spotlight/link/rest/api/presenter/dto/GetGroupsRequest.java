package os.spotlight.link.rest.api.presenter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record GetGroupsRequest(
        @NotBlank(message = "Invalid field. Empty accountId.")
        @NotNull(message = "Invalid field. Null accountId.")
        String accountId,

        @NotBlank(message = "Invalid field. Empty boardId.")
        @NotNull(message = "Invalid field. Null boardId.")
        String boardId) {
}
