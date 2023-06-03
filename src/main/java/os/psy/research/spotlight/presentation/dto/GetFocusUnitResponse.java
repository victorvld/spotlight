package os.psy.research.spotlight.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class GetFocusUnitResponse {

    @NotBlank
    private final String id;

    @NotBlank
    private final String userId;

    // FocusTimeDto focusTimeDto;

    // BreakTimeDto breakTimeDto;

    // InterruptionDto interruptionDto;

    // Label label;

    // Status status;
}
