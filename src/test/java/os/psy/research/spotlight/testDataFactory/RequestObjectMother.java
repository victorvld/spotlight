package os.psy.research.spotlight.testDataFactory;

import os.psy.research.spotlight.presentation.dto.BreakTimeDto;
import os.psy.research.spotlight.presentation.dto.InterruptionDto;
import os.psy.research.spotlight.presentation.dto.LinkedResourceDto;
import os.psy.research.spotlight.presentation.dto.WorkingTimeDto;
import os.psy.research.spotlight.presentation.dto.request.RegisterFocusUnitRequest;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

public class RequestObjectMother {

    public static class RegisterFocusUnit {
        public static RegisterFocusUnitRequest.RegisterFocusUnitRequestBuilder complete() {
            return RegisterFocusUnitRequest.builder()
                    .userId("userId")
                    .linkedResourceDto(LinkedResourceDto.builder()
                            .projectId("projectId")
                            .taskId("taskId")
                            .build())
                    .workingTimeDto(WorkingTimeDto.builder()
                            .startedAt(OffsetDateTime.MIN)
                            .completedAt(OffsetDateTime.MAX)
                            .selectedDuration(Duration.ZERO)
                            .interruptionsDto(List.of(InterruptionDto.builder()
                                    .type("type")
                                    .reasonType("reasonType")
                                    .recordedAt(OffsetDateTime.MIN)
                                    .duration(Duration.ZERO)
                                    .build()))
                            .build())
                    .breakTimeDto(BreakTimeDto.builder()
                            .startedAt(OffsetDateTime.MIN)
                            .completedAt(OffsetDateTime.MAX)
                            .selectedDuration(Duration.ZERO)
                            .interruptionsDto(List.of(InterruptionDto.builder()
                                    .type("type")
                                    .reasonType("reasonType")
                                    .recordedAt(OffsetDateTime.MIN)
                                    .duration(Duration.ZERO)
                                    .build()))
                            .build());
        }
    }

}
