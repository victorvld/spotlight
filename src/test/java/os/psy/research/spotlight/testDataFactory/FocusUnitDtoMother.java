package os.psy.research.spotlight.testDataFactory;

import os.psy.research.spotlight.presentation.dto.FocusUnitDto;
import os.psy.research.spotlight.presentation.dto.LinkedResourceDto;

public class FocusUnitDtoMother {

    public static FocusUnitDto.FocusUnitDtoBuilder complete() {
        return FocusUnitDto.builder()
                .userId("userId")
                .id("id")
                .linkedResourceDto(LinkedResourceDto.builder()
                        .projectId("projectId")
                        .taskId("taskId")
                        .build());
    }
}
