package os.spotlight.testDataFactory;

import os.spotlight.presentation.dto.BreakTimeDto;
import os.spotlight.presentation.dto.FocusUnitDto;
import os.spotlight.presentation.dto.WorkingTimeDto;

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
