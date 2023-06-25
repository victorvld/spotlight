package os.psy.research.spotlight.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FocusUnitDto {

    @NotBlank(message = "Invalid user id. Empty id.")
    @NotNull(message = "Invalid user id. Null id.")
    private String userId;

    private LinkedResourceDto linkedResourceDto;

    private String id;
}
