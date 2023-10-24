package os.psy.research.spotlight.testDataFactory;

import os.psy.research.spotlight.presentation.dto.BreakTimeDto;
import os.psy.research.spotlight.presentation.dto.FocusUnitDto;
import os.psy.research.spotlight.presentation.dto.WorkingTimeDto;

import java.time.Duration;
import java.time.OffsetDateTime;

public class DtoObjectMother {

    public static class FocusUnit {
        public static FocusUnitDto.FocusUnitDtoBuilder complete() {
            return FocusUnitDto.builder()
                    .userId("userId")
                    .id("id")
                    .linkedTaskId("taskId")
                    .workingTimeDto(WorkingTimeDto.builder()
                            .startedAt(OffsetDateTime.MIN)
                            .completedAt(OffsetDateTime.MAX)
                            .plannedMinutes(25)
                            .build())
                    .breakTimeDto(BreakTimeDto.builder()
                            .startedAt(OffsetDateTime.MIN)
                            .completedAt(OffsetDateTime.MAX)
                            .plannedMinutes(25)
                            .build());
        }

    }
}
