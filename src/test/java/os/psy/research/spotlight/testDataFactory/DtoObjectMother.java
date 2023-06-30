package os.psy.research.spotlight.testDataFactory;

import os.psy.research.spotlight.presentation.dto.FocusUnitDto;
import os.psy.research.spotlight.presentation.dto.LinkedResourceDto;
import os.psy.research.spotlight.presentation.dto.WorkingTimeDto;

import java.time.Duration;
import java.time.OffsetDateTime;

public class DtoObjectMother {
    public static FocusUnitDto.FocusUnitDtoBuilder complete() {
        return FocusUnitDto.builder()
                .userId("userId")
                .id("id")
                .linkedResourceDto(LinkedResourceDto.builder()
                        .projectId("projectId")
                        .taskId("taskId")
                        .build())
                .workingTimeDto(WorkingTimeDto.builder()
                        .startedAt(OffsetDateTime.MIN)
                        .completedAt(OffsetDateTime.MAX)
                        .selectedDuration(Duration.ZERO)
                        .build());
    }
}
