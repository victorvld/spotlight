package os.psy.research.spotlight.testDataFactory;

import os.psy.research.spotlight.presentation.dto.FocusUnitDto;
import os.psy.research.spotlight.presentation.dto.LinkedResourceDto;
import os.psy.research.spotlight.presentation.dto.WorkingTimeDto;

import java.time.Duration;
import java.time.OffsetDateTime;

public class FocusUnitDtoMother {
    public static FocusUnitDto.FocusUnitDtoBuilder complete() {
        return FocusUnitDto.builder()
                .userId("userId")
                .id("id")
                .linkedResourceDto(LinkedResourceDto.builder()
                        .projectId("projectId")
                        .taskId("taskId")
                        .build())
                .workingTimeDto(WorkingTimeDto.builder()
                        .startedAt(OffsetDateTime.parse("2021-09-30T15:30:00+01:00"))
                        .selectedDuration(Duration.ofMinutes(25))
                        .completedAt(OffsetDateTime.parse("2021-09-30T15:55:00+01:00"))
                        .build());
    }
}
