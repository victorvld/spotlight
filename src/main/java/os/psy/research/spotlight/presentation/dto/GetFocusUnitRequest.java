package os.psy.research.spotlight.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class GetFocusUnitRequest {

    @NotBlank(message = "Invalid user id. Empty id.")
    @NotNull(message = "Invalid user id. Null id.")
    private String userId;

}
